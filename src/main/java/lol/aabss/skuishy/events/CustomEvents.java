package lol.aabss.skuishy.events;

import io.papermc.paper.event.player.PlayerItemCooldownEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomEvents implements Listener {

    @EventHandler
    public void onShieldBreak(PlayerItemCooldownEvent e) {
        if (e.getType() == Material.SHIELD) {
            if (e.getCooldown() > 0){
                new ShieldBreakEvent(e.getPlayer()).callEvent();
            }
        }
    }
}