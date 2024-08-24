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

@Name("Axolotl - Variant")
@Description("Gets/sets the variant of an axolotl.")
@Examples({
        "set axolotl variant of {_axolotl} to blue"
})
@Since("2.8")
public class ExprAxolotlVariant extends SimplePropertyExpression<Entity, Axolotl.Variant> {

    static {
        register(ExprAxolotlVariant.class, Axolotl.Variant.class, "axolotl variant", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "axolotl variant";
    }

    @Override
    public @Nullable Axolotl.Variant convert(Entity entity) {
        if (entity instanceof Axolotl) {
            return ((Axolotl) entity).getVariant();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Axolotl.Variant> getReturnType() {
        return Axolotl.Variant.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Axolotl.Variant.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Axolotl.Variant) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Axolotl) {
                        ((Axolotl) entity).setVariant((Axolotl.Variant) delta[0]);
                    }
                }
            }
        }
    }
}