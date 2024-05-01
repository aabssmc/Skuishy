package lol.aabss.skuishy.elements.plugins.effects;

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
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

@Name("Plugin - Disable Plugin")
@Description("Disables a plugin")
@Examples({
        "disable plugin named \"Essentials\""
})
@Since("1.4")

public class EffDisablePlugin extends Effect {

    static {
        Skript.registerEffect(EffDisablePlugin.class,
                "disable [plugin] %plugins%"
        );
    }

    private Expression<Plugin> plugin;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parser) {
        plugin = (Expression<Plugin>) expressions[0];
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug)  {
        return "disable plugin";
    }

    @Override
    protected void execute(@NotNull Event event) {
        Plugin[] pl = this.plugin.getArray(event);
        for (Plugin p : pl) {
            Bukkit.getPluginManager().disablePlugin(p);
        }
    }
}