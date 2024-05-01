package lol.aabss.skuishy.elements.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@Name("Enderman - Teleport")
@Description("Tries to make an enderman teleport either randomly or towards an entity.")
@Examples({
        "make {_e} teleport towards player"
})
@Since("2.3")
public class EffTeleport extends Effect {

    static {
        if (Skript.methodExists(Enderman.class, "teleport") && Skript.methodExists(Enderman.class, "teleportTowards", Entity.class)) {
            Skript.registerEffect(EffTeleport.class,
                    "[(try|attempt) to] [:randomly] make %entities% teleport [towards %-entity%]"
            );
        }
    }

    private Expression<Entity> enderman;
    private Expression<Entity> entity;
    private boolean random;

    @Override
    protected void execute(@NotNull Event e) {
        Entity enderman = this.enderman.getSingle(e);
        if (enderman instanceof Enderman){
            if (entity == null){
                if (random) {
                    ((Enderman) enderman).teleportRandomly();
                } else {
                    ((Enderman) enderman).teleport();
                }
            } else{
                Entity entity = this.entity.getSingle(e);
                if (entity != null){
                    ((Enderman) enderman).teleportTowards(entity);
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "make a enderman teleport";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        enderman = (Expression<Entity>) exprs[0];
        entity = (Expression<Entity>) exprs[1];
        random = parseResult.hasTag("randomly");
        return true;
    }
}
