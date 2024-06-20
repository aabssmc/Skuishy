package lol.aabss.skuishy.elements.skins.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.command.EffectCommandEvent;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.SkinWrapper;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Skins - Player Head")
@Description("Sends a player's head as a string.")
@Examples({
        "send face of \"aabss\" # works with strings too",
        "send no layer face of \"aabss\" # sends face without the outer layer"
})
@Since("2.6")
public class EffSendHead extends Effect {

    static {
        Skript.registerEffect(EffSendHead.class,
                "show [:offline] (head|face) of %offlineplayers/strings% [without:[the] [outer] layer] [to %-commandsenders%]"
        );
    }

    private Expression<Object> player;
    private Expression<CommandSender> receiver;
    private boolean offline;
    private boolean without;

    @Override
    protected void execute(@NotNull Event event) {
        for (Object o : player.getArray(event)) {
            SkinWrapper.sendHead((o instanceof OfflinePlayer ? ((OfflinePlayer) o).getName() : o.toString()), !without, offline).whenComplete((string, throwable) -> {
                if (receiver != null) {
                    for (CommandSender sender : receiver.getArray(event)) {
                        sender.sendMessage(string);
                    }
                } else {
                    if (event instanceof PlayerEvent playerEvent){
                        playerEvent.getPlayer().sendMessage(string);
                    } else if (event instanceof EntityEvent entityEvent){
                        entityEvent.getEntity().sendMessage(string);
                    } else if (event instanceof EffectCommandEvent effectEvent){
                        effectEvent.getSender().sendMessage(string);
                    }
                }
            });
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        player = (Expression<Object>) expressions[0];
        receiver = (Expression<CommandSender>) expressions[1];
        without = parseResult.hasTag("without");
        offline = parseResult.hasTag("offline");
        return true;
    }
}
