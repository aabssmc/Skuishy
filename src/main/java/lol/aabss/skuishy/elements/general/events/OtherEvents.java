package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.command.EffectCommandEvent;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.destroystokyo.paper.loottable.LootableInventory;
import com.destroystokyo.paper.loottable.LootableInventoryReplenishEvent;
import io.papermc.paper.event.server.WhitelistStateUpdateEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.block.FluidLevelChangeEvent;
import org.bukkit.event.block.InventoryBlockStartEvent;
import org.bukkit.event.command.UnknownCommandEvent;
import org.bukkit.event.inventory.HopperInventorySearchEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class OtherEvents extends SkriptEvent {
    static{
// effect command
        Skript.registerEvent("Other - Effect Command", OtherEvents.class, EffectCommandEvent.class,
                        "[s(k|c)ript] effect command"
                )
                .description("Called when someone uses a skript effect command.")
                .examples(
                        "on effect command:",
                        "\tif event-string contains \"ip\":",
                        "\t\tcancel event"
                )
                .since("1.7");
        EventValues.registerEventValue(EffectCommandEvent.class, String.class, new Getter<>() {
            @Override
            public String get(EffectCommandEvent e) {
                return e.getCommand();
            }
        }, 0);
        EventValues.registerEventValue(EffectCommandEvent.class, CommandSender.class, new Getter<>() {
            @Override
            public CommandSender get(EffectCommandEvent e) {
                return e.getSender();
            }
        }, 0);

        // fluid level change
        Skript.registerEvent("Other - Fluid Level Change", OtherEvents.class, FluidLevelChangeEvent.class,
                        "fluid level change[d]"
                )
                .description("Called when the fluid level of a block changes.")
                .examples("on fluid level change:")
                .since("2.0");
        EventValues.registerEventValue(FluidLevelChangeEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(FluidLevelChangeEvent e) {
                return e.getBlock();
            }
        }, 0);

// hopper inventory search
        if (Skript.classExists("org.bukkit.event.inventory.HopperInventorySearchEvent")) {
            Skript.registerEvent("Other - Hopper Inventory Search", OtherEvents.class, HopperInventorySearchEvent.class,
                            "hopper inventory search[ed]",
                    "hopper search[ed] inventory"
                    )
                    .description("Called when a hopper searches for an inventory to pull items from.")
                    .examples("on hopper inventory search:")
                    .since("2.0");
            EventValues.registerEventValue(HopperInventorySearchEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(HopperInventorySearchEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(HopperInventorySearchEvent.class, Inventory.class, new Getter<>() {
                @Override
                public Inventory get(HopperInventorySearchEvent e) {
                    return e.getInventory();
                }
            }, 0);
        }

// inventory block start
        if (Skript.classExists("org.bukkit.event.block.InventoryBlockStartEvent")) {
            Skript.registerEvent("Other - Inventory Block Start", OtherEvents.class, InventoryBlockStartEvent.class,
                            "inventory block start"
                    )
                    .description("Called when an inventory block starts processing.")
                    .examples("on inventory block start:")
                    .since("2.0");
            EventValues.registerEventValue(InventoryBlockStartEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(InventoryBlockStartEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(InventoryBlockStartEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(InventoryBlockStartEvent e) {
                    return e.getSource();
                }
            }, 0);
        }

// lootable inventory replenish
        Skript.registerEvent("Other - LootTable Inventory Replenish", OtherEvents.class, LootableInventoryReplenishEvent.class,
                        "loot[ |-]table [inventory] (replenish|refill)"
                )
                .description("Called when a lootable inventory replenishes its contents.")
                .examples("on lootable inventory replenish:")
                .since("2.0");
        EventValues.registerEventValue(LootableInventoryReplenishEvent.class, LootableInventory.class, new Getter<>() {
            @Override
            public LootableInventory get(LootableInventoryReplenishEvent e) {
                return e.getInventory();
            }
        }, 0);

// unknown command
        Skript.registerEvent("Other - Unknown Command", OtherEvents.class, UnknownCommandEvent.class,
                        "unknown command"
                )
                .description("Thrown when someone executes a command that is not defined.")
                .examples("on unknown command:", "\tset unknown command message to \"that doesn't exist\"")
                .since("1.3");
        EventValues.registerEventValue(UnknownCommandEvent.class, CommandSender.class, new Getter<>() {
            @Override
            public CommandSender get(UnknownCommandEvent e) {
                return e.getSender();
            }
        }, 0);

        EventValues.registerEventValue(UnknownCommandEvent.class, String.class, new Getter<>() {
            @Override
            public String get(UnknownCommandEvent e) {
                return e.getCommandLine();
            }
        }, 0);

// whitelist state update
        if (Skript.classExists("io.papermc.paper.event.server.WhitelistStateUpdateEvent")) {
            Skript.registerEvent("Other - Whitelist State Update", OtherEvents.class, WhitelistStateUpdateEvent.class,
                            "whitelist (mode|state) update[d]",
                    "whitelist update[d] (mode|state)"
                    )
                    .description("Called when the server's whitelist state is updated.")
                    .examples("on whitelist state update:")
                    .since("2.0");
            EventValues.registerEventValue(WhitelistStateUpdateEvent.class, OfflinePlayer.class, new Getter<>() {
                @Override
                public OfflinePlayer get(WhitelistStateUpdateEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(WhitelistStateUpdateEvent.class, Boolean.class, new Getter<>() {
                @Override
                public Boolean get(WhitelistStateUpdateEvent e) {
                    return e.getStatus() == WhitelistStateUpdateEvent.WhitelistStatus.ADDED;
                }
            }, 0);
        }

    }
    @Override
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event event) {
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        if (e != null) return e.getEventName();
        return "other event";
    }
}
