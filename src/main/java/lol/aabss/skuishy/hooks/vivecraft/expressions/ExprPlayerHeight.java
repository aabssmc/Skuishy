package lol.aabss.skuishy.hooks.vivecraft.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.vivecraft.VivePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("ViveCraft - Player Height")
@Description("Gets a vivecraft player's height.")
@Examples({
        "if player's height < 20:",
        "send \"dwarf!\" to player"
})
@Since("1.9")
public class ExprPlayerHeight extends PropertyExpression<VivePlayer, Integer> {

    static{
        register(ExprPlayerHeight.class, Integer.class,
                "height",
                "viveplayers");
    }

    @Override
    protected Integer @NotNull [] get(@NotNull Event event, VivePlayer[] source) {
        VivePlayer p = source[0];
        return new Integer[]{(int) p.heightScale};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "height of viveplayer";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
