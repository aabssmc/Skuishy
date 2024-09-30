package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Cannot Enter Hive Ticks")
@Description("Gets/sets the ticks a bee cannot enter its hive.")
@Examples({
        "set cannot enter hive ticks of {_bee} to 20"
})
@Since("2.8")
public class ExprBeeCannotEnterHiveTicks extends EntityExpression<Bee, Integer> {

    static {
        register(ExprBeeCannotEnterHiveTicks.class, Integer.class, "[bee] cannot enter hive ticks", "entities");
    }

    @Override
    public Integer get(Bee bee) {
        return bee.getCannotEnterHiveTicks();
    }

    @Override
    public void change(Bee bee, @Nullable Integer ticks, Changer.ChangeMode mode) {
        if (ticks != null && mode == Changer.ChangeMode.SET) {
            bee.setCannotEnterHiveTicks(ticks);
        }
    }
}
