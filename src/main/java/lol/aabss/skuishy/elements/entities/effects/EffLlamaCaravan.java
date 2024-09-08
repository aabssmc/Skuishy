package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Llama;
import org.bukkit.event.Event;

@Name("Llama - Caravan")
@Description("Makes a llama join/leave a caravan..")
@Examples({
        "make {_llama} leave caravan"
})
@Since("2.8")
public class EffLlamaCaravan extends EntityEffect<Llama> {

    static {
        Skript.registerEffect(EffLlamaCaravan.class,
                "make %entities% join %entity% caravan",
                "make %entities% leave caravan"
        );
    }

    @Override
    protected void execute(Llama llama, Event event) {
        if (exprs.length >= 2) {
            Llama caravan = (Llama) exprs[1].getSingle(event);
            if (caravan == null) return;
            llama.joinCaravan(caravan);
        } else {
            llama.leaveCaravan();
        }
    }

}
