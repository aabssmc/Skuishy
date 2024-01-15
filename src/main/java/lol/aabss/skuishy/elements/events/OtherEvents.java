package lol.aabss.skuishy.elements.events;

import ch.njol.skript.Skript;
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
import org.bukkit.event.Event;
import org.bukkit.event.block.FluidLevelChangeEvent;
import org.bukkit.event.block.InventoryBlockStartEvent;
import org.bukkit.event.inventory.HopperInventorySearchEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class OtherEvents extends SkriptEvent {
    static{
        // fluid level change
        Skript.registerEvent("fluid level change", OtherEvents.class, FluidLevelChangeEvent.class,
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
        Skript.registerEvent("hopper inventory search", OtherEvents.class, HopperInventorySearchEvent.class,
                        "hopper inventory search"
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

// inventory block start
        Skript.registerEvent("inventory block start", OtherEvents.class, InventoryBlockStartEvent.class,
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

// lootable inventory replenish
        Skript.registerEvent("loot table inventory replenish", OtherEvents.class, LootableInventoryReplenishEvent.class,
                        "loot[ |-]table [inventory] replenish"
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

// whitelist state update
        Skript.registerEvent("whitelist state update", OtherEvents.class, WhitelistStateUpdateEvent.class,
                        "whitelist (mode|state) update[d]"
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
