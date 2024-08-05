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
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public static String latest_version;
    public static String data_path;
    public static HashMap<String, Boolean> element_map = new HashMap<>();
    public static final String prefix = net.md_5.bungee.api.ChatColor.of("#00ff00") + "[Skuishy] " + ChatColor.RESET;
    private static Metrics metrics;
    public static final boolean skript_reflect_supported = Skript.classExists("com.btk5h.skriptmirror.ObjectWrapper");

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
            Logger.exception(e);
        }
        metrics.addCustomChart(new SimplePie("skript_version", () -> Skript.getVersion().toString()));
        start = System.currentTimeMillis()/50;
        Logger.success("Skuishy has been enabled!");
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            latest_version = latestVersion();
            if (getConfig().getBoolean("version-check-msg")) Logger.warn("Got latest version."); // not a warn just want yellow
        }, 0L, 144000L);
        data_path = this.getDataFolder().getAbsolutePath();
    }

    @Override
    public void onDisable(){
        getServer().getScheduler().cancelTasks(this);
        if (getConfig().getBoolean("auto-update", false)){
            if (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).delete()) {
                UpdateChecker.update();
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            sender.sendMessage(miniMessage().deserialize(
                    "<red>/skuishy <" +
                            "<click:run_command:'/skuishy dependencies'><hover:show_text:'<green>/skuishy dependencies'>dependencies</hover></click>" +
                            " | " +
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
                case "dependencies" -> cmdDependencies(sender);
                case "info" -> cmdInfo(sender, (args.length >= 2 ? args[1] : null));
                case "reload" -> cmdReload(sender);
                case "update" -> cmdUpdate(sender);
                case "version" -> cmdVersion(sender);
                default -> sender.sendMessage(miniMessage().deserialize(
                        "<red>/skuishy <" +
                                "<click:run_command:'/skuishy dependencies'><hover:show_text:'<green>/skuishy dependencies'>dependencies</hover></click>" +
                                " | " +
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
            if ("dependencies".startsWith(args[0].toLowerCase())) completions.add("dependencies");
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

    public void registerPluginElements(String pluginName, String name) throws IOException {
        element_map.put(name, false);
        if (Bukkit.getServer().getPluginManager().isPluginEnabled(pluginName)) {
            addon.loadClasses("lol.aabss.skuishy.elements."+name.toLowerCase());
            Logger.success(name+" elements loaded.");
            element_map.put(name, true);
        } else Logger.error(name+" elements not loaded.");
        metrics.addCustomChart(new SimplePie(name, () -> Boolean.toString(element_map.get(name))));
    }

    public void registerElements(String name, String plural) throws IOException {
        element_map.put(plural, false);
        if (getConfig().getBoolean(name.toLowerCase()+"-elements", true)){
            addon.loadClasses("lol.aabss.skuishy.elements."+plural.toLowerCase());
            Logger.success(name+" elements loaded.");
            element_map.put(plural, true);
        } else Logger.error(name+" elements not loaded.");
    }

    @Nullable
    public static NamespacedKey namespacedKeyFromObject(Object object){
        if (object instanceof String string) {
            String[] split = string.split(":");
            if (split.length > 1) {
                String namespace = split[0];
                String key = split[1];
                return new NamespacedKey(split[0], split[1]);
            } else {
                return new NamespacedKey(Skuishy.instance, string);
            }
        } else if (object instanceof NamespacedKey){
            return (NamespacedKey) object;
        }
        return null;
    }

    public static int index(int index) {
        boolean skriptIndex = instance.getConfig().getBoolean("prefer-skript-index", false);
        return (skriptIndex ? index+1 : index);
    }

    public static class Logger{

        public static void success(String message){
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GREEN + message);
        }

        public static void log(String message){
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.WHITE + message);
        }

        public static void warn(String message){
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.YELLOW + message);
        }

        public static void error(String message){
            Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.RED + message);
        }

        public static void exception(Throwable throwable) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.isOp()) {
                    p.sendMessage(prefix + ChatColor.RED+"Something went wrong! See the problem in console.");
                }
            }
            Bukkit.getConsoleSender().sendMessage(prefix+ChatColor.DARK_RED+
                    "An unexpected error occurred! See the stacktrace below:"
            );
            Bukkit.getConsoleSender().sendMessage(
                    prefix+ChatColor.RED+throwable+"\n"+ChatColor.DARK_RED
            );
            for (StackTraceElement element : throwable.getStackTrace()) {
                Bukkit.getConsoleSender().sendMessage(
                        prefix+"| "+ChatColor.RED+element
                );
            }
        }

        public static void exception(Class<? extends Throwable> exceptionClass, String message) {
            for (Constructor<?> constructor : exceptionClass.getDeclaredConstructors()) {
                if (constructor.getParameterCount() == 1 && constructor.getParameters()[0].getType().equals(String.class)) {
                    try {
                        exception((Throwable) constructor.newInstance(message));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        exception(e);
                    }
                }
            }
        }

        public static void exception(String message) {
            exception(new RuntimeException(message));
        }
    }
}
