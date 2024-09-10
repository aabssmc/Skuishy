package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Chicken;
import org.jetbrains.annotations.Nullable;

@Name("Chicken - Chicken Jockey")
@Description("Gets/sets the chicken jockey state of a chicken.")
@Examples({
        "set chicken jockey state of {_chicken} to true"
})
@Since("2.8")
public class ExprChickenJockey extends EntityExpression<Chicken, Boolean> {

    static {
        register(ExprChickenJockey.class, Boolean.class, "chicken jockey [state|mode]", "entities");
    }

    @Override
    public Boolean get(Chicken chicken) {
        return chicken.isChickenJockey();
    }

    @Override
    public void change(Chicken chicken, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            chicken.setIsChickenJockey(aBoolean);
        }
    }
}