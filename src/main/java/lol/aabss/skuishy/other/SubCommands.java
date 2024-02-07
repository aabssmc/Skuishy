package lol.aabss.skuishy.other;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import lol.aabss.skuishy.Skuishy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lol.aabss.skuishy.Skuishy.instance;
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
        String msg = getString(sku, sk);
        // addons --
        List<String> msgs = new ArrayList<>();
        List<SkriptAddon> addonlist = new ArrayList<>(Skript.getAddons().stream().toList());
        // removes skuishy from addon list
        addonlist.remove(Skuishy.addon);
        if (!addonlist.isEmpty()){
            for (SkriptAddon addon : Skript.getAddons()) {
                // if the loop plugin is not skuishy add a message
                if (addon != instance.getAddonInstance()) {
                    msgs.add("    <click:open_url:" + addon.plugin.getDescription().getWebsite() + "><hover:show_text:'<gray>" + addon.plugin.getDescription().getWebsite() + "'><gray>" + addon.plugin.getDescription().getName()+": " + (addon.plugin.isEnabled() ? "<color:#40ff00>" : "<color:#ff0000>") + " " + addon.plugin.getDescription().getVersion() + " <gray>| <color:#40ff00>" + addon.plugin.getDescription().getAuthors() + "</hover></click>");
                }
            }
        }
        StringBuilder addons = new StringBuilder();
        for (String e : msgs){
            addons.append(e).append("\n");
        }
        // dependencies --
        List<String> deps = new ArrayList<>();
        for (SkriptAddon addon : Skript.getAddons()){
            for (String dep : addon.plugin.getDescription().getSoftDepend()){
                Plugin pl = Bukkit.getPluginManager().getPlugin(dep);
                if (pl != null) {
                    String msgg = "    <click:open_url:" + pl.getDescription().getWebsite() + "><hover:show_text:'<gray>" + pl.getDescription().getWebsite() + "'><gray>" + pl.getDescription().getName()+": " + (pl.isEnabled() ? "<color:#40ff00>" : "<color:#ff0000>") + " " + pl.getDescription().getVersion() + " <gray>| <color:#40ff00>" + pl.getDescription().getAuthors() + "</hover></click>";
                    if (!deps.contains(msgg)) {
                        deps.add(msgg);
                    }
                }
            }
        }
        for (String dep : Skript.getInstance().getDescription().getSoftDepend()){
            Plugin pl = Bukkit.getPluginManager().getPlugin(dep);
            if (pl != null){
                deps.add("    <click:open_url:"+pl.getDescription().getWebsite()+"><hover:show_text:'<gray>"+pl.getDescription().getWebsite()+"'><gray>"+pl.getDescription().getName()+": " + (pl.isEnabled() ? "<color:#40ff00>" : "<color:#ff0000>") + " " + pl.getDescription().getVersion() + " <gray>| <color:#40ff00>" + pl.getDescription().getAuthors() + "</hover></click>");
            }
        }
        StringBuilder dependencies = new StringBuilder();
        for (String e : deps){
            dependencies.append(e).append("\n");
        }
        // sending the message --
        if (dependencies.isEmpty()) {
            if (addons.isEmpty()){
                msg = msg + "    <color:#ff0000>N/A\n<gray>Dependencies:\n    <color:#ff0000>N/A";
            } else{
                msg = msg + addons + "<gray>Dependencies:\n    <color:#ff0000>N/A";
            }
        } else{
            if (addons.compareTo(new StringBuilder()) == 0){
                msg = msg + "    <color:#ff0000>N/A\n<gray>Dependencies:\n" + dependencies;
            } else{
                msg = msg + addons + "<gray>Dependencies:\n" + dependencies;
            }
        }
        sender.sendMessage(miniMessage().deserialize(msg+
                "\n<dark_gray>----------------"
                ));
    }

    @NotNull
    private static String getString(String sku, String sk) {
        String skuishyv = instance.getDescription().getVersion();
        String skriptv = Skript.getInstance().getDescription().getVersion();
        return "\n<dark_gray>-- <color:#40ff00>Skuishy <gray>Info: <dark_gray>--<reset>\n\n" +
                "<gray>Skuishy Version: <color:#40ff00>"+ skuishyv + (!Objects.equals(sku, skuishyv) ? " [Latest: "+ sku + "]" : "") +"<reset>\n" +
                "<gray>Server Version: <color:#40ff00>"+instance.getServer().getMinecraftVersion()+"<reset>\n" +
                "<gray>Server Implementation: <color:#40ff00>"+instance.getServer().getVersion()+"<reset>\n" +
                "<gray>Skript Version: <color:#40ff00>"+skriptv+(!Objects.equals(sk, skriptv) ? " [Latest: "+ sk + "]" : "") +"<reset>\n" +
                "<gray>Addons:\n";
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
        sender.sendMessage(miniMessage().deserialize("<color:#40ff00>This server is running Skuishy v" + instance.getDescription().getVersion() + " by aabss!"));
    }
}
