package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.SimpleEntityEffect;
import org.bukkit.entity.Allay;

@Name("Allay - Dancing")
@Description("Makes an allay start/stop dancing.")
@Examples({
        "make {_allay} allay dance"
})
@Since("2.8")
public class EffAllayDancing extends SimpleEntityEffect<Allay> {

    static {
        Skript.registerEffect(EffAllayDancing.class,
                "make %entities% (allay dance|(start|:stop) allay dancing)"
        );
    }

    @Override
    protected void execute(Allay allay) {
        if (tags.contains("stop")) {
            allay.startDancing();
        } else {
            allay.stopDancing();
        }
    }
}
