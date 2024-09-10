package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Allay;
import org.bukkit.event.Event;

@Name("Allay - Duplicate")
@Description("Makes an allay duplicate itself.")
@Examples({
        "make {_allay} duplicate"
})
@Since("2.8")
public class EffDuplicateAllay extends EntityEffect<Allay> {

    static {
        Skript.registerEffect(EffDuplicateAllay.class,
                "make %entities% duplicate",
                "duplicate [allay] %entities%"
        );
    }

    @Override
    protected void execute(Allay allay, Event event) {
        allay.duplicateAllay();
    }
}
