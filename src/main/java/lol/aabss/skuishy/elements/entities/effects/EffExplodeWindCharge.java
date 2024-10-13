package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.AbstractWindCharge;
import org.bukkit.event.Event;

@Name("Wind Charge - Explode")
@Description("Forces a wind charge to explode.")
@Examples({
        "make {_charge} windcharge explode"
})
@Since("2.8")
public class EffExplodeWindCharge extends EntityEffect<AbstractWindCharge> {

    static {
        if (Skript.classExists("org.bukkit.entity.AbstractWindCharge")) {
            Skript.registerEffect(EffExplodeWindCharge.class,
                    "explode wind[ ]charge %entities%",
                    "wind[ ]charge explode %entities%",
                    "make %entities% wind[ ]charge explode"
            );
        }
    }

    @Override
    protected void execute(AbstractWindCharge abstractWindCharge, Event event) {
        abstractWindCharge.explode();
    }
}
