package lol.aabss.skuishy.blueprints;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerTextures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Blueprint {

    // add namemc skin transformations
    // transformation url:
    // POST https://namemc.com/transform-skin
    // payload:
    // skin=[skinhash]&transformation=[transformation]
    // FIGURE OUT SKINHASHES

    private BufferedImage image;

    Blueprint(BufferedImage image) {
        this.image = image;
    }

    public static Blueprint blueprint(Player player) {
        try {
            BufferedImage playerSkin = ImageIO.read(player.getPlayerProfile().getTextures().getSkin());
            return new Blueprint(playerSkin);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Blueprint blueprint(OfflinePlayer player) {
        try {
            BufferedImage playerSkin = ImageIO.read(player.getPlayerProfile().getTextures().getSkin());
            return new Blueprint(playerSkin);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Blueprint blueprint(String player) {
        try {
            BufferedImage playerSkin = ImageIO.read(Bukkit.getOfflinePlayer(player).getPlayerProfile().getTextures().getSkin());
            return new Blueprint(playerSkin);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Blueprint overlay(Blueprint overlay, Blueprint blueprint){
        BufferedImage print = blueprint.image();
        Graphics2D e = print.createGraphics();
        e.drawImage(overlay.image(), 0, 0, null);
        return new Blueprint(print);
    }
    

    public static Blueprint blueprint() {
        return new Blueprint(new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB));
    }

    public void setSkin(Player player){
        URL url = Mineskin.upload(image, player.getName());
        PlayerProfile profile = player.getPlayerProfile();
        PlayerTextures textures = profile.getTextures();
        textures.setSkin(url);
        profile.setTextures(textures);
        player.setPlayerProfile(profile);
    }

    public Color pixelColor(Blueprint print, int x, int y){
        return new Color(print.image().getRGB(x,y));
    }

    public void pixelColor(Blueprint print, int x, int y, Color color){
        print.image().setRGB(x, y, color.getRGB());
    }



    public BufferedImage image(){
        return this.image;
    }

}
