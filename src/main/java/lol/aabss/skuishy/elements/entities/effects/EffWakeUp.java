package lol.aabss.skuishy.elements.entities.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.SimpleEntityEffect;
import org.bukkit.entity.Player;

@Name("Player - Wake Up")
@Description("Makes a player wake up and optionally sets their spawn.")
@Examples({
        "make player wake up and set their spawn"
})
@Since("2.0")
public class EffWakeUp extends SimpleEntityEffect<Player> {

    static {
        Skript.registerEffect(EffWakeUp.class,
                "make %players% wake up [spawn:and set the[ir] spawn [location]]"
        );
    }

    @Override
    protected void execute(Player player) {
        player.wakeup(tags.contains("spawn"));
    }
}
