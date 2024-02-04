package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("Block - Command of Command Block")
@Description("Sends the command in a command block.")
@Examples({
        "send command of target block"
})
@Since("1.7.5")

public class ExprCommand extends PropertyExpression<Block, String> {

    static{
        register(ExprCommand.class, String.class,
                "command",
                "block"
        );
    }

    @Override
    protected @Nullable String[] get(@NonNull Event e, Block @NonNull [] source) {
        if (getExpr().getSingle(e) instanceof CommandBlock c){
            return new String[]{c.getCommand()};
        }
        return new String[0];
    }

    @Override
    public void change(@NonNull Event e, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            Block block = getExpr().getSingle(e);
            if (block instanceof CommandBlock){
                ((CommandBlock) block).setCommand((String) delta[0]);
            }
        } else {
            assert false;
        }
    }

    @Override
    public Class<?> @NonNull [] acceptChange(final Changer.@NonNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "command of command block";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }
}
