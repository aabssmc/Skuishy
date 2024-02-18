package lol.aabss.skuishy.elements.plugins.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@Name("Plugin - Is Disabled")
@Description("Returns true if the plugin is disabled.")
@Examples({
        "if {_p} is disabled:"
})
@Since("2.3")
public class CondIsDisabled extends PropertyCondition<Plugin> {

    static {
        register(CondIsDisabled.class, PropertyType.BE,
                "disabled", "plugins"
        );
    }

    @Override
    public boolean check(Plugin plugin) {
        return !plugin.isEnabled();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "disabled";
    }
}
