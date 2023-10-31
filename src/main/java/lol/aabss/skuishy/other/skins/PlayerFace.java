package lol.aabss.skuishy.other.skins;

import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerFace {
    public static BufferedImage get(Player player, @Nullable Number size, boolean lay) throws Exception {
        BufferedImage image = PlayerTexture.imgTexture(player);
        BufferedImage img2 = image.getSubimage(8,8,16,16);
        assert size != null;
        BufferedImage face = new BufferedImage(size.intValue(), size.intValue(), img2.getType());
        Graphics2D facetmp = face.createGraphics();
        facetmp.drawImage(image, 0, 0, size.intValue(), size.intValue(), null);
        facetmp.dispose();
        if (lay) {
            BufferedImage outerlayer = image.getSubimage(40, 8, 48, 16);
            facetmp.drawImage(outerlayer, 0, 0, size.intValue(), size.intValue(), null);
            facetmp.dispose();
        }
        return face;
    }
}
