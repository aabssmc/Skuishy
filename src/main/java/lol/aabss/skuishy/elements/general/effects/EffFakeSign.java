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
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.FakeSign;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Name("Other - Fake Sign")
@Description("Creates/Deletes a fake sign")
@Examples({
        "open fake sign to player with text \"ok\""
})
@Since("2.5")
public class EffFakeSign extends Effect {

    public static HashMap<Block, FakeSign> FAKE_SIGNS = new HashMap<>();

    static {
        if (Skript.classExists("org.bukkit.block.sign.Side")) {
            Skript.registerEffect(EffFakeSign.class,
                    "(open|show) [a] fake sign to %players% [and store (the sign|it) in %-object%] [with text %-strings%]",
                    "(delete|remove) fake sign %fakesigns%"
            );
        }
    }

    private Expression<Player> players;
    private Variable<?> var;
    private int pattern;
    private Expression<FakeSign> fakesign;
    private Expression<String> strings;

    @SuppressWarnings("deprecation")
    @Override
    protected void execute(@NotNull Event e) {
        if (pattern == 0) {
            for (Player p : players.getArray(e)) {
                Block b = sphereAround(p.getLocation());
                if (b != null) {
                    BlockData og = b.getBlockData().clone();
                    b.setBlockData(Material.OAK_SIGN.createBlockData(), false);
                    b.getState().update(true, false);
                    Sign newblock = (Sign) b.getState();
                    if (strings != null) {
                        String[] strings = this.strings.getArray(e);
                        List<String> strs;
                        if (strings.length > 4) {
                            strs = List.of(strings[0], strings[1], strings[2], strings[3]);
                        } else{
                            strs = Arrays.stream(strings).toList();
                        }
                        int line = 0;
                        for (String s : strs) {
                            if (line <= 4) {
                                newblock.getSide(Side.FRONT).setLine(line, s);
                                line = line + 1;
                            }
                        }

                    }
                    p.openSign(newblock, Side.FRONT);
                    FakeSign fakeSign = new FakeSign(newblock, og, p);
                    if (var != null) {
                        var.change(e, new FakeSign[]{fakeSign}, Changer.ChangeMode.SET);
                    }
                    FAKE_SIGNS.put(newblock.getBlock(), fakeSign);
                    Bukkit.getScheduler().runTaskLater(Skuishy.instance, () -> {
                        for (Player pp : Bukkit.getOnlinePlayers()) {
                            pp.sendBlockChange(newblock.getLocation(), og);
                        }
                    }, 4L);
                }
            }
        } else{
            for (FakeSign fakeSign : fakesign.getArray(e)){
                fakeSign.remove();
            }
        }
    }
    private Block sphereAround(Location location) {
        Block center = location.getBlock();
        for(int x = -6; x <= 6; x++) {
            for(int y = -6; y <= 6; y++) {
                for(int z = -6; z <= 6; z++) {
                    Block b = center.getRelative(x, y, z);
                    if(center.getLocation().distance(b.getLocation()) <= 6) {
                        if (b.getType() == Material.AIR ||
                                b.getType() == Material.CAVE_AIR ||
                                b.getType() == Material.VOID_AIR
                        ){
                            return b;
                        }
                    }
                }

            }
        }
        return null;
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
            strings = (Expression<String>) exprs[2];
        } else{
            fakesign = (Expression<FakeSign>) exprs[0];
        }
        return true;
    }

}

