package lol.aabss.skuishy.elements.skins.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.SkinWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Player - Skin")
@Description({"Sets the skin of the player to a value and a signature."})
@Examples({
        "set skin of player to \"value\" and \"signature\" "
})
@Since("2.3, 2.6 (fixed)")
public class EffSetSkin extends Effect {

    static {
        Skript.registerEffect(EffSetSkin.class,
                "set [minecraft] skin of %players% to [value] %string% and [signature] %string%",
                "set %player%'[s] [minecraft] skin to [value] %string% and [signature] %string%"
        );
    }

    private Expression<Player> player;
    private Expression<String> value;
    private Expression<String> signature;

    @Override
    protected void execute(@NotNull Event e) {
        for (Player p : player.getArray(e)){
            if (value != null && signature != null){
                String value = this.value.getSingle(e);
                String signature = this.signature.getSingle(e);
                if (value != null && signature != null){
                    SkinWrapper.setSkin(p, value, signature);
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "skin of player";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        value = (Expression<String>) exprs[1];
        signature = (Expression<String>) exprs[2];
        return true;
    }
}
