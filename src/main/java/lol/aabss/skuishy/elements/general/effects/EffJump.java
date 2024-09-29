package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import com.destroystokyo.paper.event.entity.EntityJumpEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import static org.bukkit.Statistic.JUMP;

@Name("Living Entity - Jump")
@Description("Makes a living entity jump")
@Examples({
        "make all players jump"
})
@Since("2.4")
public class EffJump extends EntityEffect<LivingEntity> {

    static {
        Skript.registerEffect(EffJump.class, "make %livingentities% jump");
    }

    @Override
    protected void execute(LivingEntity p, Event event) {
        Vector from = p.getVelocity();
        float f = p.getWorld().getBlockState(p.getLocation()).getBlock().getType() == Material.HONEY_BLOCK ? 0.5F : 1.0F;
        float g = p.getWorld().getBlockState(p.getVelocity().toLocation(p.getWorld())).getBlock().getType() == Material.HONEY_BLOCK ? 0.5F : 1.0F;
        float aa = (double) f == 1.0 ? g : f;
        double d = ((double) 0.42F * aa) + (p.hasPotionEffect(PotionEffectType.getById(8)) ? (double) (0.1F * (float) (p.getPotionEffect(PotionEffectType.getById(8)).getAmplifier() + 1)) : 0.0);
        Vector vec3d = p.getVelocity();
        p.setVelocity(new Vector(vec3d.getX(), d, vec3d.getZ()));
        if (p instanceof Player pp) {
            if (pp.isSprinting()) {
                float ff = pp.getLocation().getYaw() * 0.017453292F;
                pp.setVelocity(pp.getVelocity().add(new Vector(-Math.sin(ff) * 0.2F, 0.0, Math.cos(ff) * 0.2F)));
            }
            pp.incrementStatistic(JUMP, 1);
            if (pp.isSprinting()) {
                pp.setExhaustion(pp.getExhaustion() + 0.2F);
            } else {
                pp.setExhaustion(pp.getExhaustion() + 0.05F);
            }
        } else{
            float ff = p.getLocation().getYaw() * 0.017453292F;
            p.setVelocity(p.getVelocity().add(new Vector(-Math.sin(ff) * 0.2F, 0.0, Math.cos(ff) * 0.2F)));
        }
        Vector to = p.getVelocity();
        if (p instanceof Player pp) {
            new PlayerJumpEvent(pp, from.toLocation(pp.getWorld()), to.toLocation(pp.getWorld())).callEvent();
            new EntityJumpEvent(p).callEvent();
        } else{
            new EntityJumpEvent(p).callEvent();
        }
    }
}
