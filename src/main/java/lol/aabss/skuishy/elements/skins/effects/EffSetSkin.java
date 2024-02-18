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
@Description({"Sets/resets the skin of the player",
"# NOTE: ",
"### Only accepted values are; username, url (MUST BE textures.minecraft.net/texture/) or skin vaule"})
@Examples({
        "set skin of player to \"aabss\"",
        "set skin of player to \"http://textures.minecraft.net/texture/9c8acc755dc6b5e1d282d528030ebc20823a7608853ad5f747ff7ec45d576555\"",
        "set skin of player to \"{SKIN VALUE}\" # not adding real skin value cuz its too long"
})
@Since("2.3")
public class EffSetSkin extends Effect {

    static {
        Skript.registerEffect(EffSetSkin.class,
                "set [minecraft] skin of %players% to %string%",
                "set %player%'[s] [minecraft] skin to %string%",
                "reset [minecraft] skin of %players%",
                "reset %players%'[s] [minecraft] skin"
        );
    }

    private Expression<Player> player;
    private Expression<String> skin;

    @Override
    protected void execute(@NotNull Event e) {
        for (Player p : player.getArray(e)){
            if (skin == null){
                SkinWrapper.setSkin(p, null);
            } else{
                String skin = this.skin.getSingle(e);
                if (skin != null){
                    if (skin.length() <= 200){
                        SkinWrapper.setSkin(p, skin);
                    } else{
                        SkinWrapper.setSkin(p, skin, null);
                    }
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
        if (matchedPattern == 0 || matchedPattern == 1) {
            skin = (Expression<String>) exprs[1];
        } else {
            skin = null;
        } return true;
    }
}
