package lol.aabss.skuishy.other;

import ch.njol.skript.Skript;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;
import org.eclipse.jdt.annotation.Nullable;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import static lol.aabss.skuishy.Skuishy.instance;

public class SkinWrapper {

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

    public static void setSkin(Player player, @Nullable String skin){
        if (Skript.methodExists(PlayerProfile.class, "getTextures") && Skript.classExists("org.bukkit.profile.PlayerTextures")) {
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
        JSONObject json = new JSONObject(new String(Base64.getDecoder().decode(value)));
        setSkin(player,json.getJSONArray("textures").getJSONArray(0).getString(0));
    }

    @SuppressWarnings("deprecation")
    public static String sendHead(String name, boolean helm) {
        BufferedImage img;
        try {
            img = ImageIO.read(new URL("https://minotar.net/"+(helm?"helm":"avatar")+"/" + name + "/8.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] result = new String[8];
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                ChatColor c = ChatColor.of(new Color(img.getRGB(x,y)));
                if(result[y] == null) result[y] = "";
                result[y] += (c.toString() + "\u2588").replaceAll("\\?", "");
            }
        }
        return String.join("\n", result);
    }


}
