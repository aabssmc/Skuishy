package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Ticks Since Pollination")
@Description("Gets/sets the number of ticks since a bee last pollinated.")
@Examples({
        "set ticks since pollination of {_bee} to 20"
})
@Since("2.8")
public class ExprBeeTicksSincePollination extends EntityExpression<Bee, Integer> {

    static {
        register(ExprBeeTicksSincePollination.class, Integer.class, "[bee] ticks since pollination", "entities");
    }

    @Override
    public Integer get(Bee bee) {
        return bee.getTicksSincePollination();
    }

    @Override
    public void change(Bee bee, @Nullable Integer ticks, Changer.ChangeMode mode) {
        if (ticks != null && mode == Changer.ChangeMode.SET) {
            bee.setTicksSincePollination(ticks);
        }
    }
}
