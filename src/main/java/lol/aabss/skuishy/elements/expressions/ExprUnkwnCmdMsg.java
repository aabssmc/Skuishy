package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.command.UnknownCommandEvent;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("Player - Unknown Command Message")
@Description("Gets or sets the unknown command message.")
@Examples({
        "on unknown command:",
        "\tset unknown command message to \"that doesnt exist\""
})
@Since("1.3")
@Events({"unknown command"})
@RequiredPlugins("Paper 1.13+")
public class ExprUnkwnCmdMsg extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprUnkwnCmdMsg.class, String.class, ExpressionType.SIMPLE,
                "[the] unknown command message"
        );
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }


    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parser) {
        if (!getParser().isCurrentEvent(UnknownCommandEvent.class)){
            Skript.error("Cannot use 'unknown command message' outside of a unknown command event");
            return false;
        }
        return true;
    }

    @Override
    public @NonNull String toString(Event event, boolean debug) {
        return "unknown command message";
    }

    @Override
    protected @Nullable String[] get(@NonNull Event e) {
        if (e instanceof UnknownCommandEvent){
            String cmdline = String.valueOf(((UnknownCommandEvent) e).message());
            return new String[]{cmdline};
        }
        return new String[0];
    }

    @Override
    public void change(@NonNull Event event, Object @NonNull [] delta, Changer.@NonNull ChangeMode mode){
        if (mode == Changer.ChangeMode.SET) {
            ((UnknownCommandEvent) event).message(Component.text((String) delta[0]));
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


}
