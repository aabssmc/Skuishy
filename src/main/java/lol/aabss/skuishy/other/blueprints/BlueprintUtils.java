package lol.aabss.skuishy.other.blueprints;

import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.mineskin.Variant;
import org.json.JSONObject;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BlueprintUtils {

    public static JSONObject json;

    public static void loadJson(){
        try {
            File file = new File(Skuishy.instance.getDataFolder().getAbsolutePath() + "/blueprints/");
            if (!file.exists()) {
                if (file.mkdirs()) {
                    File jsonfile = new File(file.getAbsolutePath() + "/blueprints.json");
                    if (jsonfile.createNewFile()) {
                        FileWriter writer = new FileWriter(jsonfile);
                        writer.write(new JSONObject().toString(4));
                        json = new JSONObject();
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
            return new Blueprint(ImageIO.read(file), Variant.valueOf(getJson().getString(name)));
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

    public static JSONObject getJson(){
        try {
            File jsonfile = new File(Skuishy.instance.getDataFolder().getAbsolutePath() + "/blueprints/blueprints.json");
            Scanner scan = new Scanner(jsonfile);
            StringBuilder jsonstr = new StringBuilder();
            while (scan.hasNextLine()) {
                jsonstr.append(scan.nextLine());
            }
            return new JSONObject(jsonstr);
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
            writer.write(json.toString(4));
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Blueprint fromImage(BufferedImage image){
        return new Blueprint(image, Variant.AUTO);
    }

    // useful for auto variant
    public static Variant getVariant(BufferedImage image){
        return new Color(image.getRGB(39, 52)).getAlpha() == 255 ? Variant.SLIM : Variant.CLASSIC;
    }
}
