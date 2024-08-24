package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Axolotl - Is Playing Dead")
@Description("True if the axolotl is playing dead.")
@Examples({
        "if {_axolotl} is playing dead:",
        "\tset playing dead state of {_axolotl} to false"
})
@Since("2.8")
public class CondIsPlayingDead extends PropertyCondition<Entity> {

    static {
        register(CondIsPlayingDead.class, "playing dead", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof Axolotl) {
            return ((Axolotl) entity).isPlayingDead();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "playing dead";
    }
}
