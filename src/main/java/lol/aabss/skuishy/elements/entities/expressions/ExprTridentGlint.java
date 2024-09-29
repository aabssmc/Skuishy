package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Trident;
import org.jetbrains.annotations.Nullable;

@Name("Trident - Has Glint")
@Description("Gets/Sets the has glint state of a trident.")
@Examples({
        "set glint state of {_trident} to false"
})
@Since("2.8")
public class ExprTridentGlint extends EntityExpression<Trident, Boolean> {

    static {
        register(ExprTridentGlint.class, Boolean.class, "[trident] [has] glint (state|mode)", "entities");
    }

    @Override
    public Boolean get(Trident trident) {
        return trident.hasGlint();
    }

    @Override
    public void change(Trident trident, @Nullable Boolean bool, Changer.ChangeMode mode) {
        if (bool != null && mode == Changer.ChangeMode.SET) {
            trident.setGlint(bool);
        }
    }
}
