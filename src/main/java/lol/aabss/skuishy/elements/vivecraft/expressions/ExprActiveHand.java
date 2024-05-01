package lol.aabss.skuishy.elements.vivecraft.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import org.vivecraft.VSE;

@Name("ViveCraft - Player Active Hand")
@Description("Gets a vivecraft player's activehand.")
@Examples({
        "if player's height < 20:",
        "\tsend \"dwarf!\" to player"
})
@Since("2.5")
public class ExprActiveHand extends PropertyExpression<Player, String> {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
            register(ExprActiveHand.class, String.class,
                    "active[ ]hand",
                    "viveplayers/players");
        }
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Player[] source) {
        Player vivePlayer = source[0];
        if (VSE.vivePlayers.containsKey(vivePlayer.getUniqueId())) {
            return new String[]{vivePlayer.getMetadata("activehand").get(0).asString()};
        }
        Skript.error("Player is not a ViveCraft player!");
        return new String[]{null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "active hand of viveplayer";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
