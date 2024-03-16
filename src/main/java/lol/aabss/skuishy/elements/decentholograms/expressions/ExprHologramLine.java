package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.HologramLine;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


@Name("Decent Holograms - Hologram Line")
@Description("Gets a hologram line by its index starting from 0.")
@Examples({
        "set {_line} to 1st line of {_page}"
})
@Since("2.5")
@RequiredPlugins("DecentHolograms")
public class ExprHologramLine extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprHologramLine.class, String.class, ExpressionType.COMBINED,
                "[the] line %integer% of %hologrampages%",
                "%hologrampages%'s line %integer%"
        );
    }

    private Expression<Integer> line;
    private Expression<HologramPage> page;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event e) {
        List<String> pages = new ArrayList<>();
        Integer line = this.line.getSingle(e);
        if (line != null) {
            for (HologramPage page : page.getArray(e)) {
                HologramLine l = page.getLine(line);
                if (l != null) {
                    pages.add(l.getContent());
                }
            }
            return pages.toArray(String[]::new);
        }
        return new String[]{null};
    }

    @Override
    public boolean isSingle() {
        return page.isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "hologram line";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0 || matchedPattern == 2){
            line = (Expression<Integer>) exprs[0];
            page = (Expression<HologramPage>) exprs[1];
        } else if (matchedPattern == 1 || matchedPattern == 3){
            page = (Expression<HologramPage>) exprs[0];
            line = (Expression<Integer>) exprs[1];
        }
        return true;
    }
}
