package lol.aabss.skuishy.elements.vivecraft.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.vivecraft.VSE;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NullableProblems")
@Name("ViveCraft - Vive Only Mode")
@Description("Gets/Sets the vive craft only mode for the server.")
@Examples({
        "set vive only mode to true"
})
@Since("1.9")
public class ExprViveOnly extends SimpleExpression<Boolean> {

    static {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vivecraft-Spigot-Extensions")) {
            Skript.registerExpression(ExprViveOnly.class, Boolean.class, ExpressionType.SIMPLE,
                    "[the] vive[craft] only (mode|state)"
            );
        }
    }

    @Override
    protected @Nullable Boolean[] get(@NotNull Event event) {
        return new Boolean[]{VSE.me.getConfig().getBoolean("general.vive-only")};
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            VSE.me.getConfig().set("general.vive-only", delta[0]);
        }
        else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "vivecraft only mode";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
