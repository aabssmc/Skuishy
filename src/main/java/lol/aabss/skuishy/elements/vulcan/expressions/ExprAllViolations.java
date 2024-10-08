package lol.aabss.skuishy.elements.vulcan.expressions;

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
import lol.aabss.skuishy.elements.vulcan.VulcanHook;
import me.frep.vulcan.api.VulcanAPI$Factory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Vulcan - Player Violations")
@Examples("send violations of player")
@Description("Represents the violations of a player.")
@Since("2.0")
@SuppressWarnings("NullableProblems")
public class ExprAllViolations extends SimpleExpression<Integer> {

    static{
        if (VulcanHook.vulcanEnabled()) {
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
    protected @Nullable Integer[] get(@NotNull Event event) {
        List<Integer> violations = new ArrayList<>();
        switch (vtype){
            case "all" -> {
                for (Player p : this.player.getArray(event)) {
                    violations.add(VulcanAPI$Factory.getApi().getPlayerData(p).getTotalViolations());
                }
            } case "combat" -> {
                for (Player p : this.player.getArray(event)) {
                    violations.add(VulcanAPI$Factory.getApi().getPlayerData(p).getCombatViolations());
                }
            } case "movement" -> {
                for (Player p : this.player.getArray(event)) {
                    violations.add(VulcanAPI$Factory.getApi().getPlayerData(p).getMovementViolations());
                }
            } case "player" -> {
                for (Player p : this.player.getArray(event)) {
                    violations.add(VulcanAPI$Factory.getApi().getPlayerData(p).getPlayerViolations());
                }
            } case "auto clicker" -> {
                for (Player p : this.player.getArray(event)) {
                    violations.add(VulcanAPI$Factory.getApi().getPlayerData(p).getAutoClickerViolations());
                }
            } case "timer" -> {
                for (Player p : this.player.getArray(event)) {
                    violations.add(VulcanAPI$Factory.getApi().getPlayerData(p).getTimerViolations());
                }
            } case "scaffold" -> {
                for (Player p : this.player.getArray(event)) {
                    violations.add(VulcanAPI$Factory.getApi().getPlayerData(p).getScaffoldViolations());
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
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
