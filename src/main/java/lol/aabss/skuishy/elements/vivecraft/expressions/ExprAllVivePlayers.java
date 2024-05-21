package lol.aabss.skuishy.elements.vivecraft.expressions;

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
import org.vivecraft.VSE;
import org.vivecraft.VivePlayer;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NullableProblems")
@Name("ViveCraft - All Vive Players")
@Description("Gets all the vivecraft players.")
@Examples({
        "send all vivecraft players"
})
@Since("1.9")
public class ExprAllVivePlayers extends SimpleExpression<VivePlayer> {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
            Skript.registerExpression(ExprAllVivePlayers.class, VivePlayer.class, ExpressionType.SIMPLE,
                    "[all [[of] the]] vive[craft] players"
            );
        }
    }

    @Override
    protected @Nullable VivePlayer[] get(@NotNull Event event) {
        return VSE.vivePlayers.values().toArray(VivePlayer[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends VivePlayer> getReturnType() {
        return VivePlayer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "all vive players";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
