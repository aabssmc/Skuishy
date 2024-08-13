package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Decent Holograms - Always Face Player")
@Description("Gets/sets the always face player state of a hologram.")
@Examples({
        "set always face player mode of {_holo}"
})
@Since("2.8")
@RequiredPlugins("DecentHolograms")
public class ExprAlwaysFacePlayer extends SimplePropertyExpression<Hologram, Boolean> {

    static {
        register(ExprAlwaysFacePlayer.class, Boolean.class, "always face player [state|mode]", "holograms");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "always face player";
    }

    @Override
    public @Nullable Boolean convert(Hologram hologram) {
        return hologram.isAlwaysFacePlayer();
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
            if (delta[0] instanceof Boolean){
                for (Hologram holo : getExpr().getArray(e)) {
                    holo.setAlwaysFacePlayer((Boolean) delta[0]);
                }
            }
        }
    }
}
