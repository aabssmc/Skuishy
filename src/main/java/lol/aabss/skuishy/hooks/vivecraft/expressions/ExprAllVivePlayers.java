package lol.aabss.skuishy.hooks.vivecraft.expressions;

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
import org.vivecraft.VSE;
import org.vivecraft.VivePlayer;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("ViveCraft - All Vive Players")
@Description("Gets all the vivecraft players.")
@Examples({
        "send all vivecraft players"
})
@Since("1.9")
public class ExprAllVivePlayers extends SimpleExpression<VivePlayer> {

    static{
        Skript.registerExpression(ExprAllVivePlayers.class, VivePlayer.class, ExpressionType.SIMPLE,
                "[all [[of] the]] vive[craft] players"
        );
    }

    @Override
    protected @Nullable VivePlayer[] get(@NonNull Event e) {
        return VSE.vivePlayers.values().toArray(VivePlayer[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NonNull Class<? extends VivePlayer> getReturnType() {
        return VivePlayer.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "all vive players";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        return true;
    }
}
