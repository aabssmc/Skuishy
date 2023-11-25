package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.papermc.paper.event.world.WorldGameRuleChangeEvent;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@Name("World - On Gamerule Change")
@Description("Called when a player changes their main hand in the client settings.")
@Examples({
        "on gamerule change in \"world\":",
        "\tif gamerule is doDaylightCyle",
        "\t\tsend \"%gamerule% is now %event-string%\" to commandsender"
})
@Since("1.6")

public class EvtGameruleChange extends SkriptEvent {

    static {
        Skript.registerEvent("on gamerule change", EvtGameruleChange.class, WorldGameRuleChangeEvent.class,
                "game[( |-)]rule change [of %-gamerules%] [in %-worlds%]",
                "game[( |-)]rule change [in %-worlds%] [of %-gamerules%]"
        );
        EventValues.registerEventValue(WorldGameRuleChangeEvent.class, World.class, new Getter<>() {
            @Override
            public World get(WorldGameRuleChangeEvent e) {
                return e.getWorld();
            }
        }, 0);

        EventValues.registerEventValue(WorldGameRuleChangeEvent.class, GameRule.class, new Getter<>() {
            @Override
            public GameRule<?> get(WorldGameRuleChangeEvent e) {
                return e.getGameRule();
            }
        }, 0);

        EventValues.registerEventValue(WorldGameRuleChangeEvent.class, String.class, new Getter<>() {
            @Override
            public String get(WorldGameRuleChangeEvent e) {
                return e.getValue();
            }
        }, 0);

        EventValues.registerEventValue(WorldGameRuleChangeEvent.class, CommandSender.class, new Getter<>() {
            @Override
            public CommandSender get(WorldGameRuleChangeEvent e) {
                return e.getCommandSender();
            }
        }, 0);

    }

    Literal<World> worlds;
    Literal<GameRule<?>> gamerules;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Literal<?> @NotNull [] exprs, int matchedPattern, @NotNull SkriptParser.ParseResult parseResult) {
        if (matchedPattern == 0){
            gamerules = (Literal<GameRule<?>>) exprs[0];
            worlds = (Literal<World>) exprs[1];
        }
        else{
            worlds = (Literal<World>) exprs[0];
            gamerules = (Literal<GameRule<?>>) exprs[1];
        }
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        if (e instanceof WorldGameRuleChangeEvent) {
            for (World world : worlds.getArray(e)) {
                if (world.equals(((WorldGameRuleChangeEvent) e).getWorld())) {
                    for (GameRule<?> gameRule : gamerules.getArray(e) ) {
                        if (gameRule.equals(((WorldGameRuleChangeEvent) e).getGameRule())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public @NotNull String toString(Event e, boolean debug) {
        return "gamerule change event";
    }
}
