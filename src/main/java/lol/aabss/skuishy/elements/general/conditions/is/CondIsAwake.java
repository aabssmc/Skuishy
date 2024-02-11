package lol.aabss.skuishy.elements.general.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

@Name("Bat - Is Awake")
@Description("Returns true if the bat is awake.")
@Examples({
        "if last spawned bat is awake:"
})
@Since("2.0")
public class CondIsAwake extends PropertyCondition<LivingEntity> {

    static{
        register(CondIsAwake.class, PropertyType.BE, "awake", "livingentities");
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Bat){
            return ((Bat) entity).isAwake();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "awake";
    }
}
