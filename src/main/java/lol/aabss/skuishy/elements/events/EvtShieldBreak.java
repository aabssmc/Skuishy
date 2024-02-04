package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.papermc.paper.event.player.PlayerItemCooldownEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Player - On Shield Break")
@Description("Thrown when someone's shield gets broken.")
@Examples({
        "on shield break:",
        "\tbroadcast \"%player%'s shield broke!!\""
})
@Since("1.5, 2.1 (UPDATE)")
public class EvtShieldBreak extends SkriptEvent {

    static{
        Skript.registerEvent("on shield break", SimpleEvent.class, PlayerItemCooldownEvent.class,
                "[player] shield (disable|break)"
        );
        EventValues.registerEventValue(PlayerItemCooldownEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerItemCooldownEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?> @NonNull [] args, int matchedPattern, SkriptParser.@NonNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NonNull Event e) {
        if (e instanceof PlayerItemCooldownEvent) {
            if (((PlayerItemCooldownEvent) e).getType() == Material.SHIELD) {
                return ((PlayerItemCooldownEvent) e).getCooldown() > 0;
            }
        }
        return false;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "shield disable event";
    }


}
