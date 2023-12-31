package lol.aabss.skuishy.other.skins;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public abstract class SkinWrapper {

    public static BufferedImage get(Player player, @Nullable Number size, boolean lay) throws Exception {
        BufferedImage textureImage = imgTexture(player);
        BufferedImage subImage = textureImage.getSubimage(8,8,8,8);
        assert size != null;
        BufferedImage face = new BufferedImage(size.intValue(), size.intValue(), subImage.getType());
        Graphics2D faceTmp = face.createGraphics();
        faceTmp.drawImage(textureImage, 0, 0, size.intValue(), size.intValue(), null);
        faceTmp.dispose();
        if (lay) {
            try {
                BufferedImage outerLayer = textureImage.getSubimage(40, 8, 8, 8);
                faceTmp.drawImage(outerLayer, 0, 0, size.intValue(), size.intValue(), null);
                faceTmp.dispose();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return face;
    }

    public static ProfileProperty getProfileProperties(Player p) {
        PlayerProfile playerProfile = p.getPlayerProfile();
        ProfileProperty prop = null;
        for (ProfileProperty property : playerProfile.getProperties()) {
            if (property.getName().equals("textures")) {
                prop = property;
                break;
            }
        }
        return prop;
    }

    public static BufferedImage imgTexture(Player player) {
        try {
            URL url = player.getPlayerProfile().getTextures().getSkin();
            assert url != null;
            return ImageIO.read(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setSkin(Player player, String skin){
        if (player.getName().equals(skin)){
            player.getPlayerProfile().getTextures().clear();
        }
        PlayerProfile newprofile = Bukkit.createProfile(skin);
        newprofile.complete();
        PlayerProfile profile = player.getPlayerProfile();
        profile.setProperties(newprofile.getProperties());
        player.setPlayerProfile(profile);
    }

}
