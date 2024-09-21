package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Horse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Style")
@Description("Gets/sets the style of a horse.")
@Examples({
        "set style of {_horse} to creamy"
})
@Since("2.8")
public class ExprHorseStyle extends EntityExpression<Horse, Horse.Style> {

    static {
        register(ExprHorseStyle.class, Horse.Style.class, "horse style", "entities");
    }

    @Override
    public Horse.Style get(Horse horse) {
        return horse.getStyle();
    }

    @Override
    public void change(Horse horse, @Nullable Horse.Style style, Changer.ChangeMode mode) {
        if (style != null && mode == Changer.ChangeMode.SET) {
            horse.setStyle(style);
        }
    }
}