package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Goat;
import org.jetbrains.annotations.Nullable;

@Name("Goat - Horn")
@Description("Gets/sets the horn state of a goat.")
@Examples({
        "set left horn state of {_goat} to true"
})
@Since("2.8")
public class ExprGoatHorn extends EntityExpression<Goat, Boolean> {

    static {
        register(ExprGoatHorn.class, Boolean.class, "[goat] (:left|right) horn [mode|state]", "entities");
    }

    @Override
    public Boolean get(Goat goat) {
        return tags.contains("left") ? goat.hasLeftHorn() : goat.hasRightHorn();
    }

    @Override
    public void change(Goat goat, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("left")) {
                goat.setLeftHorn(aBoolean);
            } else {
                goat.setRightHorn(aBoolean);
            }
        }
    }
}