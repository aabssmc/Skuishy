package lol.aabss.skuishy.other;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lol.aabss.skuishy.Skuishy;
import org.bukkit.Bukkit;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetVersion {
    public static String version(){
        return Bukkit.getBukkitVersion().split("-")[0];
    }

    public static String latestVersion() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.modrinth.com/v2/project/skuishy/version"))
                .build();
        try {
            String body = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            return JsonParser.parseString(body).getAsJsonArray().get(0).getAsJsonObject().get("version_number").getAsString();
        } catch (Exception e) {
            Skuishy.Logger.exception(e);
            return null;
        }
    }

    public static boolean latestFile(){
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.modrinth.com/v2/project/skuishy/version"))
                .build();
        try {
            String body = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            JsonObject object = JsonParser.parseString(body).getAsJsonArray().get(0).getAsJsonObject().get("files").getAsJsonArray().get(0).getAsJsonObject();
            String url = object.get("url").getAsString();
            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(
                         Bukkit.getPluginsFolder().getAbsoluteFile()+"/"+object.get("filename").getAsString())
            ) {
                Bukkit.getConsoleSender().sendMessage(Bukkit.getPluginsFolder().getAbsoluteFile()+"/"+object.get("filename").getAsString());
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
                return true;
            } catch (IOException e) {
                Skuishy.Logger.exception(e);
                return false;
            }
        } catch (Exception e) {
            Skuishy.Logger.exception(e);
            return false;
        }
    }

    public static String latestSkriptVersion() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/SkriptLang/Skript/releases/latest"))
                .build();
        try {
            String body = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            return JsonParser.parseString(body).getAsJsonObject().get("tag_name").getAsString();
        } catch (Exception e) {
            Skuishy.Logger.exception(e);
            return null;
        }
    }
}
