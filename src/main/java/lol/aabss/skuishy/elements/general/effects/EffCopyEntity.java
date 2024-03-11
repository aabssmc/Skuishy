package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Entity - Copy Entity")
@Description("Makes a copy of an entity.")
@Examples({
        "copy event-entity and spawn it at location of event-entity"
})
@Since("2.0")
public class EffCopyEntity extends Effect {

    static {
        if (Skript.methodExists(Entity.class, "copy") && Skript.methodExists(Entity.class, "copy", Location.class)) {
            Skript.registerEffect(EffCopyEntity.class,
                    "copy %entity% and (store|put) it in %object%",
                    "copy %entity% and (spawn|put) it at %location%"
            );
        }
    }

    private Expression<Entity> entity;
    private Variable<?> var;
    private Expression<Location> location;

    @Override
    protected void execute(@NotNull Event e) {
        Entity en = entity.getSingle(e);
        if (location != null) {
            Location loc = this.location.getSingle(e);
            if (loc != null && en != null) {
                en.copy(loc);
            }
        } else{
            var.change(e, new Object[]{en.copy()}, Changer.ChangeMode.SET);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "copy entity";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        entity = (Expression<Entity>) exprs[0];
        if (matchedPattern == 0){
            if (exprs[1] instanceof Variable<?>) {
                var = (Variable<?>) exprs[1];
            }
            return true;
        }
        location = (Expression<Location>) exprs[1];
        return true;
    }
}
