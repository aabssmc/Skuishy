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

@Name("Tebex - Currency")
@Description("Gets the tebex currency")
@Examples({
        "send tebex currency symbol # $",
        "send tebex currency iso_4217 # USD",
        "send tebex currency iso 4217 # USD",
        "send tebex currency iso # USD"
})
@Since("1.6")

public class ExprCurrency extends SimpleExpression<String> {

    static{
        Skript.registerExpression(
                ExprCurrency.class, String.class, ExpressionType.SIMPLE,
                "[the] tebex currency [as] (:symbol|iso[[( |_)]4217])"
        );
    }

    boolean symbol;

    @Override
    protected String @NotNull [] get(@NotNull Event e) {
        try {
            JsonObject api = TebexAPI.api("information", "GET");
            if (api == null){
                Skript.error("Invalid Tebex Secret. Edit in config.yml");
                return null;
            }
            String a;
            if (symbol){
                a = api
                        .getAsJsonObject("account")
                        .getAsJsonObject("currency")
                        .getAsJsonPrimitive("iso_4217")
                        .getAsString();
            }
            else{
                a = api
                        .getAsJsonObject("account")
                        .getAsJsonObject("currency")
                        .getAsJsonPrimitive("symbol")
                        .getAsString();
            }
            return new String[]{a};
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "tebex currency";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        symbol = parseResult.hasTag("symbol");
        return true;
    }
}
