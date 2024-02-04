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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

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

    private boolean isDe;
    private Expression<Integer> shift;
    private Expression<String> value;

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, @NonNull ParseResult parseResult) {
        value = (Expression<String>) exprs[0];
        isDe = parseResult.hasTag("de");
        if (exprs[1] != null){
            shift = (Expression<Integer>) exprs[1];
        } return true;
    }

    @Override
    protected String @NonNull [] get(@NonNull Event e) {
        String Value = value.getSingle(e);
        if (Value == null) return null;
        if (shift == null){
            if (isDe){
                return new String[]{CaesarCipher.decrypt(Value, 7)};
            }
            return new String[]{CaesarCipher.encrypt(Value, 7)};
        }
        if (isDe){
            return new String[]{CaesarCipher.decrypt(Value, shift.getSingle(e))};
        }
        return new String[]{CaesarCipher.encrypt(Value, shift.getSingle(e))};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "caesar cipher " + (isDe ? "de" : "en") + "crypted from string \"" + value.toString(e, debug) + "\"";
    }
}
