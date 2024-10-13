package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.OminousItemSpawner;
import org.jetbrains.annotations.Nullable;

@Name("Ominous Item Spawner - Spawn Item After Ticks")
@Description("Gets/sets the spawn item after ticks of a ominous item spawner.")
@Examples({
        "set spawn item after ticks of {_spawn} to 1000"
})
@Since("2.8")
public class ExprOminousItemSpawnerSpawnItemAfterTicks extends EntityExpression<OminousItemSpawner, Long> {

    static {
        if (Skript.classExists("org.bukkit.entity.OminousItemSpawner")) {
            register(ExprOminousItemSpawnerSpawnItemAfterTicks.class, Long.class, "[ominous[( | item )]spawner] spawn item after ticks", "entities");
        }
    }

    @Override
    public Long get(OminousItemSpawner ominousItemSpawner) {
        return ominousItemSpawner.getSpawnItemAfterTicks();
    }

    @Override
    public void change(OminousItemSpawner spawner, @Nullable Long aLong, Changer.ChangeMode mode) {
        if (aLong != null && mode == Changer.ChangeMode.SET) {
            spawner.setSpawnItemAfterTicks(aLong);
        }
    }
}
