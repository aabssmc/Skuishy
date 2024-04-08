package lol.aabss.skuishy.other;

import ch.njol.skript.Skript;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import lol.aabss.skuishy.other.mineskin.MineskinClient;
import lol.aabss.skuishy.other.mineskin.SkinOptions;
import lol.aabss.skuishy.other.mineskin.Variant;
import lol.aabss.skuishy.other.mineskin.Visibility;
import lol.aabss.skuishy.other.mineskin.data.Skin;
import lol.aabss.skuishy.other.mineskin.data.Texture;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SkinWrapper {

    public static ProfileProperty getProfileProperties(PlayerProfile p) {
        ProfileProperty prop = null;
        for (ProfileProperty property : p.getProperties()) {
            if (property.getName().equals("textures")) {
                prop = property;
                break;
            }
        }
        return prop;
    }

    public static void setSkin(Player player, @Nullable String skin){
        if (Skript.classExists("com.destroystokyo.paper.profile.PlayerProfile")
                && Skript.classExists("org.bukkit.profile.PlayerTextures")
        ) {
            if (player.getName().equals(skin)) {
                PlayerProfile e = player.getPlayerProfile();
                e.getTextures().clear();
                PlayerTextures t = e.getTextures();
                t.clear();
                e.setTextures(t);
                player.setPlayerProfile(e);
            }
            PlayerProfile newprofile = Bukkit.createProfile(skin);
            newprofile.setTextures(newprofile.getTextures());
            newprofile.complete();
            PlayerProfile profile = player.getPlayerProfile();
            profile.setProperties(newprofile.getProperties());
            player.setPlayerProfile(profile);
        }
    }

    public static void setSkin(Player player, URL skin){
        Texture texture = uploadSkin(skin.toString());
        setSkin(player, texture.value, texture.signature);
    }

    public static void setSkin(Player player, RenderedImage img){
        Texture texture = uploadSkin(img);
        setSkin(player, texture.value, texture.signature);
    }

    public static void setSkin(Player player, String value, @Nullable String signature){
        PlayerProfile profile = player.getPlayerProfile();
        profile.removeProperty(getProfileProperties(player.getPlayerProfile()));
        profile.setProperty(new ProfileProperty("textures", value, signature));
        player.setPlayerProfile(profile);
    }

    @SuppressWarnings("deprecation")
    public static String sendHead(String name, boolean helm) {
        try {
            BufferedImage img = ImageIO.read(new URL("https://minotar.net/" + (helm ? "helm" : "avatar") + "/" + name + "/8.png"));
            String[] result = new String[8];
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    ChatColor c = ChatColor.of(new Color(img.getRGB(x, y)));
                    if (result[y] == null) result[y] = "";
                    result[y] += (c.toString() + "\u2588").replaceAll("\\?", "");
                }
            }
            return String.join("\n", result);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Texture uploadSkin(RenderedImage img) {
        try {
            Skin skin = new MineskinClient("Skuishy-Agent")
                    .generateUpload(img, SkinOptions.create("Skuishy-Upload", Variant.AUTO, Visibility.PRIVATE))
                    .get();
            return skin.data.texture;
        } catch (IOException | ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Texture uploadSkin(String img) {
        try {
            Skin skin = new MineskinClient("Skuishy-Agent")
                    .generateUrl(img, SkinOptions.create("Skuishy-Upload", Variant.AUTO, Visibility.PRIVATE))
                    .get();
            return skin.data.texture;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
