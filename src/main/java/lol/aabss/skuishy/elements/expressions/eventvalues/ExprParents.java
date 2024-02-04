package lol.aabss.skuishy.elements.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.papermc.paper.event.entity.EntityFertilizeEggEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;
import java.util.Objects;

@Name("Entity - Parents")
@Description("The parents of a fertilized egg.")
@Examples({
        "on egg fertilize:",
        "\tbroadcast \"%event-mother% and %event-father% gave birth to %event-entity%\""
})
@Since("2.0")
public class ExprParents extends EventValueExpression<Entity> {
    static {
        Skript.registerExpression(ExprParents.class, Entity.class, ExpressionType.SIMPLE,
                "[the] [event-](:mother|father)"
        );
    }

    private String parent;

    public ExprParents() {
        super(Entity.class);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "event parent";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (getParser().isCurrentEvent(EntityFertilizeEggEvent.class)){
            parent = parseResult.hasTag("mother") ? "mother" : "father";
            return true;
        }
        Skript.error("'parents' can not be used outside of EntityFertilizeEggEvent");
        return false;
    }

    @Override
    protected Entity @NotNull [] get(@NotNull Event e) {
        if (Objects.equals(parent, "mother")) {
            return new Entity[]{((EntityFertilizeEggEvent) e).getMother()};
        }
        else if (Objects.equals(parent, "father")) {
            return new Entity[]{((EntityFertilizeEggEvent) e).getFather()};
        }
        return new Entity[]{null};
    }
}