package lol.aabss.skuishy;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lol.aabss.skuishy.events.ShieldBreak;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Skuishy extends JavaPlugin implements CommandExecutor {

    private static Skuishy instance;
    private SkriptAddon addon;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ShieldBreak(), this);
        getCommand("skuishy").setExecutor(this);
        Metrics metrics = new Metrics(this, 20162);
        instance = this;
        try {
            addon = Skript.registerAddon(this)
                    .loadClasses("lol.aabss.skuishy", "elements");
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveDefaultConfig();
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


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0){
            sender.sendMessage(Component.text("/skuishy <reload|version>", NamedTextColor.RED));
        }
        else if (args[0].equals("reload") || args[0].equals("r")){
            if (sender.hasPermission("skuishy.reload")){
                reloadConfig();
                sender.sendMessage(Component.text("Reload successful!", NamedTextColor.GREEN));
            }
            else{
                sender.sendMessage(Component.text("No permission.", NamedTextColor.RED));
            }
        }
        else if (args[0].equals("version") || args[0].equals("ver")){
            reloadConfig();
            sender.sendMessage(Component.text("This server is running Skuishy v" + this.getDescription().getVersion() + "!", NamedTextColor.YELLOW));
        }
        else{
            sender.sendMessage(Component.text("/skuishy <reload|version>", NamedTextColor.RED));
        }
        return super.onCommand(sender, command, label, args);
    }
}
