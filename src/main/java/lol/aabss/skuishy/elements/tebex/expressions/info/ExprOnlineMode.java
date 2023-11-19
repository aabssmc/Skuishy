package lol.aabss.skuishy.elements.tebex.expressions.info;

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
import com.google.gson.JsonObject;
import lol.aabss.skuishy.other.TebexAPI;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Name("Tebex - Online Mode")
@Description("Gets the online mode of tebex")
@Examples({
        "send tebex online mode"
})
@Since("1.6")

public class ExprOnlineMode extends SimpleExpression<Boolean> {

    static{
        Skript.registerExpression(
                ExprOnlineMode.class, Boolean.class, ExpressionType.SIMPLE,
                "[the] tebex online mode [status]"
        );
    }

    @Override
    protected Boolean @NotNull [] get(@NotNull Event e) {
        try {
            JsonObject api = TebexAPI.api("information", "GET");
            if (api == null){
                Skript.error("Invalid Tebex Secret. Edit in config.yml");
                return null;
            }
            boolean a = api
                    .getAsJsonObject("account")
                    .getAsJsonPrimitive("online_mode")
                    .getAsBoolean();
            return new Boolean[]{a};
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
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
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "tebex online mode";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
