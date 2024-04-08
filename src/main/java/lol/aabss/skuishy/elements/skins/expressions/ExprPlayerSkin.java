package lol.aabss.skuishy.elements.skins.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import lol.aabss.skuishy.other.SkinWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Name("Skins - Player Skin")
@Description("Gets/Sets/Resets a player's skin.")
@Examples({
        "set skin of player to \"Dinnerbone\" #A name of a player",
        "set skin of player to \"http://textures.minecraft.net/texture/9c8acc755dc6b5e1d282d528030ebc20823a7608853ad5f747ff7ec45d576555\" #Official minecraft skin site",
        "set skin of player to \"https://i.imgur.com/KWITSCB.png\" #An image from any website (nothing but the image on the site)",
        "set skin of player to \"{VALUE}\" #The full value of the skin (no signature)"
})
@Since("1.4, 2.6 (fixed)")

public class ExprPlayerSkin extends PropertyExpression<Player, String> {


    static {
        register(ExprPlayerSkin.class, String.class,
                "[minecraft] skin",
                "players"
        );
    }

    @Override
    protected String @NotNull [] get(@NotNull Event event, Player[] source) {
        List<String> skins = new ArrayList<>();
        for (Player p : source){
            skins.add(p.getPlayerProfile().getTextures().getSkin().toString());
        }
        return skins.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return getExpr().isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "skin of player";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parseResult) {
        setExpr((Expression<? extends Player>) exprs[0]);
        return true;
    }

    @Override
    public @Nullable Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET){
            return CollectionUtils.array(String.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event e, @Nullable Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            for (Player p : getExpr().getArray(e)) {
                if (delta[0] == null) {
                    SkinWrapper.setSkin(p, p.getName());
                } else {
                    String skin = (String) delta[0];
                    if (skin.length() <= 16 || !skin.contains("://")) {
                        SkinWrapper.setSkin(p, skin);
                    } else {
                        if (skin.contains("://")) {
                            try {
                                SkinWrapper.setSkin(p, new URL(skin));
                            } catch (MalformedURLException ignored) {
                            }
                        } else {
                            SkinWrapper.setSkin(p, skin, null);
                        }
                    }
                }
            }
        } else{
            for (Player p : getExpr().getArray(e)) {
                SkinWrapper.setSkin(p, p.getName());
            }
        }
    }
}
