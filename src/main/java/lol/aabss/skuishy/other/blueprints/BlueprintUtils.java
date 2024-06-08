package lol.aabss.skuishy.other.blueprints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.mineskin.Variant;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BlueprintUtils {

    public static JsonObject json;
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void loadJson(){
        try {
            File file = new File(Skuishy.instance.getDataFolder().getAbsolutePath() + "/blueprints/");
            if (!file.exists()) {
                if (file.mkdirs()) {
                    File jsonfile = new File(file.getAbsolutePath() + "/blueprints.json");
                    if (jsonfile.createNewFile()) {
                        FileWriter writer = new FileWriter(jsonfile);
                        writer.write(gson.toJson(new JsonObject()));
                        json = new JsonObject();
                        writer.close();
                    }
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        if (json == null) json = getJson();
    }

    @Nullable
    public static Blueprint load(String name) {
        File file = new File(Skuishy.instance.getDataFolder().getAbsolutePath()+"/blueprints/"+name+".png");
        if (!file.exists()){
            return null;
        }
        try {
            return new Blueprint(ImageIO.read(file), Variant.valueOf(getJson().get(name).getAsString()));
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void delete(String name) {
        File file = new File(Skuishy.instance.getDataFolder().getAbsolutePath()+"/blueprints/"+name+".png");
        if (!file.delete()) {
            Skuishy.instance.getLogger().warning("Failed to delete image file of "+name+"!");
        }
        json.remove(name);
        saveJson();
    }

    private static JsonObject getJson(){
        try {
            File jsonfile = new File(Skuishy.instance.getDataFolder().getAbsolutePath() + "/blueprints/blueprints.json");
            Scanner scan = new Scanner(jsonfile);
            StringBuilder jsonstr = new StringBuilder();
            while (scan.hasNextLine()) {
                jsonstr.append(scan.nextLine());
            }
            return JsonParser.parseString(jsonstr.toString()).getAsJsonObject();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void saveJson(){
        try {
            File jsonfile = new File(Skuishy.instance.getDataFolder().getAbsolutePath() + "/blueprints/blueprints.json");
            jsonfile.delete();
            jsonfile.createNewFile();
            FileWriter writer = new FileWriter(jsonfile);
            writer.write(gson.toJson(json));
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static Blueprint fromImage(BufferedImage image){
        return new Blueprint(image, Variant.AUTO);
    }

    // useful for auto variant
    public static Variant getVariant(BufferedImage image){
        return new Color(image.getRGB(39, 52)).getAlpha() == 255 ? Variant.SLIM : Variant.CLASSIC;
    }
}
