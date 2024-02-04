package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Server - Update")
@Description(
        """
                Updates recipe data and the recipe book for all connected clients. Useful for updating clients to new recipes.

                Updates all advancement, tag, and recipe data for all connected clients. Useful for updating clients to new advancements/recipes/tags
        """)
@Examples({
        "update the server resources",
        "update the server recipes"
})
@Since("2.1")
public class EffUpdateResources extends Effect {

    static {
        Skript.registerEffect(EffUpdateResources.class,
                "update [the] [server] (resources|:recipes)"
        );
    }

    private boolean recipes;

    @Override
    protected void execute(@NotNull Event e) {
        if (recipes) {
            Bukkit.updateRecipes();
        } else {
            Bukkit.updateResources();
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "update recipes/resources";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        recipes = parseResult.hasTag("recipes");
        return true;
    }
}
