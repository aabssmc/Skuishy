package lol.aabss.skuishy.other;

import ch.njol.skript.Skript;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;
import org.eclipse.jdt.annotation.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import static lol.aabss.skuishy.Skuishy.instance;

public class SkinWrapper {

    public static BufferedImage get(Player player, @Nullable Number size, boolean lay) {
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
        if (Skript.methodExists(com.destroystokyo.paper.profile.PlayerProfile.class, "getTextures")) {
            URL url = player.getPlayerProfile().getTextures().getSkin();
            if (url != null) {
                try {
                    return ImageIO.read(url);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } return null;
    }

    public static void setSkin(Player player, @Nullable String skin){
        if (Skript.methodExists(com.destroystokyo.paper.profile.PlayerProfile.class, "getTextures") && Skript.classExists("org.bukkit.profile.PlayerTextures")) {
            if (skin != null) {
                if (skin.length() <= 16) {
                    if (player.getName().equals(skin)) {
                        PlayerProfile e = player.getPlayerProfile();
                        e.getTextures().clear();
                        PlayerTextures t = e.getTextures();
                        t.clear();
                        e.setTextures(t);
                        player.setPlayerProfile(e);
                    }
                    PlayerProfile newprofile = Bukkit.createProfile(skin);
                    newprofile.complete();
                    PlayerProfile profile = player.getPlayerProfile();
                    profile.setProperties(newprofile.getProperties());
                    player.setPlayerProfile(profile);
                } else {
                    URL url = null;
                    try {
                        url = new URL(skin);
                    } catch (MalformedURLException ignored) {
                        instance.getLogger().warning("Invalid URL");
                    }
                    PlayerProfile e = player.getPlayerProfile();
                    PlayerTextures t = e.getTextures();
                    t.setSkin(url);
                    e.setTextures(t);
                    player.setPlayerProfile(e);
                }
            } else{
                PlayerProfile e = player.getPlayerProfile();
                PlayerTextures t = e.getTextures();
                t.setSkin(null);
                e.setTextures(t);
                player.setPlayerProfile(e);
            }
        }
    }

    public static void setSkin(Player player, String value, @Nullable String signature){
        JsonObject json = JsonParser.parseString(new String(Base64.getDecoder().decode(value))).getAsJsonObject();
        setSkin(player,
                json.getAsJsonArray("textures").getAsJsonObject().getAsJsonArray("SKIN").getAsJsonObject().get("url").getAsString()
        );
    }
}
