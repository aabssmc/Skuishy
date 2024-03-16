package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.util.Version;
import lol.aabss.skuishy.other.Events;
import lol.aabss.skuishy.other.Metrics;
import lol.aabss.skuishy.other.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
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

public class Skuishy extends JavaPlugin implements TabExecutor {

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

    @SuppressWarnings("deprecation")
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new UpdateChecker(), this);
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginCommand("skuishy").setExecutor(this);
        getServer().getPluginCommand("skuishy").setTabCompleter(this);
        Metrics metrics = new Metrics(this, 20162);
        instance = this;
        String prefix = net.md_5.bungee.api.ChatColor.of("#40ff00") + "[Skuishy] " + ChatColor.RESET;
        try {
            addon = Skript.registerAddon(this);
            addon.setLanguageFileDirectory("lang");
            addon.loadClasses("lol.aabss.skuishy.elements.general");
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "DecentHolograms found! Enabling DecentHolograms elements...");
                Plugin decentHolograms = Bukkit.getPluginManager().getPlugin("DecentHolograms");
                Version decentHoloVersion = new Version(decentHolograms.getDescription().getVersion());
                if (decentHoloVersion.compareTo(2,8,6) < 0) {
                    Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.YELLOW + "You must be running decent holograms version 2.8.6 as the minimum");
                } else{
                    Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "DecentHolograms elements loaded!");
                    addon.loadClasses("lol.aabss.skuishy.elements.decentholograms");
                    dh = true;
                }
            } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "DecentHolograms elements not loaded.");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
                addon.loadClasses("lol.aabss.skuishy.elements.vivecraft");
                vc = true;
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Vivecraft elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Vivecraft elements not loaded.");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
                addon.loadClasses("lol.aabss.skuishy.elements.vulcan");
                vu = true;
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Vulcan elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Vulcan elements not loaded.");

            if (getConfig().getBoolean("note-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.notes");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Note elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Note elements not loaded.");

            if (getConfig().getBoolean("permission-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.permissions");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Permission elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Permission elements not loaded.");

            if (getConfig().getBoolean("plugin-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.plugins");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Plugin elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Plugin elements not loaded.");

            if (getConfig().getBoolean("skin-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.skins");
                Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Skin elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + "Skin elements not loaded!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        metrics.addCustomChart(new Metrics.SimplePie("decentholograms", () -> Boolean.toString(dh)));
        metrics.addCustomChart(new Metrics.SimplePie("vivecraft", () -> Boolean.toString(vc)));
        metrics.addCustomChart(new Metrics.SimplePie("vulcan", () -> Boolean.toString(vu)));
        metrics.addCustomChart(new Metrics.SimplePie("skript_version", () -> Skript.getVersion().toString()));
        start = System.currentTimeMillis()/50;
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + "Skuishy has been enabled!");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            latest_version = latestVersion();
            latest_skript_version = latestSkriptVersion();
            if (getConfig().getBoolean("version-check-msg")) Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.YELLOW + "Got latest version.");
        }, 0L, 144000L);
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
}
