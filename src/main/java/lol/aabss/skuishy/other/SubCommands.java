package lol.aabss.skuishy.other;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lol.aabss.skuishy.Skuishy.*;
import static lol.aabss.skuishy.other.UpdateChecker.*;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class SubCommands {
    public static void cmdInfo(CommandSender sender){
        if (!sender.hasPermission("skuishy.command.info")){
            sender.sendMessage(miniMessage().deserialize(instance.getConfig().getString("permission-message")));
            return;
        }
        String sk = latestSkriptVersion();
        String sku = latestVersion();
        String skuishyv = instance.getPluginMeta().getVersion();
        String skriptv = Skript.getInstance().getPluginMeta().getVersion();
        String msg = "\n<dark_gray>-- <color:#40ff00>Skuishy <gray>Info: <dark_gray>--<reset>\n\n" +
                "<gray>Skuishy Version: <color:#40ff00>"+ skuishyv + (!Objects.equals(sku, skuishyv) ? " [Latest: "+ sku + "]" : "") +"<reset>\n" +
                "<gray>Server Version: <color:#40ff00>"+instance.getServer().getMinecraftVersion()+"<reset>\n" +
                "<gray>Server Implementation: <color:#40ff00>"+instance.getServer().getVersion()+"<reset>\n" +
                "<gray>Skript Version: <color:#40ff00>"+skriptv+(!Objects.equals(sk, skriptv) ? " [Latest: "+ sk + "]" : "") +"<reset>\n" +
                "<gray>Addons:\n";
        // addons --
        List<String> msgs = new ArrayList<>();
        for (SkriptAddon addon : Skript.getAddons()){
            msgs.add("    <click:open_url:"+addon.plugin.getPluginMeta().getWebsite()+"><hover:show_text:'<gray>"+addon.plugin.getPluginMeta().getWebsite()+"'><gray>"+addon.plugin.getPluginMeta().getName()+": <color:#40ff00>"+ addon.plugin.getPluginMeta().getVersion() + " <gray>| <color:#40ff00>" + addon.plugin.getPluginMeta().getAuthors() + "</hover></click>");
        }
        StringBuilder addons = new StringBuilder();
        for (String e : msgs){
            addons.append(e).append("\n");
        }
        // dependencies --
        List<String> deps = new ArrayList<>();
        for (String dep : Skript.getInstance().getPluginMeta().getPluginSoftDependencies()){
            Plugin pl = Bukkit.getPluginManager().getPlugin(dep);
            if (pl != null){
                deps.add("    <click:open_url:"+pl.getPluginMeta().getWebsite()+"><hover:show_text:'<gray>"+pl.getPluginMeta().getWebsite()+"'><gray>"+pl.getPluginMeta().getName()+": <color:#40ff00>"+ pl.getPluginMeta().getVersion() + " <gray>| <color:#40ff00>" + pl.getPluginMeta().getAuthors() + "</hover></click>");
            }
        }
        StringBuilder dependencies = new StringBuilder();
        for (String e : deps){
            dependencies.append(e).append("\n");
        }
        // sending the message --
        if (dependencies.compareTo(new StringBuilder()) == 0) {
            if (addons.compareTo(new StringBuilder()) == 0){
                msg = msg + "    none\n<gray>Dependencies:\n    none";
            } else{
                msg = msg + addons + "<gray>Dependencies:\n    none";
            }
        } else{
            if (addons.compareTo(new StringBuilder()) == 0){
                msg = msg + "    none\n<gray>Dependencies:\n" + dependencies;
            } else{
                msg = msg + addons + "<gray>Dependencies:\n" + dependencies;
            }
        }
        sender.sendMessage(miniMessage().deserialize(msg+
                "\n<gray>Decent Holograms: "+(dh ? "<color:#40ff00>true" : "<color:#ff0000>false")+"<reset>\n"+
                "<gray>Vivecraft: "+(vc ? "<color:#40ff00>true" : "<color:#ff0000>false")+"<reset>\n"+
                "<gray>Vulcan: "+(vu ? "<color:#40ff00>true" : "<color:#ff0000>false")+"<reset>\n"+
                "<dark_gray>----------------"
                ));
    }

    public static void cmdReload(CommandSender sender){
        if (!sender.hasPermission("skuishy.command.reload")){
            sender.sendMessage(miniMessage().deserialize(instance.getConfig().getString("permission-message")));
            return;
        }
        instance.reloadConfig();
        instance.saveConfig();
        sender.sendMessage(miniMessage().deserialize("<color:#40ff00>Config reloaded!"));
    }

    public static void cmdUpdate(CommandSender sender){
        if (!sender.hasPermission("skuishy.command.update")){
            sender.sendMessage(miniMessage().deserialize(instance.getConfig().getString("permission-message")));
            return;
        }
        if (!updateCheck(sender)){
            sender.sendMessage(miniMessage().deserialize("<color:#40ff00>You are up to date!"));
        }
    }

    public static void cmdVersion(CommandSender sender){
        sender.sendMessage(miniMessage().deserialize("<color:#40ff00>This server is running Skuishy v" + instance.getPluginMeta().getVersion() + " by aabss!"));
    }
}
