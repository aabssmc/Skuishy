package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Skuishy extends JavaPlugin {

    private static Skuishy instance;
    private SkriptAddon addon;

    public void onEnable() {
        int pluginId = 20162;
        Metrics metrics = new Metrics(this, pluginId);
        instance = this;
        try {
            addon = Skript.registerAddon(this)
                    .loadClasses("lol.aabss.skuishy", "elements");
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
