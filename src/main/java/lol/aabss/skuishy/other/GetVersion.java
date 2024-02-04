package lol.aabss.skuishy.other;

import org.bukkit.Bukkit;

public class GetVersion {
    public static String version(){
        return Bukkit.getBukkitVersion().split("-")[0];
    }

    public static int datapackVersion(){
        String v = version();
        return switch (v){
            case "1.17", "1.17.1" -> 7;
            case "1.18", "1.18.1" -> 8;
            case "1.18.2" -> 9;
            case "1.19", "1.19.1", "1.19.2", "1.19.3" -> 10;
            case "1.19.4" -> 12;
            case "1.20", "1.20.1" -> 15;
            case "1.20.2" -> 18;
            case "1.20.3", "1.20.4" -> 26;
            default -> 0;
        };
    }
}
