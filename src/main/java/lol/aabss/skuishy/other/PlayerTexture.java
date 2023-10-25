package lol.aabss.skuishy.other;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

public class PlayerTexture {
    public static String urlTexture(UUID uuid) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false").openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        String url = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            reader.close();
            String JsonContent = content.toString();
            Object obj = new JSONParser().parse(new StringReader(JsonContent));
            JSONObject jo = (JSONObject) obj;
            JSONArray ja = (JSONArray) jo.get("properties");
            JSONObject properties = (JSONObject) ja.get(0);
            String value = (String) properties.get("value");

            byte[] playerBytes = Base64.getDecoder().decode(value);
            String playerString = new String(playerBytes);

            Object obj2 = new JSONParser().parse(new StringReader(playerString));
            JSONObject jo2 = (JSONObject) obj2;
            JSONObject textures = (JSONObject) jo2.get("textures");
            JSONObject skin = (JSONObject) textures.get("SKIN");
            url = (String) skin.get("url");
        }
        return url;
    }
    public static BufferedImage imgTexture(UUID uuid) throws Exception {
        String url = (String) urlTexture(uuid);
        URL skinurl = new URL(url);
        BufferedImage image = ImageIO.read(skinurl);
        return image;
    }
}
