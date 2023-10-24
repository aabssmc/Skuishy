package lol.aabss.skuishy.other;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class PlayerSignature {

    public static String Signature(UUID uuid) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false").openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
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
            String signature = (String) properties.get("signature");
            return signature;
        }
        return null;
    }

}
