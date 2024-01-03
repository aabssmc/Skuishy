package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Color;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.Glow;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Entity - Glow Color")
@Description("Makes a entity glow a color")
@Examples({
        "make player glow red",
        "wait 10 seconds",
        "make player stop glowing "
})
@Since("1.4")

public class EffGlowColor extends Effect {

    static {
        Skript.registerEffect(EffGlowColor.class,
                "make %entities% glow %color%"
        );
    }

    private Expression<Entity> entity;
    private Expression<Color> color;

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean isDelayed, @NotNull ParseResult parser) {
        entity = (Expression<Entity>) expressions[0];
        color = (Expression<Color>) expressions[1];
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug)  {
        return "make " + entity.toString(event, debug) + " glowing " + color.toString(event, debug);
    }

    @Override
    protected void execute(@NotNull Event event) {
        for(Entity e : entity.getArray(event)){
            Glow.mainGlow(e, color, event);
        }
    }
}