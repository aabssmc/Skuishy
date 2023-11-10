package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.CaesarCipher;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Other - Caesar Cipher")
@Description("Decrypts/Encrypts caesar ciphers with shifts")
@Examples({
        "send to encrypted caesar from \"I love Skript!\" #P svcl Zrypwa!",
        "send encrypted caesar from \"I love Skript!\" with shift 10 #S vyfo Cubszd!",
        "send decrypted caesar from string \"P svcl Zrypwa!\" #I love Skript!",
        "send decrypted caesar from string \"S vyfo Cubszd!\" with shift 10 #I love Skript!"
})
@Since("1.5")

public class ExprCaesarCipher extends SimpleExpression<String> {


    static {
        Skript.registerExpression(ExprCaesarCipher.class, String.class, ExpressionType.COMBINED,
                "(:de|:en)(crypted|coded) caesar [cipher] from %string% [with shift %-integer%]"
        );
    }

    boolean isDe;

    Expression<Integer> shift;

    Expression<String> value;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        value = (Expression<String>) exprs[0];
        if (parseResult.hasTag("de")){
            isDe = true;
            if (exprs[1] == null){
                shift = null;
                return true;
            }
            shift = (Expression<Integer>) exprs[1];
            return true;
        }
        isDe = false;
        if (exprs[1] == null){
            shift = null;
            return true;
        }
        shift = (Expression<Integer>) exprs[1];
        return true;
    }

    @Override
    protected String @NotNull [] get(@NotNull Event e) {
        if (shift == null){
            if (isDe){
                return new String[]{CaesarCipher.decrypt(value.getSingle(e), 7)};
            }
            return new String[]{CaesarCipher.encrypt(value.getSingle(e), 7)};
        }
        if (isDe){
            return new String[]{CaesarCipher.decrypt(value.getSingle(e), shift.getSingle(e))};
        }
        return new String[]{CaesarCipher.encrypt(value.getSingle(e), shift.getSingle(e))};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return null;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "caesar cipher " + (isDe ? "de" : "en") + "crypted from string \"" + value + "\"";
    }
}