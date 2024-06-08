package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.bstats.bukkit.Metrics;
import ch.njol.skript.bstats.charts.SimplePie;
import lol.aabss.skuishy.other.UpdateChecker;
import lol.aabss.skuishy.other.blueprints.Blueprint;
import lol.aabss.skuishy.other.blueprints.BlueprintUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static lol.aabss.skuishy.other.GetVersion.latestVersion;
import static lol.aabss.skuishy.other.SubCommands.*;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

@SuppressWarnings("deprecation")
public class Skuishy extends JavaPlugin implements TabExecutor {

    public static Skuishy instance;
    public static SkriptAddon addon;
    public static long start;
    public static PermissionAttachment last_permission_attachment;
    public static Permission last_permission;
    public static Blueprint last_blueprint;
    public static boolean dh = false;
    public static boolean vc = false;
    public static boolean vu = false;
    public static String latest_version;
    public static String data_path;
    private static String prefix = net.md_5.bungee.api.ChatColor.of("#40ff00") + "[Skuishy] " + ChatColor.RESET;
    private static Metrics metrics;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        BlueprintUtils.loadJson();
        getServer().getPluginManager().registerEvents(new UpdateChecker(), this);
        getServer().getPluginCommand("skuishy").setExecutor(this);
        getServer().getPluginCommand("skuishy").setTabCompleter(this);
        metrics = new Metrics(this, 20162);
        try {
            addon = Skript.registerAddon(this);
            addon.setLanguageFileDirectory("lang");
            registerPluginElements("DecentHolograms", "DecentHolograms");
            registerPluginElements("Vivecraft-Spigot-Extensions", "Vivecraft");
            registerPluginElements("Vulcan", "Vulcan");
            registerElements("General", "General");
            registerElements("Note", "Notes");
            registerElements("Permission", "Permissions");
            registerElements("Persistence", "Persistence");
            registerElements("Plugin", "Plugins");
            registerElements("Skin", "Skins");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        metrics.addCustomChart(new SimplePie("skript_version", () -> Skript.getVersion().toString()));
        start = System.currentTimeMillis()/50;
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Skuishy has been enabled!");
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            latest_version = latestVersion();
            if (getConfig().getBoolean("version-check-msg")) Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.YELLOW + "Got latest version.");
        }, 0L, 144000L);
        data_path = this.getDataFolder().getAbsolutePath();
    }

    @Override
    public void onDisable(){
        getServer().getScheduler().cancelTasks(this);
        if (getConfig().getBoolean("auto-update", false)){
            if (new File(getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).delete()) {
                UpdateChecker.update();
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            sender.sendMessage(miniMessage().deserialize(
                    "<red>/skuishy <" +
                            "<click:run_command:'/skuishy info'><hover:show_text:'<green>/skuishy info'>info</hover></click>" +
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
                case "info" -> cmdInfo(sender, (args.length >= 2 ? args[1] : null));
                case "reload" -> cmdReload(sender);
                case "update" -> cmdUpdate(sender);
                case "version" -> cmdVersion(sender);
                default -> sender.sendMessage(miniMessage().deserialize(
                        "<red>/skuishy <" +
                                "<click:run_command:'/skuishy info'><hover:show_text:'<green>/skuishy info'>info</hover></click>" +
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
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            if ("info".startsWith(args[0].toLowerCase())) completions.add("info");
            if ("reload".startsWith(args[0].toLowerCase())) completions.add("reload");
            if ("update".startsWith(args[0].toLowerCase())) completions.add("update");
            if ("version".startsWith(args[0].toLowerCase())) completions.add("version");
            return completions;
        } else if (args.length == 2){
             if (args[0].equalsIgnoreCase("info")) {
                List<String> completions = new ArrayList<>();
                for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
                    if (p.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                        completions.add(p.getName());
                    }
                }
                return completions;
            }
        }
        return null;
    }

    public static Skuishy getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    public void registerPluginElements(String pluginName, String name) throws IOException {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled(pluginName)) {
            addon.loadClasses("lol.aabss.skuishy.elements."+name.toLowerCase());
            vu = true;
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + name+" elements loaded!");
        } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + name+" elements not loaded.");
        metrics.addCustomChart(new SimplePie(name, () -> Boolean.toString(dh)));
    }

    public void registerElements(String name, String plural) throws IOException {
        if (getConfig().getBoolean(name.toLowerCase()+"-elements", true)){
            addon.loadClasses("lol.aabss.skuishy.elements."+plural.toLowerCase());
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + name+" elements loaded!");
        } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + name+" elements not loaded!");
    }

    @Nullable
    public static NamespacedKey namespacedKeyFromObject(Object object){
        if (object instanceof String string) {
            String[] split = string.split(":");
            if (split.length > 1) {
                String namespace = split[0];
                String key = split[1];
                return new NamespacedKey(split[0], split[1]);
            }
            return null;
        } else if (object instanceof NamespacedKey){
            return (NamespacedKey) object;
        }
        return null;
    }
}
