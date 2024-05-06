package lol.aabss.skuishy.other;

import ch.njol.skript.Skript;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import lol.aabss.skuishy.Skuishy;
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
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SkinWrapper {

    public static MineskinClient client = new MineskinClient("Skuishy-Agent");
    public static SkinOptions options = SkinOptions.create("Skuishy-Upload", Variant.AUTO, Visibility.PRIVATE);

    public static ProfileProperty getProfileProperties(PlayerProfile p) {
        return p.getProperties().iterator().next();
    }

    public static void setSkin(Player player, String skin){
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

    public static void setSkin(Player player, Texture texture) {
        setSkin(player, texture.value, texture.signature);
    }

    public static void setSkin(Player player, String value, @Nullable String signature){
        PlayerProfile profile = player.getPlayerProfile();
        profile.removeProperties(profile.getProperties());
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
            return java.lang.String.join("\n", result);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    public static Texture uploadSkin(BufferedImage image) {
        final Texture[] t = {};
        Bukkit.getScheduler().runTaskAsynchronously(Skuishy.instance, bukkitTask -> {
            try {
                CompletableFuture<Skin> future = client.generateUpload(image, options);
                t[0] = future.thenApplyAsync(result -> result.data.texture).get();
            } catch (IOException | InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        return t[0];
    }

    public static Texture uploadSkin(String url) {
        final Texture[] t = {};
        Bukkit.getScheduler().runTaskAsynchronously(Skuishy.instance, bukkitTask -> {
            try {
                CompletableFuture<Skin> future = client.generateUrl(url, options);
                t[0] = future.thenApplyAsync(result -> result.data.texture).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        return t[0];
    }


}
