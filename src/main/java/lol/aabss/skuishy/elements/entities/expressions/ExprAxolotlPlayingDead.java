package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Axolotl - Playing Dead")
@Description("Gets/sets the playing dead state of an axolotl.")
@Examples({
        "set playing dead state of {_axolotl} to true"
})
@Since("2.8")
public class ExprAxolotlPlayingDead extends SimplePropertyExpression<Entity, Boolean> {

    static {
        register(ExprAxolotlPlayingDead.class, Boolean.class, "[axolotl] playing dead [mode|state]", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "playing dead";
    }

    @Override
    public @Nullable Boolean convert(Entity entity) {
        if (entity instanceof Axolotl) {
            return ((Axolotl) entity).isPlayingDead();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Boolean) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Axolotl) {
                        ((Axolotl) entity).setPlayingDead((Boolean) delta[0]);
                    }
                }
            }
        }
    }
}

