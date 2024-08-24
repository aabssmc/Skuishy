package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

@Name("Player - Chat Completions")
@Description("Changes custom chat completion suggestions shown to the player while typing a message.")
@Examples({
        "add \"mama\" and \"papa\" to chat completions of all players"
})
@Since("2.3")
public class EffCustomChatCompletions extends Effect {

    static {
        if (Skript.methodExists(Player.class, "addCustomChatCompletions", Collection.class)) {
            Skript.registerEffect(EffCustomChatCompletions.class,
                    "add %strings% to [the] [custom] chat completions of %players%",
                    "remove %strings% from [the] [custom] chat completions of %players%",
                    "set [the] [custom] chat completions of %players% to %strings%"
            );
        }
    }

    private int pattern;
    private Expression<Player> player;
    private Expression<String> strings;

    @Override
    protected void execute(@NotNull Event event) {
        Player[] players = this.player.getArray(event);
        String[] strings = this.strings.getArray(event);
        if (pattern == 0) {
            for (Player p : players) p.addCustomChatCompletions(List.of(strings));
        } else if (pattern == 1) {
            for (Player p : players) p.removeCustomChatCompletions(List.of(strings));
        } else if (pattern == 2) {
            for (Player p : players) p.setCustomChatCompletions(List.of(strings));
        }

    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "custom chat completions";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        pattern = matchedPattern;
        if (matchedPattern == 0 || matchedPattern == 1){
            strings = (Expression<String>) exprs[0];
            player = (Expression<Player>) exprs[1];
            return true;
        }
        player = (Expression<Player>) exprs[0];
        strings = (Expression<String>) exprs[1];
        return true;
    }
}
