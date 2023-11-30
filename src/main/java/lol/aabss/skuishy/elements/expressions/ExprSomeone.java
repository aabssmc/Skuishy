package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

@Name("Player - Someone")
@Description("Gets a random player")
@Examples({
        "kill someone"
})
@Since("1.6")

public class ExprSomeone extends SimpleExpression<Player> {

    static{
        Skript.registerExpression(ExprSomeone.class, Player.class, ExpressionType.SIMPLE,
                "some(one|body)",
                "[a] random player"
        );
    }

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    protected @Nullable Player[] get(@NotNull Event e) {
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        Random rand = new Random();
        return new Player[]{players.get(rand.nextInt(players.size()))};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "someone";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
