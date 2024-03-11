package lol.aabss.skuishy.elements.general.expressions;

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
import lol.aabss.skuishy.other.FakeSign;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Other - Fake Sign")
@Description("Returns things of a fake sign.")
@Examples({
        "send new block of {_fakesign}"
})
@Since("2.5")
public class ExprFakeSign extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprFakeSign.class, Object.class, ExpressionType.COMBINED,
                "[the] [new] (block|sign) of %fakesigns%",
                "[the] player of %fakesigns%"
        );
    }

    private int pattern;
    private Expression<FakeSign> fakesign;

    @Override
    protected @Nullable Object @NotNull [] get(@NotNull Event e) {
        if (pattern == 0){
            List<Block> neww = new ArrayList<>();
            for (FakeSign fakeSign : fakesign.getArray(e)) {
                neww.add(fakeSign.getNewBlock().getBlock());
            }
            return neww.toArray(Block[]::new);
        } else if (pattern == 1){
            //even steven
            List<Player> player = new ArrayList<>();
            for (FakeSign fakeSign : fakesign.getArray(e)) {
                player.add(fakeSign.getPlayer());
            }
            return player.toArray(Player[]::new);
        }
        return new Object[]{null};
    }

    @Override
    public boolean isSingle() {
        return fakesign.isSingle();
    }

    @Override
    public @NotNull Class<?> getReturnType() {
        return switch (pattern) {
            case (0) -> BlockData.class;
            case (1) -> Player.class;
            default -> null;
        };
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "fake sign";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        fakesign = (Expression<FakeSign>) exprs[0];
        pattern = matchedPattern;
        return true;
    }
}
