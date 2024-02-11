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
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Plugin - Plugin Authors")
@Description("Gets the authors of a plugin.")
@Examples({
        "send authors of plugin named \"Skuishy\""
})
@Since("2.3")
public class ExprPluginAuthors extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPluginAuthors.class, String.class, ExpressionType.COMBINED,
                "[the] [plugin] authors of %plugins%",
                "%plugins%'s [plugin] authors"
        );
    }

    private Expression<Plugin> plugin;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event e) {
        List<String> provides = new ArrayList<>();
        for (Plugin p : this.plugin.getArray(e)) {
            provides.addAll(p.getDescription().getAuthors());
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
        return "authors of plugin";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        plugin = (Expression<Plugin>) exprs[0];
        return true;
    }
}
