package lol.aabss.skuishy.other.skins;

import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.StringReader;
import java.net.URL;
import java.util.Base64;

public class PlayerTexture {
    public static String urlTexture(Player player) throws Exception {

        // Gets the player's value
        Player p = player.getPlayer();
        assert p != null;
        String value = SkinWrapper.getProfileProperties(p).getValue();
        byte[] playerBytes = Base64.getDecoder().decode(value);
        String playerString = new String(playerBytes);
        Object obj2 = new JSONParser().parse(new StringReader(playerString));
        JSONObject jo2 = (JSONObject) obj2;
        JSONObject textures = (JSONObject) jo2.get("textures");
        JSONObject skin = (JSONObject) textures.get("SKIN");
        return (String) skin.get("url");
    }
    public static BufferedImage imgTexture(Player player) {
        String url;
        try {
            url = urlTexture(player);
            URL skinurl = new URL(url);
            return ImageIO.read(skinurl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
