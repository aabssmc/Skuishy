package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Allay;
import org.bukkit.event.Event;

@Name("Allay - Dancing")
@Description("Makes an allay start/stop dancing.")
@Examples({
        "make {_allay} allay dance"
})
@Since("2.8")
public class EffAllayDancing extends EntityEffect<Allay> {

    static {
        Skript.registerEffect(EffAllayDancing.class,
                "make %entities% (allay dance|(start|:stop) allay dancing)"
        );
    }

    @Override
    protected void execute(Allay allay, Event event) {
        if (tags.contains("stop")) {
            allay.startDancing();
        } else {
            allay.stopDancing();
        }
    }
}
