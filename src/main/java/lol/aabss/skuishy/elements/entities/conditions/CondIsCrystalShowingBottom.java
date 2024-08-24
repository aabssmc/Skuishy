package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

@Name("Ender Crystal - Is Showing Bottom")
@Description("True if the bottom of the end crystal is shown.")
@Examples({
        "if {_crystal} is showing bottom:",
        "\tset showing bottom of {_crystal} to false"
})
@Since("2.8")
public class CondIsCrystalShowingBottom extends PropertyCondition<Entity> {

    static {
        register(CondIsCrystalShowingBottom.class, "showing bottom", "entities");
    }

    @Override
    public boolean check(Entity entity) {
        if (entity instanceof EnderCrystal) {
            return ((EnderCrystal) entity).isShowingBottom();
        }
        return false;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "showing bottom";
    }
}
