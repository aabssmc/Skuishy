package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.bstats.bukkit.Metrics;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@Name("bStats - bStats Metrics")
@Description("Makes or shuts down a bStats metric from a service id.")
@Examples({
        "on load:",
        "\tmake bstats metrics by id 12345",
        "",
        "on unload:",
        "\tshutdown bstats metrics from service id 12345"
})
@Since("2.7")
public class EffbStatsMetrics extends Effect {

    public static HashMap<Integer, Metrics> metricsList = new HashMap<>();

    static {
        Skript.registerEffect(EffbStatsMetrics.class,
                "(make [new]|:shutdown) (bstats|[bstats] metrics) (with|from|by) [service ]id %integer%"
        );
    }

    private Expression<Integer> serviceId;
    private boolean shutdown;

    @Override
    protected void execute(@NotNull Event event) {
        Integer serviceId = this.serviceId.getSingle(event);
        if (serviceId == null){
            return;
        }
        if (shutdown) {
            if (metricsList.containsKey(serviceId)) {
                metricsList.get(serviceId).shutdown();
                metricsList.remove(serviceId);
            }
        } else {
            if (!metricsList.containsKey(serviceId)) {
                metricsList.put(serviceId, new Metrics(Skript.getInstance(), serviceId));
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return (shutdown ? "shutdown" : "register") + " bstats metrics";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        serviceId = (Expression<Integer>) expressions[0];
        shutdown = parseResult.hasTag("shutdown");
        return true;
    }
}
