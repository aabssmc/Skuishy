package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Llama - Variant")
@Description("Gets/sets the variant of an llama.")
@Examples({
        "set llama variant of {_llama} to salt pepper"
})
@Since("2.8")
public class ExprLlamaVariant extends SimplePropertyExpression<Entity, Llama.Color> {

    static {
        register(ExprLlamaVariant.class, Llama.Color.class, "llama (variant|type|color)", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "llama variant";
    }

    @Override
    public @Nullable Llama.Color convert(Entity entity) {
        if (entity instanceof Llama) {
            return ((Llama) entity).getColor();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Llama.Color> getReturnType() {
        return Llama.Color.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Llama.Color.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof Llama.Color) {
                for (Entity entity : getExpr().getArray(e)) {
                    if (entity instanceof Llama) {
                        ((Llama) entity).setColor((Llama.Color) delta[0]);
                    }
                }
            }
        }
    }
}