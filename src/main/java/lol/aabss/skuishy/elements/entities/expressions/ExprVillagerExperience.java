package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.Nullable;

@Name("Villager - Experience")
@Description("Gets/sets the experience of a villager.")
@Examples({
        "set experience of {_villager} to 100"
})
@Since("2.8")
public class ExprVillagerExperience extends EntityExpression<Villager, Integer> {

    static {
        register(ExprVillagerExperience.class, Integer.class, "villager experience", "entities");
    }

    @Override
    public Integer get(Villager villager) {
        return villager.getVillagerExperience();
    }

    @Override
    public void change(Villager villager, @Nullable Integer experience, Changer.ChangeMode mode) {
        if (experience != null && mode == Changer.ChangeMode.SET) {
            villager.setVillagerExperience(experience);
        }
    }
}
