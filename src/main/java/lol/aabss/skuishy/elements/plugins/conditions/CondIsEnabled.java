package lol.aabss.skuishy.elements.plugins.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@Name("Plugin - Is Enabled")
@Description("Returns true if the plugin is enabled.")
@Examples({
        "if {_p} is enabled:"
})
@Since("2.3")
public class CondIsEnabled extends PropertyCondition<Plugin> {

    static {
        register(CondIsEnabled.class, PropertyType.BE,
                "enabled", "plugins"
        );
    }

    @Override
    public boolean check(Plugin plugin) {
        return plugin.isEnabled();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "enabled";
    }
}
