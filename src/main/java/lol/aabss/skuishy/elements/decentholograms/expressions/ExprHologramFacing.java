package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Decent Holograms - Hologram Facing")
@Description("Gets/Sets a hologram facing of a hologram.")
@Examples({
        "send hologram facing of {_holo}"
})
@Since("2.5")
@RequiredPlugins("DecentHolograms")
public class ExprHologramFacing extends PropertyExpression<Hologram, Number> {

    static {
        register(ExprHologramFacing.class, Number.class, "holo[gram] facing", "holograms");
    }

    @Override
    protected Number @NotNull [] get(@NotNull Event event, Hologram[] source) {
        List<Number> names = new ArrayList<>();
        for (Hologram holo : source){
            names.add(holo.getFacing());
        }
        return names.toArray(Number[]::new);
    }

    @Override
    public @NotNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "facing of hologram";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Hologram>) exprs[0]);
        return true;
    }

    @Override
    public @Nullable Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            for (Hologram holo : getExpr().getArray(e)){
                holo.setFacing((Float) delta[0]);
            }
        }
    }
}
