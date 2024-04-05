package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
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
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class EvtShieldBreak extends SkriptEvent {

    static{
        Skript.registerEvent("Player - Shield Break", SimpleEvent.class, PlayerItemCooldownEvent.class,
                "[player] shield (disable|break)"
        )
                .description("Thrown when someone's shield gets broken.")
                .examples("on shield break:", "\tbroadcast \"%player%'s shield broke!!\"")
                .since("1.5, 2.1 (UPDATE)");
        EventValues.registerEventValue(PlayerItemCooldownEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerItemCooldownEvent e) {
                return e.getPlayer();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        if (e instanceof PlayerItemCooldownEvent) {
            if (((PlayerItemCooldownEvent) e).getType() == Material.SHIELD) {
                return ((PlayerItemCooldownEvent) e).getCooldown() > 0;
            }
        }
        return false;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "shield disable event";
    }


}
