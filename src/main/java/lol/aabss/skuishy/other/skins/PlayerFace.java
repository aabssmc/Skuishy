package lol.aabss.skuishy.other.skins;

import org.bukkit.entity.Player;

import org.eclipse.jdt.annotation.Nullable;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerFace {
    public static BufferedImage get(Player player, @Nullable Number size, boolean lay) {
        BufferedImage image = SkinWrapper.imgTexture(player);
        BufferedImage img2 = image.getSubimage(8,8,8,8);
        assert size != null;
        BufferedImage face = new BufferedImage(size.intValue(), size.intValue(), img2.getType());
        Graphics2D facetmp = face.createGraphics();
        facetmp.drawImage(image, 0, 0, size.intValue(), size.intValue(), null);
        facetmp.dispose();
        if (lay) {
            try {
                BufferedImage outerlayer = image.getSubimage(40, 8, 8, 8);
                facetmp.drawImage(outerlayer, 0, 0, size.intValue(), size.intValue(), null);
                facetmp.dispose();
            } catch (Exception e) {
               throw new IllegalStateException(e);
            }
        }
        return face;
    }
}
