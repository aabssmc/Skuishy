package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import io.papermc.paper.entity.SchoolableFish;
import lol.aabss.skuishy.other.skript.EntityEffect;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

@Name("Schoolable Fish - Follow Fish")
@Description("Makes a schoolable fish follow or stop following a fish.")
@Examples({
        "make {_fish::1} start following {_fish::2}",
        "make {_fish::2} start following {_fish::1} # lol"
})
@Since("2.8")
public class EffSchoolableFishFollow extends EntityEffect<SchoolableFish> {

    static {
        Skript.registerEffect(EffSchoolableFishFollow.class,
                "make %entities% start following %entity%",
                "make %entities% stop following [the school|%-entity%]"
        );
    }

    @Override
    protected void execute(SchoolableFish schoolableFish, Event event) {
        if (matchedPattern == 0) {
            if (exprs.length >= 1) {
                Entity entity = (Entity) exprs[0];
                if (entity instanceof SchoolableFish) {
                    schoolableFish.startFollowing((SchoolableFish) entity);
                }
            }
        } else {
            schoolableFish.stopFollowing();
        }
    }
}
