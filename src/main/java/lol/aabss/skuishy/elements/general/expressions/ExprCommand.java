package lol.aabss.skuishy.elements.general.expressions;

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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
                "blocks"
        );
    }

    @Override
    protected @Nullable String[] get(@NotNull Event e, Block @NotNull [] source) {
        if (getExpr().getArray(e) instanceof CommandBlock[] c){
            List<String> commands = new ArrayList<>();
            for (CommandBlock b : c) {
                commands.add(b.getCommand());
            }
            return commands.toArray(String[]::new);
        }
        return new String[]{null};
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            Block[] block = getExpr().getArray(e);
            if (block instanceof CommandBlock[] cc){
                for (CommandBlock c : cc) {
                    c.setCommand((String) delta[0]);
                }
            }
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "command of command block";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Block>) exprs[0]);
        return true;
    }
}
