package lol.aabss.skuishy.elements.vulcan;

import ch.njol.skript.Skript;
import me.frep.vulcan.api.VulcanAPI$Factory;
import org.bukkit.Bukkit;

public class VulcanHook {
    public static boolean vulcanEnabled() {
        return
                Bukkit.getPluginManager().isPluginEnabled("Vulcan") &&
                        Skript.classExists("me.frep.vulcan.api.VulcanAPI") &&
                        VulcanAPI$Factory.getApi() != null;
    }
}
