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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Plugin - Enable Plugin")
@Description("Enables a plugin")
@Examples({
        "enable plugin \"Essentials\""
})
@Since("1.4")

public class EffEnablePlugin extends Effect {

    static {
        Skript.registerEffect(EffEnablePlugin.class,
                "enable plugin %string%"
        );
    }

    private Expression<String> plugin;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parser) {
        plugin = (Expression<String>) expressions[0];
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug)  {
        return "enable plugin " + plugin.toString(event, debug);
    }

    @Override
    protected void execute(@NotNull Event event) {
        String pl = this.plugin.getSingle(event);
        if (pl != null) {
            Plugin plugin2 = Bukkit.getPluginManager().getPlugin(pl);
            if (plugin2 != null) {
                Bukkit.getPluginManager().enablePlugin(plugin2);
            }
        }
    }
}