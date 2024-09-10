package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Boat;
import org.jetbrains.annotations.Nullable;

@Name("Boat - Variant")
@Description("Gets/sets the variant of an boat.")
@Examples({
        "set boat variant of {_boat} to oak"
})
@Since("2.8")
public class ExprBoatVariant extends EntityExpression<Boat, Boat.Type> {

    static {
        register(ExprBoatVariant.class, Boat.Type.class, "boat (variant|type)", "entities");
    }

    @Override
    public Boat.Type get(Boat boat) {
        return boat.getBoatType();
    }

    @Override
    public void change(Boat boat, Boat.@Nullable Type type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            boat.setBoatType(type);
        }
    }
}