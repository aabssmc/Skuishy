package lol.aabss.skuishy.elements.entities.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Axolotl;

@Name("Axolotl - Is Playing Dead")
@Description("True if the axolotl is playing dead.")
@Examples({
        "if {_axolotl} is playing dead:",
        "\tset playing dead state of {_axolotl} to false"
})
@Since("2.8")
public class CondIsAxolotlPlayingDead extends EntityCondition<Axolotl> {

    static {
        register(CondIsAxolotlPlayingDead.class, "[axolotl] playing dead", "entities");
    }

    @Override
    protected boolean run(Axolotl axolotl) {
        return axolotl.isPlayingDead();
    }

}
