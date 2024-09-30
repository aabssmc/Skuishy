package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.Nullable;

@Name("Villager - Level")
@Description("Gets/sets the level of a villager.")
@Examples({
        "set level of {_villager} to 5"
})
@Since("2.8")
public class ExprVillagerLevel extends EntityExpression<Villager, Integer> {

    static {
        register(ExprVillagerLevel.class, Integer.class, "villager level", "entities");
    }

    @Override
    public Integer get(Villager villager) {
        return villager.getVillagerLevel();
    }

    @Override
    public void change(Villager villager, @Nullable Integer level, Changer.ChangeMode mode) {
        if (level != null && mode == Changer.ChangeMode.SET) {
            villager.setVillagerLevel(level);
        }
    }
}
