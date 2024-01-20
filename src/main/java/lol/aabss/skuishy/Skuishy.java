package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lol.aabss.skuishy.events.CustomEvents;
import lol.aabss.skuishy.hooks.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Skuishy extends JavaPlugin{

    public static Skuishy instance;
    private SkriptAddon addon;
    public static long start;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CustomEvents(), this);
        Metrics metrics = new Metrics(this, 20162);
        instance = this;
        start = System.currentTimeMillis()/50;
        try {
            addon = Skript.registerAddon(this);
            addon.setLanguageFileDirectory("lang");
            addon.loadClasses("lol.aabss.skuishy.elements");
            if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
                getLogger().info("DecentHolograms found! Enabling DecentHolograms elements...");
                addon.loadClasses("lol.aabss.skuishy.hooks.decentholograms");
                getLogger().info("DecentHolograms elements loaded!");
            } else getLogger().info("DecentHolograms not found, skipping!");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
                getLogger().info("Vivecraft-Spigot-Extensions found! Enabling Vivecraft-Spigot-Extensions elements...");
                addon.loadClasses("lol.aabss.skuishy.hooks.vivecraft");
                getLogger().info("Vivecraft-Spigot-Extensions elements loaded!");
            } else getLogger().info("Vivecraft-Spigot-Extensions not found, skipping!");

            if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
                getLogger().info("Vulcan found! Enabling Vulcan elements...");
                addon.loadClasses("lol.aabss.skuishy.hooks.vulcan");
                getLogger().info("Vulcan elements loaded!");
            } else getLogger().info("Vulcan not found, skipping!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getLogger().info("Skuishy has been enabled!");
    }

    public static Skuishy getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
