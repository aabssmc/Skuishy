package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Fireball;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

@Name("Fireball - Acceleration")
@Description("Gets/sets the acceleration.")
@Examples({
        "set acceleration of {_fireball} to {_sheep}"
})
@Since("2.8")
public class ExprFireballAcceleration extends EntityExpression<Fireball, Vector> {

    static {
        register(ExprFireballAcceleration.class, Vector.class, "[fireball] acceleration", "entities");
    }

    @Override
    public Vector get(Fireball fireball) {
        return fireball.getAcceleration();
    }

    @Override
    public void change(Fireball fireball, @Nullable Vector vector, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && vector != null) {
            fireball.setAcceleration(vector);
        }
    }

}