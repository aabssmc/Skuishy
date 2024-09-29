package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.EnderCrystal;

@Name("Ender Crystal - Is Showing Bottom")
@Description("True if the bottom of the end crystal is shown.")
@Examples({
        "if {_crystal} is showing bottom:",
        "\tset showing bottom of {_crystal} to false"
})
@Since("2.8")
public class CondIsCrystalShowingBottom extends EntityCondition<EnderCrystal> {

    static {
        register(CondIsCrystalShowingBottom.class, "[[end[er]] crystal] showing bottom", "entities");
    }

    @Override
    protected boolean run(EnderCrystal enderCrystal) {
        return enderCrystal.isShowingBottom();
    }

}
