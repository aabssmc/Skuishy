package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.Nullable;

@Name("Villager - Restocks Today")
@Description("Gets/sets the number of restocks for today of a villager.")
@Examples({
        "set restocks today of {_villager} to 3"
})
@Since("2.8")
public class ExprVillagerRestocksToday extends EntityExpression<Villager, Integer> {

    static {
        register(ExprVillagerRestocksToday.class, Integer.class, "[villager] restocks today", "entities");
    }

    @Override
    public Integer get(Villager villager) {
        return villager.getRestocksToday();
    }

    @Override
    public void change(Villager villager, @Nullable Integer restocks, Changer.ChangeMode mode) {
        if (restocks != null && mode == Changer.ChangeMode.SET) {
            villager.setRestocksToday(restocks);
        }
    }
}
