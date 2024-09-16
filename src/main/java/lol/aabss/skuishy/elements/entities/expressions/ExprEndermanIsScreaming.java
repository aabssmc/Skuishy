package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Enderman;
import org.jetbrains.annotations.Nullable;

@Name("Enderman - Is Screaming")
@Description("Gets/sets the is screaming state of an enderman.")
@Examples({
        "set screaming state of {_enderman} to true"
})
@Since("2.8")
public class ExprEndermanIsScreaming extends EntityExpression<Enderman, Boolean> {

    static {
        register(ExprEndermanIsScreaming.class, Boolean.class, "[enderman] screaming [state|mode]", "entities");
    }

    @Override
    public Boolean get(Enderman enderman) {
        return enderman.isScreaming();
    }

    @Override
    public void change(Enderman enderman, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            enderman.setScreaming(aBoolean);
        }
    }
}