package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Plugin - Disable Plugin")
@Description("Disables a plugin")
@Examples({
        "disable plugin \"Essentials\""
})
@Since("1.4")

public class EffDisablePlugin extends Effect {

    static {
        Skript.registerEffect(EffDisablePlugin.class,
                "disable plugin %string%"
        );
    }

    private Expression<String> plugin;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NonNull Kleenean isDelayed, @NonNull ParseResult parser) {
        plugin = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public @NonNull String toString(@Nullable Event event, boolean debug)  {
        return "disable plugin " + plugin.toString(event, debug);
    }

    @Override
    protected void execute(@NonNull Event event) {
        String pl = this.plugin.getSingle(event);
        if (pl != null) {
            Plugin plugin2 = Bukkit.getPluginManager().getPlugin(pl);
            if (plugin2 != null) {
                Bukkit.getPluginManager().disablePlugin(plugin2);
            }
        }
    }
}