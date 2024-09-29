package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.LightningStrike;

@Name("Lightning - Is Effect")
@Description("True if the lightning strike is just an effect that does no damage.")
@Examples({
        "if {_lightning} is effect:"
})
@Since("2.8")
public class CondIsLightningEffect extends EntityCondition<LightningStrike> {

    static {
        register(CondIsLightningEffect.class, "[lightning] [a[n]] effect", "entities");
    }

    @Override
    protected boolean run(LightningStrike lightningStrike) {
        return lightningStrike.isEffect();
    }
}
