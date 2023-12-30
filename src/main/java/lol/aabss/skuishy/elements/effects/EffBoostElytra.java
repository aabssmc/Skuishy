package lol.aabss.skuishy.elements.effects;

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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("Player - Boost Elytra")
@Description("Make a player boost their elytra.")
@Examples({
        "boost player's elytra with player's tool"
})
@Since("2.0")
public class EffBoostElytra extends Effect {

    static{
        Skript.registerEffect(EffBoostElytra.class,
                "boost %player%'s elytra with %item%"
        );
    }

    private Expression<Player> p;
    private Expression<ItemStack> firework;

    @Override
    protected void execute(@NotNull Event e) {
        p.getSingle(e).fireworkBoost(firework.getSingle(e));
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "boost player's elytra";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        p = (Expression<Player>) exprs[0];
        firework = (Expression<ItemStack>) exprs[1];
        return true;
    }
}
