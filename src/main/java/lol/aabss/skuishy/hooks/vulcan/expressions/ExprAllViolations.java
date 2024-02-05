package lol.aabss.skuishy.hooks.vulcan.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("NullableProblems")
public class ExprAllViolations extends SimpleExpression<Integer> {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerExpression(ExprAllViolations.class, Integer.class, ExpressionType.COMBINED,
                    "[all [[of] the]] violations of %player%",
                    "[all [[of] the]] combat violations of %player%",
                    "[all [[of] the]] movement violations of %player%",
                    "[all [[of] the]] player violations of %player%",
                    "[all [[of] the]] auto[( |-)]clicker violations of %player%",
                    "[all [[of] the]] timer violations of %player%",
                    "[all [[of] the]] scaffold violations of %player%"
            );
        }
    }

    private String vtype;
    private Expression<Player> player;

    @Override
    protected @Nullable Integer[] get(@NotNull Event e) {
        Player p = player.getSingle(e);
        VulcanAPI api = VulcanAPI.Factory.getApi();
        if (p != null && api != null) {
            return switch (vtype){
                case "all" -> new Integer[]{api.getPlayerData(p).getTotalViolations()};
                case "combat" -> new Integer[]{api.getPlayerData(p).getCombatViolations()};
                case "movement" -> new Integer[]{api.getPlayerData(p).getMovementViolations()};
                case "player" -> new Integer[]{api.getPlayerData(p).getPlayerViolations()};
                case "auto clicker" -> new Integer[]{api.getPlayerData(p).getAutoClickerViolations()};
                case "timer" -> new Integer[]{api.getPlayerData(p).getTimerViolations()};
                case "scaffold" -> new Integer[]{api.getPlayerData(p).getScaffoldViolations()};
                default -> new Integer[]{null};
            };
        }
        return new Integer[]{null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "player violations";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        if (matchedPattern == 0){vtype = "all";}
        else if (matchedPattern == 1){vtype = "combat";}
        else if (matchedPattern == 2){vtype = "movement";}
        else if (matchedPattern == 3){vtype = "player";}
        else if (matchedPattern == 4){vtype = "auto clicker";}
        else if (matchedPattern == 5){vtype = "timer";}
        else if (matchedPattern == 6){vtype = "scaffold";}
        return true;
    }
}
