package lol.aabss.skuishy.hooks.vivecraft.expressions;

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
import org.vivecraft.VSE;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("ViveCraft - Vive Only Mode")
@Description("Gets/Sets the vive craft only mode for the server.")
@Examples({
        "set vive only mode to true"
})
@Since("1.9")
public class ExprViveOnly extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprViveOnly.class, Boolean.class, ExpressionType.SIMPLE,
                "[the] vive[craft] only (mode|state)"
        );
    }

    @Override
    protected @Nullable Boolean[] get(@NonNull Event e) {
        return new Boolean[]{VSE.me.getConfig().getBoolean("general.vive-only")};
    }

    @Override
    public void change(@NonNull Event e, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            VSE.me.getConfig().set("general.vive-only", delta[0]);
        }
        else {
            assert false;
        }
    }

    @Override
    public Class<?> @NonNull [] acceptChange(final Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NonNull Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "vivecraft only mode";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        return true;
    }
}
