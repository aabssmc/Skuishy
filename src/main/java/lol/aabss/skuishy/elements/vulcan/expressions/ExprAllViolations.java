package lol.aabss.skuishy.elements.vulcan.expressions;

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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class ExprAllViolations extends SimpleExpression<Integer> {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Vulcan")) {
            Skript.registerExpression(ExprAllViolations.class, Integer.class, ExpressionType.COMBINED,
                    "[all [[of] the]] violations of %players%",
                    "[all [[of] the]] combat violations of %players%",
                    "[all [[of] the]] movement violations of %players%",
                    "[all [[of] the]] player violations of %players%",
                    "[all [[of] the]] auto[( |-)]clicker violations of %players%",
                    "[all [[of] the]] timer violations of %players%",
                    "[all [[of] the]] scaffold violations of %players%"
            );
        }
    }

    private String vtype;
    private Expression<Player> player;

    @Override
    protected @Nullable Integer[] get(@NotNull Event e) {
        List<Integer> violations = new ArrayList<>();
        switch (vtype){
            case "all" -> {
                for (Player p : this.player.getArray(e)) {
                    violations.add(VulcanAPI.Factory.getApi().getPlayerData(p).getTotalViolations());
                }
            } case "combat" -> {
                for (Player p : this.player.getArray(e)) {
                    violations.add(VulcanAPI.Factory.getApi().getPlayerData(p).getCombatViolations());
                }
            } case "movement" -> {
                for (Player p : this.player.getArray(e)) {
                    violations.add(VulcanAPI.Factory.getApi().getPlayerData(p).getMovementViolations());
                }
            } case "player" -> {
                for (Player p : this.player.getArray(e)) {
                    violations.add(VulcanAPI.Factory.getApi().getPlayerData(p).getPlayerViolations());
                }
            } case "auto clicker" -> {
                for (Player p : this.player.getArray(e)) {
                    violations.add(VulcanAPI.Factory.getApi().getPlayerData(p).getAutoClickerViolations());
                }
            } case "timer" -> {
                for (Player p : this.player.getArray(e)) {
                    violations.add(VulcanAPI.Factory.getApi().getPlayerData(p).getTimerViolations());
                }
            } case "scaffold" -> {
                for (Player p : this.player.getArray(e)) {
                    violations.add(VulcanAPI.Factory.getApi().getPlayerData(p).getScaffoldViolations());
                }
            } default -> {return new Integer[]{null};}
        } return violations.toArray(Integer[]::new);
    }

    @Override
    public boolean isSingle() {
        return player.isSingle();
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
