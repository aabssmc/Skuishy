package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lol.aabss.skuishy.events.ShieldBreak;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Skuishy extends JavaPlugin implements CommandExecutor {

    private static Skuishy instance;
    private ProtocolManager protocolManager;
    private SkriptAddon addon;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ShieldBreak(), this);
        Metrics metrics = new Metrics(this, 20162);
        instance = this;
        try {
            addon = Skript.registerAddon(this)
                    .loadClasses("lol.aabss.skuishy", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (instance.getServer().getPluginManager().isPluginEnabled("ProtocolLib")){
            protocolManager = ProtocolLibrary.getProtocolManager();
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
