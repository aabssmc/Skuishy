package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
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

public class EvtGameruleChange extends SkriptEvent {

    static {
        Skript.registerEvent("World - Gamerule Change", EvtGameruleChange.class, WorldGameRuleChangeEvent.class,
                "game[( |-)]rule change [of %-gamerule%] [in %-world%]",
                "game[( |-)]rule change [in %-world%] [of %-gamerule%]"
        )
                .description("Called when a gamerule is changed.")
                .examples("on gamerule change in \"world\":", "\tif gamerule is doDaylightCyle", "\t\tsend \"%gamerule% is now %event-string%\" to commandsender")
                .since("1.6");
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

    private Literal<World> world;
    private Literal<GameRule<?>> gamerule;

    @Override
    public boolean init(Literal<?> @NotNull [] exprs, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            gamerule = (Literal<GameRule<?>>) exprs[0];
            world = (Literal<World>) exprs[1];
        }
        else{
            world = (Literal<World>) exprs[0];
            gamerule = (Literal<GameRule<?>>) exprs[1];
        }
        return true;
    }

    @Override
    public boolean check(@NotNull Event event) {
        if (event instanceof WorldGameRuleChangeEvent) {
            if (world == null) {
                if (gamerule == null) {
                    return true;
                }
                return gamerule.getSingle(event) == ((WorldGameRuleChangeEvent) event).getGameRule();
            }
            if (((WorldGameRuleChangeEvent) event).getWorld() == world.getSingle(event)) {
                if (gamerule == null) {
                    return true;
                }
                return gamerule.getSingle(event) == ((WorldGameRuleChangeEvent) event).getGameRule();
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public @NotNull String toString(Event event, boolean debug) {
        return "gamerule change event";
    }
}
