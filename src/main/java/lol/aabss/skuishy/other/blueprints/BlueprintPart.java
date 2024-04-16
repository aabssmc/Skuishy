package lol.aabss.skuishy.other.blueprints;

import lol.aabss.skuishy.other.mineskin.Variant;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BlueprintPart {
    public enum Part {
        HEAD(0, 0, 32, 0, 32, 32, 16),
        TORSO(16, 16, 16, 32, 24, 24, 16),


        RIGHT_ARM(40, 16, 40, 32, 14, 16,16),
        LEFT_ARM(32, 48, 48, 48, 14, 16, 16),
        RIGHT_LEG(0, 16, 0, 32, 16, 16, 16),
        LEFT_LEG(16, 48, 0, 48, 16, 16, 16);

        public final int x;
        public final int y;
        public final int lx;
        public final int ly;
        public final int sw;
        public final int w;
        public final int h;


        Part(int x, int y, int layerx, int layery, int slimwidth, int width, int height) {
            this.x = x;
            this.y = y;
            this.lx = layerx;
            this.ly = layery;
            this.sw = slimwidth;
            this.w = width;
            this.h = height;
        }
    }

    public static Blueprint blueprintPart(Blueprint print, Part p, boolean layer){
        Variant model = print.model();
        if (print.model() == Variant.AUTO){
            model = BlueprintUtils.getVariant(print.image());
        }
        BufferedImage image = new Blueprint(model).image();
        Graphics2D img = image.createGraphics();
        if (model == Variant.CLASSIC){
            img.drawImage(print.image().getSubimage(p.x, p.y, p.w, p.h), p.x, p.y, null);
            if (layer){
                img.drawImage(print.image().getSubimage(p.lx, p.ly, p.w,p.h), p.x, p.y, null);
            }
            return new Blueprint(image, Variant.CLASSIC);
        }
        img.drawImage(print.image().getSubimage(p.x, p.y, p.sw, p.h), p.x, p.y, null);
        if (layer) {
            img.drawImage(print.image().getSubimage(p.lx, p.ly, p.sw, p.h), p.x, p.y, null);
        }
        return new Blueprint(image, Variant.SLIM);
    }

}

