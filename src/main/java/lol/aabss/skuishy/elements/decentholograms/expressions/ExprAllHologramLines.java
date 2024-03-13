package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Decent Holograms - All Lines")
@Description("Gets all the lines of a hologram.")
@Examples({
        "set {_lines::*} to all lines of {_holo}"
})
@Since("2.5")
@RequiredPlugins("DecentHolograms")
public class ExprAllHologramLines extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAllHologramLines.class, String.class, ExpressionType.COMBINED,
                "[all [of the]] lines of %hologrampages%"
        );
    }

    private Expression<HologramPage> page;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event e) {
        List<String> pages = new ArrayList<>();
        for (HologramPage holo : page.getArray(e)) {
            holo.getLines().forEach(hologramLine -> pages.add(hologramLine.getContent()));
        }
        return pages.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "all hologram lines";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        page = (Expression<HologramPage>) exprs[0];
        return true;
    }
}
