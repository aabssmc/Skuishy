package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.EnderDragon;
import org.jetbrains.annotations.Nullable;

@Name("Ender Dragon - Phase")
@Description("Gets/sets the phase of an ender dragon.")
@Examples({
        "set phase of {_dragon} to strafing"
})
@Since("2.8")
public class ExprEnderDragonPhase extends EntityExpression<EnderDragon, EnderDragon.Phase> {

    static {
        register(ExprEnderDragonPhase.class, EnderDragon.Phase.class, "[ender] dragon phase", "entities");
    }

    @Override
    public EnderDragon.Phase get(EnderDragon enderDragon) {
        return enderDragon.getPhase();
    }

    @Override
    public void change(EnderDragon enderDragon, EnderDragon.@Nullable Phase phase, Changer.ChangeMode mode) {
        if (phase != null && mode == Changer.ChangeMode.SET) {
            enderDragon.setPhase(phase);
        }
    }
}
