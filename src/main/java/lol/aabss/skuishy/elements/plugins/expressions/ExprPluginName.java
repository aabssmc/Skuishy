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
import java.util.Objects;

@Name("Plugin - Plugin Name")
@Description("Gets the name of a plugin.")
@Examples({
        "send name of plugin named \"Skuishy\""
})
@Since("2.3")
public class ExprPluginName extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPluginName.class, String.class, ExpressionType.COMBINED,
                "[the] [:full] plugin name of %plugins%",
                "%plugins%'s [:full] plugin name"
        );
    }

    private Expression<Plugin> plugin;
    private String type;

    @Override
    protected @Nullable String @NotNull [] get(@NotNull Event event) {
        List<String> name = new ArrayList<>();
        for (Plugin p : this.plugin.getArray(event)) {
            if (Objects.equals(type, "full")){
                name.add(p.getDescription().getFullName());
            } else{
                name.add(p.getDescription().getName());
            }
        }
        return name.toArray(String[]::new);
    }

    @Override
    public boolean isSingle() {
        return plugin.isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "version of plugin";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        plugin = (Expression<Plugin>) exprs[0];
        if (parseResult.hasTag("full")){
            type = "full";
        } return true;
    }
}
