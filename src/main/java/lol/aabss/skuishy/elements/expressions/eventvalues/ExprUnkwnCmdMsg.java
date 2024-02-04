package lol.aabss.skuishy.elements.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.command.UnknownCommandEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("NullableProblems")
@Name("Player - Unknown Command Message")
@Description("Gets or sets the unknown command message.")
@Examples({
        "on unknown command:",
        "\tset unknown command message to \"that doesnt exist\""
})
@Since("1.3")
@Events({"unknown command"})
public class ExprUnkwnCmdMsg extends EventValueExpression<String> {

    static {
        Skript.registerExpression(ExprUnkwnCmdMsg.class, String.class, ExpressionType.SIMPLE,
                "[the] [event-]unknown[ ]command[[ ]message]"
        );
    }

    public ExprUnkwnCmdMsg() {
        super(String.class);
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parser) {
        if (!getParser().isCurrentEvent(UnknownCommandEvent.class)){
            Skript.error("Cannot use 'unknown command message' outside of a unknown command event");
            return false;
        }
        return true;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "unknown command message";
    }

    @Override
    protected @Nullable String[] get(@NotNull Event e) {
        return new String[]{String.valueOf(((UnknownCommandEvent) e).message())};
    }

    @Override
    public void change(@NotNull Event event, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET && delta != null) {
            ((UnknownCommandEvent) event).message(Component.text((String) delta[0]));
        } else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }


}
