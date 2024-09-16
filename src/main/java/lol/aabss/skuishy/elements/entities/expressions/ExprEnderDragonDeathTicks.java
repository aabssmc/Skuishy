package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.EnderDragon;

@Name("Ender Dragon - Death Animation Ticks")
@Description("Gets/sets the phase of an ender dragon.")
@Examples({
        "set phase of {_dragon} to strafing"
})
@Since("2.8")
public class ExprEnderDragonDeathTicks extends EntityExpression<EnderDragon, Integer> {

    static {
        register(ExprEnderDragonDeathTicks.class, Integer.class, "[[ender] dragon] death [animation] ticks", "entities");
    }

    @Override
    public Integer get(EnderDragon enderDragon) {
        return enderDragon.getDeathAnimationTicks();
    }
}
