package lol.aabss.skuishy.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.inventory.PlayerInventory;

public class CustomEvents implements Listener {

    @EventHandler
    public void onShieldBreak(PlayerStatisticIncrementEvent e) {
        if (e.getStatistic() == Statistic.DAMAGE_BLOCKED_BY_SHIELD) {
            PlayerInventory i = e.getPlayer().getInventory();
            Material o = i.getItemInOffHand().getType();
            Material m = i.getItemInMainHand().getType();
            if (m == Material.SHIELD) {
                if (e.getPlayer().getCooldown(m) > 0){
                    Bukkit.getServer().getPluginManager().callEvent(new ShieldBreakEvent(e.getPlayer()));
                }
            } else if (o == Material.SHIELD) {
                if (e.getPlayer().getCooldown(o) > 0){
                    Bukkit.getServer().getPluginManager().callEvent(new ShieldBreakEvent(e.getPlayer()));
                }
            }
        }
    }
}