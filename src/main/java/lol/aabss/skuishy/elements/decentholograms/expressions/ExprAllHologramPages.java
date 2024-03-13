package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Decent Holograms - All Pages")
@Description("Gets all the pages of a hologram.")
@Examples({
        "set {_pages::*} to all pages of {_holo}"
})
@Since("2.5")
@RequiredPlugins("DecentHolograms")
public class ExprAllHologramPages extends SimpleExpression<HologramPage> {

    static {
        Skript.registerExpression(ExprAllHologramPages.class, HologramPage.class, ExpressionType.COMBINED,
                "[all [of the]] pages of %holograms%"
        );
    }

    private Expression<Hologram> hologram;

    @Override
    protected @Nullable HologramPage @NotNull [] get(@NotNull Event e) {
        List<HologramPage> pages = new ArrayList<>();
        for (Hologram holo : hologram.getArray(e)) {
            pages.addAll(holo.getPages());
        }
        return pages.toArray(HologramPage[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends HologramPage> getReturnType() {
        return HologramPage.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "all hologram pages";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        hologram = (Expression<Hologram>) exprs[0];
        return true;
    }
}
