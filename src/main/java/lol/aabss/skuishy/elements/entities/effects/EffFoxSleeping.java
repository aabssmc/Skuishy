package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Fox;
import org.bukkit.event.Event;

@Name("Fox - Sleep State")
@Description("Sets the sleeping state of a fox.")
@Examples({
        "set fox sleep state of {_fox} to true"
})
@Since("2.8")
public class EffFoxSleeping extends EntityEffect<Fox> {

    static {
        Skript.registerEffect(EffFoxSleeping.class,
                "set [fox] sleep[ing] [state|mode] of %entities% to %boolean%",
                "set %entities%'[s] [fox] sleep[ing] [state|mode] to %boolean%"
        );
    }

    @Override
    protected void execute(Fox fox, Event event) {
        if (exprs.length >= 2 && exprs[1].getSingle(event) != null) {
            Boolean bool = (Boolean) exprs[1].getSingle(event);
            if (bool == null) return;
            fox.setSleeping(bool);
        }
    }
}
