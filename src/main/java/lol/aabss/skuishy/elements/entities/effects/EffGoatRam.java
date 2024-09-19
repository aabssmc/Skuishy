package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

@Name("Goat - Ram")
@Description("Makes a goat ram into a living entity.")
@Examples({
        "make {_goat} ram into player"
})
@Since("2.8")
public class EffGoatRam extends EntityEffect<Goat> {

    static {
        Skript.registerEffect(EffJump.class, "make %livingentities% ram into %livingentities%");
    }

    @Override
    protected void execute(Goat goat, Event event) {
        if (exprs.length >= 2 && exprs[1].getSingle(event) != null) {
            LivingEntity entity = (LivingEntity) exprs[1].getSingle(event);
            if (entity == null) return;
            goat.ram(entity);
        }
    }
}
