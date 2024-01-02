package lol.aabss.skuishy.events;

import io.papermc.paper.event.player.PlayerItemCooldownEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CustomEvents implements Listener {

    @EventHandler
    public void onShieldBreak(PlayerItemCooldownEvent e) {
        if (e.getType() == Material.SHIELD) {
            if (e.getCooldown() > 0){
                new ShieldBreakEvent(e.getPlayer()).callEvent();
            }
        }
    }

    @EventHandler
    public void onHeadRotate(PlayerMoveEvent e) {
        if (e.getFrom().getX() == e.getTo().getX() && e.getFrom().getY() == e.getTo().getY() && e.getFrom().getZ() == e.getTo().getZ()){
            if (e.getFrom().getYaw() != e.getTo().getYaw() || e.getFrom().getPitch() != e.getTo().getPitch()){
                Bukkit.getServer().getPluginManager().callEvent(new HeadRotationEvent(e.getPlayer(), e.getFrom(), e.getTo()));
            }
        }

    }
}