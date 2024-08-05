package lol.aabss.skuishy.other;

import ch.njol.skript.Skript;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.blueprints.BlueprintUtils;
import lol.aabss.skuishy.other.mineskin.MineskinClient;
import lol.aabss.skuishy.other.mineskin.SkinOptions;
import lol.aabss.skuishy.other.mineskin.Variant;
import lol.aabss.skuishy.other.mineskin.Visibility;
import lol.aabss.skuishy.other.mineskin.data.Texture;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

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

    private static BufferedImage getHead(String name, boolean helm) throws IOException {
        OfflinePlayer player = Bukkit.getOfflinePlayer(name);
        if (player.getPlayerProfile().getTextures().getSkin() == null){
            return ImageIO.read(new URL("https://minotar.net/"+(helm ? "helm" : "avatar")+"/"+name+"/8.png"));
        }
        BufferedImage skin = ImageIO.read(player.getPlayerProfile().getTextures().getSkin());
        BufferedImage front = skin.getSubimage(8, 8, 8, 8);
        if (helm) {
            front.getGraphics().drawImage(skin.getSubimage(40, 8, 8, 8), 0, 0, null);
        }
        return front;
    }

    @SuppressWarnings("deprecation")
    public static String sendHead(String name, boolean helm, String... texts) {
        try {
            BufferedImage img = getHead(name, helm);
            String[] result = new String[8];
            for (int y = 0; y < 8; y++) {
                result[y] = "";
                for (int x = 0; x < 8; x++) {
                    ChatColor c = ChatColor.of(new Color(img.getRGB(x, y)));
                    result[y] += (c.toString() + "\u2588").replaceAll("\\?", "");
                }
                if (texts.length > y) {
                    result[y] += " " + ChatColor.RESET + texts[y];
                }
            }
            return String.join("\n", result);
        } catch (IOException e) {
            Skuishy.Logger.exception(e);
            return null;
        }
    }

    public static CompletableFuture<Texture> uploadSkin(BufferedImage image) throws IOException {
        return client.generateUpload(image, SkinOptions.create("Skuishy-Upload", BlueprintUtils.getVariant(image), Visibility.PRIVATE))
                .thenApply(result -> result.data.texture);
    }

    public static CompletableFuture<Texture> uploadSkin(String url) {
        return client.generateUrl(url, SkinOptions.create("Skuishy-Upload", Variant.AUTO, Visibility.PRIVATE))
                .thenApply(result -> result.data.texture);
    }

}
