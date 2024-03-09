package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.FakeSign;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Other - Fake Sign")
@Description("Creates/Deletes a fake sign")
@Examples({
        "send old block of {_fakesign}"
})
@Since("2.5")
public class EffFakeSign extends Effect {

    static {
        Skript.registerEffect(EffFakeSign.class,
                "(open|show) [a] fake sign to %players% [and store (the sign|it) in %-object%]",
                "(delete|remove) fake sign %fakesigns%"
        );
    }

    private Expression<Player> players;
    private Variable<?> var;
    private int pattern;
    private Expression<FakeSign> fakesign;

    @Override
    protected void execute(@NotNull Event e) {
        if (pattern == 0) {
            for (Player p : players.getArray(e)) {
                Block oldblock = p.getLocation().getBlock();
                Block tempnewblock = p.getLocation().getBlock();
                tempnewblock.setType(Material.OAK_SIGN);
                Sign newblock = (Sign) tempnewblock;
                for (Player pp : Bukkit.getOnlinePlayers()) {
                    pp.sendBlockChange(newblock.getLocation(), oldblock.getBlockData());
                }
                p.openSign(newblock, Side.FRONT);
                FakeSign fakeSign = new FakeSign(oldblock, newblock, p);
                if (var != null) {
                    var.change(e, new FakeSign[]{fakeSign}, Changer.ChangeMode.SET);
                }
            }
        } else{
            for (FakeSign fakeSign : fakesign.getArray(e)){
                fakeSign.remove();
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        pattern = matchedPattern;
        if (matchedPattern == 0) {
            players = (Expression<Player>) exprs[0];
            if (exprs[1] instanceof Variable<?>) {
                var = (Variable<?>) exprs[1];
            } else {
                var = null;
            }
        } else{
            fakesign = (Expression<FakeSign>) exprs[0];
        }
        return true;
    }

}

