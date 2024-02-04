package lol.aabss.skuishy.hooks.vivecraft.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.vivecraft.VSE;
import org.vivecraft.VivePlayer;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("ViveCraft - Player Height")
@Description("Gets a vivecraft player's height.")
@Examples({
        "if player's height < 20:",
        "send \"dwarf!\" to player"
})
@Since("1.9")
public class ExprPlayerHeight extends PropertyExpression<Player, Number> {

    static{
        register(ExprPlayerHeight.class, Number.class,
                "height",
                "viveplayers/players");
    }

    @Override
    protected Number @NonNull [] get(@NonNull Event event, Player[] source) {
        Player vivePlayer = source[0];
        if (VSE.vivePlayers.containsKey(vivePlayer.getUniqueId())) {
            return new Number[]{new VivePlayer(vivePlayer).heightScale};
        }
        Skript.error("Player is not a ViveCraft player!");
        return new Number[]{0};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NonNull Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "height of viveplayer";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        return true;
    }
}
