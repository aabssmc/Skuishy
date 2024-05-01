package lol.aabss.skuishy.elements.plugins.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Plugin - All Skript Addons")
@Description("Gets all the skript addons.")
@Examples({
        "set {_addons::*} to all skript addons",
        "loop all skript addons:",
        "\tif loop-value is not plugin named \"Skuishy\":",
        "\t\tdisable plugin loop-value"
})
@Since("2.3")
public class ExprSkriptAddons extends SimpleExpression<Plugin> {

    static {
        Skript.registerExpression(ExprSkriptAddons.class, Plugin.class, ExpressionType.COMBINED,
                "[all of] [the] skript addons"
        );
    }

    @Override
    protected @Nullable Plugin @NotNull [] get(@NotNull Event e) {
        List<Plugin> addons = new ArrayList<>();
        for (SkriptAddon addon : Skript.getAddons()){
            addons.add(addon.plugin);
        }
        return addons.toArray(Plugin[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Plugin> getReturnType() {
        return Plugin.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "all skript addons";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
