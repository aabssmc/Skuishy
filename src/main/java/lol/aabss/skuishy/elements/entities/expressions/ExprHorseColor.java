package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Horse;
import org.jetbrains.annotations.Nullable;

@Name("Horse - Color")
@Description("Gets/sets the color of a horse.")
@Examples({
        "set color of {_horse} to creamy"
})
@Since("2.8")
public class ExprHorseColor extends EntityExpression<Horse, Horse.Color> {

    static {
        register(ExprHorseColor.class, Horse.Color.class, "horse color", "entities");
    }

    @Override
    public Horse.Color get(Horse horse) {
        return horse.getColor();
    }

    @Override
    public void change(Horse horse, @Nullable Horse.Color color, Changer.ChangeMode mode) {
        if (color != null && mode == Changer.ChangeMode.SET) {
            horse.setColor(color);
        }
    }
}