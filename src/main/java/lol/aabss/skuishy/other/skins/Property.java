package lol.aabss.skuishy.other.skins;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.entity.Player;

public class Property {
    public static ProfileProperty getProfileProperties(Player p) {
        PlayerProfile playerProfile = p.getPlayerProfile();
        ProfileProperty prop = null;
        for (ProfileProperty property : playerProfile.getProperties()) {
            if (property.getName().equals("textures")) {
                prop = property;
            }
        }
        return prop;
    }
}