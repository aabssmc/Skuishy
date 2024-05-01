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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Decent Holograms - Hologram Page")
@Description("Gets a hologram page by its index starting from 0.")
@Examples({
        "set {_page} to hologram named \"poopy\""
})
@Since("2.5")
@RequiredPlugins("DecentHolograms")
public class ExprHologramPage extends SimpleExpression<HologramPage> {

    static {
        Skript.registerExpression(ExprHologramPage.class, HologramPage.class, ExpressionType.COMBINED,
                "[the] %integer%(st|nd|rd|th) page of %holograms%",
                "%holograms%'s %integer%(st|nd|rd|th) page",
                "[the] page %integer% of %holograms%",
                "%holograms%'s page %integer%"
        );
    }

    private Expression<Integer> page;
    private Expression<Hologram> hologram;

    @Override
    protected @Nullable HologramPage @NotNull [] get(@NotNull Event e) {
        List<HologramPage> pages = new ArrayList<>();
        Integer page = this.page.getSingle(e);
        if (page != null) {
            for (Hologram holo : hologram.getArray(e)) {
                HologramPage p = holo.getPage(page);
                if (p != null) {
                    pages.add(p);
                }
            }
            return pages.toArray(HologramPage[]::new);
        }
        return new HologramPage[]{null};
    }

    @Override
    public boolean isSingle() {
        return hologram.isSingle();
    }

    @Override
    public @NotNull Class<? extends HologramPage> getReturnType() {
        return HologramPage.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "hologram page";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0 || matchedPattern == 2){
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
        } else if (matchedPattern == 1 || matchedPattern == 3){
            hologram = (Expression<Hologram>) exprs[0];
            page = (Expression<Integer>) exprs[1];
        }
        return true;
    }
}
