package lol.aabss.skuishy.other;

import org.bukkit.Bukkit;

import java.util.Objects;

public class GetVersion {
    public static String version(){
        return Bukkit.getBukkitVersion().split("-")[0];
    }

    public static String datapackVersion(){
        String v = version();
        if (Objects.equals(v, "1.17") || Objects.equals(v, "1.17.1")){
            return "7";
        }
        else if (Objects.equals(v, "1.18") || Objects.equals(v, "1.18.1")){
            return "8";
        }
        else if (Objects.equals(v, "1.18.2")){
            return "9";
        }
        else if (Objects.equals(v, "1.19") || Objects.equals(v, "1.19.2") || Objects.equals(v, "1.19.3")){
            return "10";
        }
        else if (Objects.equals(v, "1.19.4")){
            return "12";
        }
        else if (Objects.equals(v, "1.20") || Objects.equals(v, "1.20.1")){
            return "15";
        }
        else if (Objects.equals(v, "1.20.2")){
            return "18";
        }
        else if (Objects.equals(v, "1.20.3") || Objects.equals(v, "1.20.4")){
            return "26";
        }
        else return "0";
    }
}
