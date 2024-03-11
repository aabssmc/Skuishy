package lol.aabss.skuishy.other;

import lol.aabss.skuishy.Skuishy;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import static lol.aabss.skuishy.elements.general.effects.EffFakeSign.FAKE_SIGNS;

public class Events implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e){
        if (FAKE_SIGNS.containsKey(e.getBlock())){
            if (e.isCancelled()){
                e.setCancelled(false);
            }
            Bukkit.getScheduler().runTaskLater(Skuishy.instance, () -> {
                e.getBlock().setType(FAKE_SIGNS.get(e.getBlock()).getOldBlock().getMaterial());
                e.getBlock().setBlockData(FAKE_SIGNS.get(e.getBlock()).getOldBlock());
                FAKE_SIGNS.remove(e.getBlock());
            }, 2L);
        }
    }
}
