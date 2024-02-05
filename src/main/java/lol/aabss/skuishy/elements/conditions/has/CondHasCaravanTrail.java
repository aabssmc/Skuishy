package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.jetbrains.annotations.NotNull;

@Name("Llama - Has Caravan Trail")
@Description("Returns true if the llama has a caravan trail.")
@Examples({
        "if target entity has caravan trail:"
})
@Since("2.0")
public class CondHasCaravanTrail extends PropertyCondition<LivingEntity> {

    static{
        if (Skript.methodExists(Llama.class, "hasCaravanTail")) {
            register(CondHasCaravanTrail.class, PropertyType.HAVE, "[a] caravan trail", "livingentities");
        }
    }

    @Override
    public boolean check(LivingEntity entity) {
        if (entity instanceof Llama){
            return ((Llama) entity).hasCaravanTail();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "caravan trail";
    }
}
