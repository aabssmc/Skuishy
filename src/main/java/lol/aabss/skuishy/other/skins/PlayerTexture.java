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
        String value = Property.jo(p).getValue();
        byte[] playerBytes = Base64.getDecoder().decode(value);
        String playerString = new String(playerBytes);
        Object obj2 = new JSONParser().parse(new StringReader(playerString));
        JSONObject jo2 = (JSONObject) obj2;
        JSONObject textures = (JSONObject) jo2.get("textures");
        JSONObject skin = (JSONObject) textures.get("SKIN");
        String url = (String) skin.get("url");
        return url;
    }
    public static BufferedImage imgTexture(Player player) throws Exception {
        String url = null;
        try {
            url = (String) urlTexture(player);
            URL skinurl = new URL(url);
            BufferedImage image = ImageIO.read(skinurl);
            return image;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
