package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Entity - Custom Name")
@Description("Gets/sets the custom name of entities.")
@Examples({
        "set custom name of target entity to \"poopies\""
})
@Since("1.8")
public class ExprCustomName extends PropertyExpression<Entity, String> {

    static {
        register(ExprCustomName.class, String.class,
                "custom[ ]name",
                "entities"
        );
    }

    @Override
    protected String @NotNull [] get(@NotNull Event e, Entity @NotNull [] source) {
        Entity en = getExpr().getSingle(e);
        assert en != null;
        if (en.customName() != null) {
            return new String[]{en.customName().toString()};
        }
        return new String[0];
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            Entity en = getExpr().getSingle(e);
            assert en != null;
            en.customName(Component.text((String) delta[0]));
        }
        else if (mode == Changer.ChangeMode.RESET) {
            Entity en = getExpr().getSingle(e);
            assert en != null;
            en.customName(Component.text(en.getName()));
        }
        else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(String.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "custom name";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Entity>) exprs[0]);
        return true;
    }
}
