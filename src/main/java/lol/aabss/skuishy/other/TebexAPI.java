package lol.aabss.skuishy.other;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.aabss.skuishy.Skuishy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TebexAPI {
    public static JsonObject api(String endpoint, String method) throws IOException {
        String secret = (String) Skuishy.getPlugin(Skuishy.class).getConfig().get("tebex-secret");
        URL url = new URL("https://plugin.tebex.io/" + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("X-Tebex-Secret", secret);
        if (connection.getResponseCode() == 200){
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return JsonParser.parseString(response.toString()).getAsJsonObject();
        }
        return null;
    }
}