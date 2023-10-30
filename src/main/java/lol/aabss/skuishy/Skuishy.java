package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Skuishy extends JavaPlugin {

    private static Skuishy instance;
    private SkriptAddon addon;

    public void onEnable() {
        Metrics metrics = new Metrics(this, 20162);
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
