package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Turtle;
import org.jetbrains.annotations.Nullable;

@Name("Turtle - Has Egg")
@Description("Gets/Sets the has egg state of a turtle.")
@Examples({
        "set egg state of {_turtle} to false"
})
@Since("2.8")
public class ExprTurtleEgg extends EntityExpression<Turtle, Boolean> {

    static {
        register(ExprTurtleEgg.class, Boolean.class, "[turtle] [has] egg (state|mode)", "entities");
    }

    @Override
    public Boolean get(Turtle turtle) {
        return turtle.hasEgg();
    }

    @Override
    public void change(Turtle turtle, @Nullable Boolean bool, Changer.ChangeMode mode) {
        if (bool != null && mode == Changer.ChangeMode.SET) {
            turtle.setHasEgg(bool);
        }
    }
}
