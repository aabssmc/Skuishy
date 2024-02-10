package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.util.Version;
import lol.aabss.skuishy.hooks.Metrics;
import lol.aabss.skuishy.other.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lol.aabss.skuishy.other.GetVersion.latestSkriptVersion;
import static lol.aabss.skuishy.other.GetVersion.latestVersion;
import static lol.aabss.skuishy.other.SubCommands.*;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class Skuishy extends JavaPlugin implements CommandExecutor, TabCompleter {

    public static Skuishy instance;
    public static SkriptAddon addon;
    public static long start;
    public static PermissionAttachment last_permission_attachment;
    public static Permission last_permission;
    public static boolean dh = false;
    public static boolean vc = false;
    public static boolean vu = false;
    public static String latest_version;
    public static String latest_skript_version;

    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new UpdateChecker(), this);
        getServer().getPluginCommand("skuishy").setExecutor(this);
        getServer().getPluginCommand("skuishy").setTabCompleter(this);
        Metrics metrics = new Metrics(this, 20162);
        instance = this;
        try {
            addon = Skript.registerAddon(this);
            addon.setLanguageFileDirectory("lang");
            addon.loadClasses("lol.aabss.skuishy.elements");
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
                getLogger().info("DecentHolograms found! Enabling DecentHolograms elements...");
                String v = Bukkit.getPluginManager().getPlugin("DecentHolograms").getDescription().getVersion();
                Plugin decentHolograms = Bukkit.getPluginManager().getPlugin("DecentHolograms");
                Version decentHoloVersion = new Version(decentHolograms.getPluginMeta().getVersion());
                if (decentHoloVersion.compareTo(2,8,6) < 0) {
                    getLogger().warning("You must be running decent holograms version 2.8.6 as the minimum");
                } else{
                    addon.loadClasses("lol.aabss.skuishy.hooks.decentholograms");
                    getLogger().info("DecentHolograms elements loaded!");
                    dh = true;
                }
            } else getLogger().info("DecentHolograms not found, skipping!");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
                getLogger().info("Vivecraft-Spigot-Extensions found! Enabling Vivecraft-Spigot-Extensions elements...");
                addon.loadClasses("lol.aabss.skuishy.hooks.vivecraft");
                getLogger().info("Vivecraft-Spigot-Extensions elements loaded!");
                vc = true;
            } else getLogger().info("Vivecraft-Spigot-Extensions not found, skipping!");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
                getLogger().info("Vulcan found! Enabling Vulcan elements...");
                addon.loadClasses("lol.aabss.skuishy.hooks.vulcan");
                getLogger().info("Vulcan elements loaded!");
                vu = true;
            } else getLogger().info("Vulcan not found, skipping!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        metrics.addCustomChart(new Metrics.SimplePie("decentholograms", () -> dh ? "true" : "false"));
        metrics.addCustomChart(new Metrics.SimplePie("vivecraft", () -> vc ? "true" : "false"));
        metrics.addCustomChart(new Metrics.SimplePie("vulcan", () -> vu ? "true" : "false"));
        start = System.currentTimeMillis()/50;
        getLogger().info("Skuishy has been enabled!");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            latest_version = latestVersion();
            latest_skript_version = latestSkriptVersion();
            if (getConfig().getBoolean("version-check-msg")) getLogger().info("Got latest version.");
        }, 0L, 222000L);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            sender.sendMessage(miniMessage().deserialize(
                    "<red>/skuishy <" +
                            "<click:run_command:'/skuishy info'><hover:show_text:'<green>/skuishy info'>version</hover></click>" +
                            " | " +
                            "<click:run_command:'/skuishy reload'><hover:show_text:'<green>/skuishy reload'>reload</hover></click>" +
                            " | " +
                            "<click:run_command:'/skuishy update'><hover:show_text:'<green>/skuishy update'>update</hover></click>" +
                            " | " +
                            "<click:run_command:'/skuishy version'><hover:show_text:'<green>/skuishy version'>version</hover></click>" +
                            ">"
            ));
        } else{
            switch (args[0]) {
                case "info" -> cmdInfo(sender);
                case "reload" -> cmdReload(sender);
                case "update" -> cmdUpdate(sender);
                case "version" -> cmdVersion(sender);
                default -> sender.sendMessage(miniMessage().deserialize(
                        "<red>/skuishy <" +
                                "<click:run_command:'/skuishy info'><hover:show_text:'<green>/skuishy info'>version</hover></click>" +
                                " | " +
                                "<click:run_command:'/skuishy reload'><hover:show_text:'<green>/skuishy reload'>reload</hover></click>" +
                                " | " +
                                "<click:run_command:'/skuishy update'><hover:show_text:'<green>/skuishy update'>update</hover></click>" +
                                " | " +
                                "<click:run_command:'/skuishy version'><hover:show_text:'<green>/skuishy version'>version</hover></click>" +
                                ">"
                ));
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        completions.add("info");
        completions.add("reload");
        completions.add("update");
        completions.add("version");
        return completions;
    }

    public static Skuishy getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
}
