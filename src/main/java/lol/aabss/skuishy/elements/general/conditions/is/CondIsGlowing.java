package lol.aabss.skuishy.elements.general.conditions.is;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Entity - Is Glowing")
@Description("Checks whether a player is glowing.")
@Examples({
        "if player is glowing:",
        "\tmake player stop glowing"
})
@Since("1.6")

public class CondIsGlowing extends Condition {

    static{
        Skript.registerCondition(CondIsGlowing.class,
                "%entities% (is|are) glowing",
                "%entities% (isn't|is not|aren't|are not) glowing"
        );
    }

    private Expression<Entity> entities;
    private boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        for (final Entity entity : entities.getArray(e)){
            if (is){
                return entity.isGlowing();
            }
            return !entity.isGlowing();
        }
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return entities.toString(e,debug) + " is glowing";
    }


    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        entities = (Expression<Entity>) exprs[0];
        is = (matchedPattern == 0);
        return true;
    }
}
