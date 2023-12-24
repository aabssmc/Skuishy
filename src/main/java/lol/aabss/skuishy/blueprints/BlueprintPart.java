package lol.aabss.skuishy.blueprints;

import javax.annotation.Nullable;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BlueprintPart {
    public enum Part {
        HEAD,
        TORSO,
        RIGHT_ARM,
        LEFT_ARM,
        RIGHT_LEG,
        LEFT_LEG;
    }

    public static Blueprint blueprintPart(Blueprint print, Part part, boolean layer, @Nullable boolean isSlim){
        if (part == Part.HEAD){
            return head(print, layer);
        }
        else if (part == Part.TORSO){
            return torso(print, layer);
        }
        else if (part == Part.RIGHT_ARM){
            return rightArm(print, layer, isSlim);
        }
        else if (part == Part.LEFT_ARM){
            return leftArm(print, layer, isSlim);
        }
        else if (part == Part.RIGHT_LEG){
            return rightLeg(print, layer);
        }
        else if (part == Part.LEFT_LEG){
            return leftLeg(print, layer);
        }
        return null;
    }

    private static Blueprint head(Blueprint print, boolean layer){
        BufferedImage image = print.image();
        BufferedImage image2 = Blueprint.blueprint().image();
        Graphics2D img = image2.createGraphics();
        img.drawImage(image.getSubimage(0,0,32,16), 0, 0, null);
        if (layer){
            img.drawImage(image.getSubimage(32,0,32,16), 0, 0, null);
        }
        return new Blueprint(image2);
    }
    
    private static Blueprint torso(Blueprint print, boolean layer){
        BufferedImage image = print.image();
        BufferedImage image2 = Blueprint.blueprint().image();
        Graphics2D img = image.createGraphics();
        img.drawImage(image.getSubimage(16,16,24,16), 16,16, null);
        if (layer){
            img.drawImage(image.getSubimage(16,32,24,16), 16,32, null);
        }
        return new Blueprint(image2);
    }

    private static Blueprint rightArm(Blueprint print, boolean layer, boolean isSlim){
        BufferedImage image = print.image();
        BufferedImage image2 = Blueprint.blueprint().image();
        Graphics2D img = image.createGraphics();
        if (!isSlim){
            img.drawImage(image.getSubimage(40,16,16,16), 40,16, null);
            if (layer){
                img.drawImage(image.getSubimage(40,32,16,16), 40,32, null);
            }
            return new Blueprint(image2);
        }
        img.drawImage(image.getSubimage(40,16,14,16), 40,16, null);
        if (layer){
            img.drawImage(image.getSubimage(40,32,14,16), 40,32, null);
        }
        return new Blueprint(image2);
    }

    private static Blueprint leftArm(Blueprint print, boolean layer, boolean isSlim){
        BufferedImage image = print.image();
        BufferedImage image2 = Blueprint.blueprint().image();
        Graphics2D img = image.createGraphics();
        if (!isSlim){
            img.drawImage(image.getSubimage(32,48,16,16), 32,48, null);
            if (layer){
                img.drawImage(image.getSubimage(48,48,16,16), 48,48, null);
            }
            return new Blueprint(image2);
        }
        img.drawImage(image.getSubimage(32,48,14,16), 32,48, null);
        if (layer){
            img.drawImage(image.getSubimage(48,48,14,16), 48,48, null);
        }
        return new Blueprint(image2);
    }

    private static Blueprint leftLeg(Blueprint print, boolean layer){
        BufferedImage image = print.image();
        BufferedImage image2 = Blueprint.blueprint().image();
        Graphics2D img = image.createGraphics();
        img.drawImage(image.getSubimage(0,16,16,16), 0,16, null);
        if (layer){
            img.drawImage(image.getSubimage(0,32,16,16), 0,32, null);
        }
        return new Blueprint(image2);
    }

    private static Blueprint rightLeg(Blueprint print, boolean layer){
        BufferedImage image = print.image();
        BufferedImage image2 = Blueprint.blueprint().image();
        Graphics2D img = image.createGraphics();
        img.drawImage(image.getSubimage(16,48,16,16), 16,48, null);
        if (layer){
            img.drawImage(image.getSubimage(0,48,16,16), 0,48, null);
        }
        return new Blueprint(image2);
    }

}

