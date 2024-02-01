package lol.aabss.skuishy.other;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

import static lol.aabss.skuishy.Skuishy.instance;

public class UpdateChecker implements Listener {

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

    public static boolean updateCheck(CommandSender p){
        String version = latestVersion();
        if (!Objects.equals(version, instance.getPluginMeta().getVersion())){
            p.sendMessage(MiniMessage.miniMessage().deserialize("""
                        
                        <click:open_url:'https://modrinth.com/plugin/skuishy/version/<NEW_VERSION>'><hover:show_text:'Click to update!'><gray>There is a new <color:#40ff00>Skuishy <gray>update!
                        <yellow>Click <green>here</green> to download!
                        """.replaceAll("<NEW_VERSION>", version)));
            return true;
        }return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if (instance.getConfig().getBoolean("update-checker")) {
            if (e.getPlayer().hasPermission("skuishy.updatechecker")) {
                Bukkit.getScheduler().runTaskLater(instance, () -> updateCheck(e.getPlayer()), 100L);
            }
        }
    }
}
