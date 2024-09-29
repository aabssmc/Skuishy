package lol.aabss.skuishy.elements.entities.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Sniffer;

@Name("Sniffer - Can Dig")
@Description("Returns true if the sniffer can dig.")
@Examples({
        "if {_sniffer} can dig:"
})
@Since("2.8")
public class CondCanSnifferDig extends EntityCondition<Sniffer> {

    static {
        register(CondCanSnifferDig.class, PropertyCondition.PropertyType.CAN, "[sniffer] dig", "entities");
    }

    @Override
    protected boolean run(Sniffer sniffer) {
        return sniffer.canDig();
    }
}
