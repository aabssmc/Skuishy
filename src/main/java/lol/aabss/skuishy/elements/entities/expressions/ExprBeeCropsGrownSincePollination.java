package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Bee;

import javax.annotation.Nullable;

@Name("Bee - Crops Grown Since Pollination")
@Description("Gets/sets the number of crops grown since a bee last pollinated.")
@Examples({
        "set crops grown since pollination of {_bee} to 10"
})
@Since("2.8")
public class ExprBeeCropsGrownSincePollination extends EntityExpression<Bee, Integer> {

    static {
        register(ExprBeeCropsGrownSincePollination.class, Integer.class, "[bee] crops grown since pollination", "entities");
    }

    @Override
    public Integer get(Bee bee) {
        return bee.getCropsGrownSincePollination();
    }

    @Override
    public void change(Bee bee, @Nullable Integer crops, Changer.ChangeMode mode) {
        if (crops != null && mode == Changer.ChangeMode.SET) {
            bee.setCropsGrownSincePollination(crops);
        }
    }
}
