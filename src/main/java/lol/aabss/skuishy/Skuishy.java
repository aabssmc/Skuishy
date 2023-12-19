package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lol.aabss.skuishy.events.CustomEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Skuishy extends JavaPlugin implements CommandExecutor {

    public static Skuishy instance;
    private SkriptAddon addon;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CustomEvents(), this);
        Metrics metrics = new Metrics(this, 20162);

        instance = this;
        try {
            addon = Skript.registerAddon(this);
            addon.setLanguageFileDirectory("lang");
            addon.loadClasses("lol.aabss.skuishy", "elements");
            if (Bukkit.getServer().getPluginManager().getPlugin("DecentHolograms") != null){
                getLogger().info("DecentHolograms found! Enabling DecentHolograms elements...");
                addon.loadClasses("lol.aabss.skuishy", "decentholograms");
                getLogger().info("DecentHolograms elements loaded!");
            } else getLogger().info("DecentHolograms not found, skipping!");

            if (Bukkit.getServer().getPluginManager().getPlugin("Vivecraft-Spigot-Extensions") != null) {
                getLogger().info("Vivecraft-Spigot-Extensions found! Enabling Vivecraft-Spigot-Extensions elements...");
                addon.loadClasses("lol.aabss.skuishy", "vivecraft");
                getLogger().info("Vivecraft-Spigot-Extensions elements loaded!");
            } else getLogger().info("Vivecraft-Spigot-Extensions not found, skipping!");

            if (Bukkit.getServer().getPluginManager().getPlugin("Vulcan") != null) {
                getLogger().info("Vulcan found! Enabling Vulcan elements...");
                addon.loadClasses("lol.aabss.skuishy", "vulcan");
                getLogger().info("Vulcan elements loaded!");
            } else getLogger().info("Vulcan not found, skipping!");
        } catch (IOException e) {
            e.printStackTrace();
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
