package lol.aabss.skuishy.other.blueprints;

import ch.njol.skript.util.Color;
import ch.njol.skript.util.ColorRGB;
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.mineskin.Variant;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static lol.aabss.skuishy.Skuishy.last_blueprint;
import static lol.aabss.skuishy.other.blueprints.BlueprintUtils.json;


public class Blueprint {

    private final BufferedImage image;
    private final @NotNull Variant model;

    public Blueprint(@NotNull Variant model) {
        this.model = model;
        this.image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        last_blueprint = this;
    }

    public Blueprint(Color color, @NotNull Variant model) {
        this.model = model;
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = img.createGraphics();
        graphics.setColor(new java.awt.Color(color.asBukkitColor().asARGB()));
        graphics.fillRect(0, 0, img.getWidth(), img.getHeight());
        graphics.dispose();
        this.image = img;
        last_blueprint = this;
    }

    public Blueprint(BufferedImage image, @NotNull Variant model) {
        this.model = model;
        this.image = image;
        last_blueprint = this;
    }

    public Blueprint(URL url, @NotNull Variant model) {
        this.model = model;
        try {
            this.image = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        last_blueprint = this;
    }

    public Blueprint(String player) {
        try {
            this.image = ImageIO.read(new URL("https://minotar.net/skin/"+player));
            this.model = BlueprintUtils.getVariant(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        last_blueprint = this;
    }

    public void save(String name) {
        File file = new File(Skuishy.instance.getDataFolder().getAbsolutePath()+"/blueprints/");
        if (!file.exists()){
            if (!file.mkdirs()){
                Skuishy.instance.getLogger().warning("Failed to make blueprints directory!");
            }
        }
        try {
            File print = new File(file.getAbsolutePath()+"/"+name+".png");
            ImageIO.write(image, "png", print);
            json.remove(name);
            json.put(name, model.name());
            BlueprintUtils.saveJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void overlay(Blueprint blueprint) {
        Graphics2D graphics = blueprint.image.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
    }

    public void replaceColor(Color before, Color after) {
        for (int x = 0; x < image.getWidth(null); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == before.asBukkitColor().asARGB()) {
                    image.setRGB(x, y, after.asBukkitColor().asARGB());
                }
            }
        }
    }

    public Color pixelColor(int x, int y){
        return ColorRGB.fromString(String.valueOf(image.getRGB(x, y)));
    }

    public void pixelColor(int x, int y, Color color){
        image.setRGB(x, y, color.asBukkitColor().asARGB());
    }

    public BufferedImage image(){
        return this.image;
    }

    public Variant model(){
        return this.model;
    }

    public Blueprint duplicate(){
        return new Blueprint(image, model);
    }

}
