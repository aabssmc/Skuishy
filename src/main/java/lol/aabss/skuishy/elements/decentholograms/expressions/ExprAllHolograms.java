package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import static eu.decentsoftware.holograms.api.commands.TabCompleteHandler.PLUGIN;

@Name("Decent Holograms - All Holograms")
@Description("Gets all the decent holograms' holograms.")
@Examples({
        "send all holograms"
})
@Since("2.6")
@RequiredPlugins("DecentHolograms")
public class ExprAllHolograms extends SimpleExpression<Hologram> {

    static {
        Skript.registerExpression(ExprAllHolograms.class, Hologram.class, ExpressionType.COMBINED,
                "[all [of the]] [decent holograms|dh] holograms"
        );
    }

    @Override
    protected @Nullable Hologram @NotNull [] get(@NotNull Event e) {
        return PLUGIN.getHologramManager().getHolograms().toArray(Hologram[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Hologram> getReturnType() {
        return Hologram.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "all holograms";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
