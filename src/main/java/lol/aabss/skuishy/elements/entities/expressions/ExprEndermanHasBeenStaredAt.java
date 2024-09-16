package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Enderman;
import org.jetbrains.annotations.Nullable;

@Name("Enderman - Been Stared At")
@Description("Gets/sets the has been stared at state of an enderman.")
@Examples({
        "set been stared at state of {_enderman} to true"
})
@Since("2.8")
public class ExprEndermanHasBeenStaredAt extends EntityExpression<Enderman, Boolean> {

    static {
        register(ExprEndermanHasBeenStaredAt.class, Boolean.class, "[enderman] been stared at [state|mode]", "entities");
    }

    @Override
    public Boolean get(Enderman enderman) {
        return enderman.hasBeenStaredAt();
    }

    @Override
    public void change(Enderman enderman, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            enderman.setHasBeenStaredAt(aBoolean);
        }
    }
}