package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
@Name("World - 1.20.3 Gamerules")
@Description("THIS IS TEMPORARY UNTIL SKRIPT UPDATES!!!\n" +
        "The gamerule value of a world.")
@Examples({
        "skuishy gamerule projectilesCanBreakBlocks in \"world\""
})
@Since("1.9")
public class ExprGamerules extends SimpleExpression<Object> {

    static{
        Skript.registerExpression(ExprGamerules.class, Object.class, ExpressionType.COMBINED,
                "(sk[uishy]) [the] gamerule projectilesCanBreakBlocks in %world%",
                "(sk[uishy]) [the] gamerule playersNetherPortalDefaultDelay in %world%",
                "(sk[uishy]) [the] gamerule playersNetherPortalCreativeDelay in %world%"
        );
    }

    int pattern;
    Expression<World> world;

    @Override
    protected @Nullable Object[] get(@NotNull Event e) {
        World world = this.world.getSingle(e);
        assert world != null;
        if (pattern == 0){
            return new Object[]{world.getGameRuleValue(GameRule.PROJECTILES_CAN_BREAK_BLOCKS)};
        }
        else if (pattern == 1){
            return new Object[]{world.getGameRuleValue(GameRule.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY)};
        }
        else if (pattern == 2){
            return new Object[]{world.getGameRuleValue(GameRule.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY)};
        }
        return new Object[0];
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode){
        World world = this.world.getSingle(e);
        assert world != null;
        if (mode == Changer.ChangeMode.SET) {
            if (pattern == 0){
                if (delta[0] instanceof Boolean){
                    world.setGameRule(GameRule.PROJECTILES_CAN_BREAK_BLOCKS, (Boolean) delta[0]);
                }
            }
            else if (pattern == 1){
                if (delta[0] instanceof Integer){
                    world.setGameRule(GameRule.PLAYERS_NETHER_PORTAL_DEFAULT_DELAY, (Integer) delta[0]);
                }
            }
            else if (pattern == 2){
                if (delta[0] instanceof Integer){
                    world.setGameRule(GameRule.PLAYERS_NETHER_PORTAL_CREATIVE_DELAY, (Integer) delta[0]);
                }
            }
        }
        else {
            assert false;
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(final Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (pattern == 0){
                return CollectionUtils.array(Boolean.class);
            }
            if (pattern == 1 || pattern == 2){
                return CollectionUtils.array(Integer.class);
            }
            return CollectionUtils.array(Object.class);
        }
        return CollectionUtils.array();
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "temp gamerules";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        pattern = matchedPattern;
        return true;
    }
}
