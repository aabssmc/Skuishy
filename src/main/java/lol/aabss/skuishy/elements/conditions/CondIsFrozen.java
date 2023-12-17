package lol.aabss.skuishy.elements.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CondIsFrozen extends Condition {

    static {
        Skript.registerCondition(CondIsFrozen.class,
                "[the] ((server|game) [tick[(s|[( |-)]rate)]]|%entity%) (is|are) frozen",
                "[the] ((server|game) [tick[(s|[( |-)]rate)]]|%entity%) (is|are)( not|n't) frozen"
        );
    }

    private boolean is;
    private Expression<Entity> entity;

    @Override
    public boolean check(@NotNull Event e) {
        if (entity != null){
            if (is){
                return Bukkit.getServerTickManager().isFrozen(entity.getSingle(e));
            }
            return !Bukkit.getServerTickManager().isFrozen(entity.getSingle(e));
        }
        if (is){
            return Bukkit.getServerTickManager().isFrozen();
        }
        return !Bukkit.getServerTickManager().isFrozen();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return ((entity != null) ? "entity" : "ticks") + "ticks are frozen";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        is = matchedPattern == 0;
        entity = (Expression<Entity>) exprs[0];
        return true;
    }
}
