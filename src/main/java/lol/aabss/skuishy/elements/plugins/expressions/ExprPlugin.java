package lol.aabss.skuishy.elements.plugins.expressions;

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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Plugin - Plugin")
@Description("Gets a plugin by it's name.")
@Examples({
        "set {_e} to plugin named \"Skuishy\""
})
@Since("2.3")
public class ExprPlugin extends SimpleExpression<Plugin> {

    static {
        Skript.registerExpression(ExprPlugin.class, Plugin.class, ExpressionType.COMBINED,
                "plugin (named|with name) %string%"
        );
    }

    private Expression<String> name;

    @Override
    protected @Nullable Plugin @NotNull [] get(@NotNull Event e) {
        String name = this.name.getSingle(e);
        if (name != null){
            return new Plugin[]{Bukkit.getPluginManager().getPlugin(name)};
        }
        return new Plugin[]{null};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Plugin> getReturnType() {
        return Plugin.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "plugin";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        return true;
    }
}
