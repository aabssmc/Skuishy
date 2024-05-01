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
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Plugin - Plugin Dependencies")
@Description("Gets the dependencies and soft dependencies of a plugin.")
@Examples({
        "send soft dependencies of plugin named \"Skuishy\""
})
@Since("2.3")
public class ExprPluginDepends extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPluginDepends.class, String.class, ExpressionType.COMBINED,
                "[all [of]] [the] [plugin] [:soft] depend[(encies|s)] of %plugins%",
                "%plugins%'s [plugin] [:soft] depend[(encies|s)]"
        );
    }

    private Expression<Plugin> plugin;
    private boolean soft;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event e) {
        List<String> provides = new ArrayList<>();
        for (Plugin p : this.plugin.getArray(e)) {
            if (soft) {
                provides.addAll(p.getDescription().getSoftDepend());
            } else{
                provides.addAll(p.getDescription().getDepend());
            }
        }
        return provides.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return (soft ? "soft " : "") + "dependencies of plugin";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        plugin = (Expression<Plugin>) exprs[0];
        soft = parseResult.hasTag("soft");
        return true;
    }
}
