package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NullableProblems")
@Name("Command Block - Command")
@Description("Sends the command in a command block.")
@Examples({
        "send command of target block"
})
@Since("1.7.5, 2.8 (command minecart support)")

public class ExprCommand extends SimplePropertyExpression<Object, String> {

    static{
        register(ExprCommand.class, String.class,
                "command",
                "blocks/entities"
        );
    }

    @Override
    protected String getPropertyName() {
        return "command";
    }

    @Override
    public @Nullable String convert(Object object) {
        if (object instanceof CommandBlock) {
            return ((CommandBlock) object).getCommand();
        } else if (object instanceof CommandMinecart) {
            return ((CommandMinecart) object).getCommand();
        }
        return null;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] instanceof String) {
                for (Object object : getExpr().getArray(e)) {
                    if (object instanceof CommandBlock) {
                        ((CommandBlock) object).setCommand((String) delta[0]);
                    } else if (object instanceof CommandMinecart) {
                        ((CommandMinecart) object).setCommand((String) delta[0]);
                    }
                }
            }
        }
    }
}
