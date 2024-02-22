package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.util.Version;
import lol.aabss.skuishy.other.Metrics;
import lol.aabss.skuishy.other.UpdateChecker;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
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
            addon.loadClasses("lol.aabss.skuishy.elements.general");
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
                getLogger().info("DecentHolograms found! Enabling DecentHolograms elements...");
                String v = Bukkit.getPluginManager().getPlugin("DecentHolograms").getDescription().getVersion();
                Plugin decentHolograms = Bukkit.getPluginManager().getPlugin("DecentHolograms");
                Version decentHoloVersion = new Version(decentHolograms.getPluginMeta().getVersion());
                if (decentHoloVersion.compareTo(2,8,6) < 0) {
                    Bukkit.getConsoleSender().sendMessage(NamedTextColor.YELLOW + "You must be running decent holograms version 2.8.6 as the minimum");
                } else{
                    Bukkit.getConsoleSender().sendMessage(NamedTextColor.GREEN + "DecentHolograms elements loaded!");
                    dh = true;
                }
            } else Bukkit.getConsoleSender().sendMessage(NamedTextColor.RED + "DecentHolograms elements not loaded.");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
                addon.loadClasses("lol.aabss.skuishy.elements.vivecraft");
                vc = true;
                Bukkit.getConsoleSender().sendMessage(NamedTextColor.GREEN + "Vivecraft elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(NamedTextColor.RED + "Vivecraft elements not loaded.");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
                addon.loadClasses("lol.aabss.skuishy.elements.vulcan");
                vu = true;
                Bukkit.getConsoleSender().sendMessage(NamedTextColor.GREEN + "Vulcan elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(NamedTextColor.RED + "Vulcan elements not loaded.");

            if (getConfig().getBoolean("note-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.notes");
                Bukkit.getConsoleSender().sendMessage(NamedTextColor.GREEN + "Note elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(NamedTextColor.RED + "Note elements not loaded.");

            if (getConfig().getBoolean("permission-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.permissions");
                Bukkit.getConsoleSender().sendMessage(NamedTextColor.GREEN + "Permission elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(NamedTextColor.RED + "Permission elements not loaded.");

            if (getConfig().getBoolean("plugin-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.plugins");
                Bukkit.getConsoleSender().sendMessage(NamedTextColor.GREEN + "Plugin elements loaded!");
            } else Bukkit.getConsoleSender().sendMessage(NamedTextColor.RED + "Plugin elements not loaded.");

            if (getConfig().getBoolean("skin-elements")){
                addon.loadClasses("lol.aabss.skuishy.elements.skins");
                getLogger().info("§aSkin elements loaded!");
            } else getLogger().warning("§cSkin elements not loaded!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        metrics.addCustomChart(new Metrics.SimplePie("decentholograms", () -> dh ? "true" : "false"));
        metrics.addCustomChart(new Metrics.SimplePie("vivecraft", () -> vc ? "true" : "false"));
        metrics.addCustomChart(new Metrics.SimplePie("vulcan", () -> vu ? "true" : "false"));
        metrics.addCustomChart(new Metrics.SimplePie("skript_version", () -> Skript.getVersion().toString()));
        start = System.currentTimeMillis()/50;
        getLogger().info("Skuishy has been enabled!");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            latest_version = latestVersion();
            latest_skript_version = latestSkriptVersion();
            if (getConfig().getBoolean("version-check-msg")) getLogger().info("Got latest version.");
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
            List<String> completions = new ArrayList<>();
            for (Plugin p : Bukkit.getPluginManager().getPlugins()){
                if (p.getName().toLowerCase().startsWith(args[1].toLowerCase())) {
                    completions.add(p.getName());
                }
            }
            return completions;
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
