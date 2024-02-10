package lol.aabss.skuishy.other;

import org.bukkit.Bukkit;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetVersion {
    public static String version(){
        return Bukkit.getBukkitVersion().split("-")[0];
    }

    public static int datapackVersion(){
        String v = version();
        return switch (v){
            case "1.17", "1.17.1" -> 7;
            case "1.18", "1.18.1" -> 8;
            case "1.18.2" -> 9;
            case "1.19", "1.19.1", "1.19.2", "1.19.3" -> 10;
            case "1.19.4" -> 12;
            case "1.20", "1.20.1" -> 15;
            case "1.20.2" -> 18;
            case "1.20.3", "1.20.4" -> 26;
            default -> 0;
        };
    }

    public static String latestVersion() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.modrinth.com/v2/project/skuishy/version"))
                .build();
        try {
            String body = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return new JSONArray(body).getJSONObject(0).getString("version_number");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String latestSkriptVersion() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/SkriptLang/Skript/releases/latest"))
                .build();
        try {
            String body = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return new JSONObject(body).getString("tag_name");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
