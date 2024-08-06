package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.command.EffectCommandEvent;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
import com.destroystokyo.paper.loottable.LootableInventory;
import com.destroystokyo.paper.loottable.LootableInventoryReplenishEvent;
import io.papermc.paper.event.server.WhitelistStateUpdateEvent;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.FluidLevelChangeEvent;
import org.bukkit.event.block.InventoryBlockStartEvent;
import org.bukkit.event.command.UnknownCommandEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.RemoteServerCommandEvent;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

        if (Skript.classExists("com.destroystokyo.paper.event.inventory.PrepareResultEvent")) {
            Skript.registerEvent("Other - Prepare Result Event", OtherEvents.class, PrepareResultEvent.class,
                            "prepare [inventory] result"
                    )
                    .description("Called when an item is put in an inventory containing a result slot.")
                    .examples("on prepare result:")
                    .since("2.8");
            EventValues.registerEventValue(PrepareResultEvent.class, Inventory.class, new Getter<>() {
                @Override
                public Inventory get(PrepareResultEvent e) {
                    return e.getInventory();
                }
            }, 0);
        }

        if (Skript.classExists("com.destroystokyo.paper.event.inventory.PrepareResultEvent")) {
            Skript.registerEvent("Other - Prepare Result Event", OtherEvents.class, TimeSkipEvent.class,
                            "prepare [inventory] result"
                    )
                    .description("Called when an item is put in an inventory containing a result slot.")
                    .examples("on prepare result:")
                    .since("2.8");
            EventValues.registerEventValue(TimeSkipEvent.class, World.class, new Getter<>() {
                @Override
                public World get(TimeSkipEvent e) {
                    return e.getWorld();
                }
            }, 0);

            EventValues.registerEventValue(TimeSkipEvent.class, TimeSkipEvent.SkipReason.class, new Getter<>() {
                @Override
                public TimeSkipEvent.SkipReason get(TimeSkipEvent e) {
                    return e.getSkipReason();
                }
            }, 0);

            EventValues.registerEventValue(TimeSkipEvent.class, Long.class, new Getter<>() {
                @Override
                public Long get(TimeSkipEvent e) {
                    return e.getSkipAmount();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.server.PluginEnableEvent")) {
            Skript.registerEvent("Plugin - Player Enable Event", OtherEvents.class, PluginEnableEvent.class,
                            "plugin enable[d]"
                    )
                    .description("Called when a plugin is enabled.")
                    .examples("on plugin enable:")
                    .since("2.8");
            EventValues.registerEventValue(PluginEnableEvent.class, Plugin.class, new Getter<>() {
                @Override
                public Plugin get(PluginEnableEvent e) {
                    return e.getPlugin();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.server.PluginDisableEvent")) {
            Skript.registerEvent("Plugin - Player Enable Event", OtherEvents.class, PluginDisableEvent.class,
                            "plugin disable[d]"
                    )
                    .description("Called when a plugin is disabled.")
                    .examples("on plugin disable:")
                    .since("2.8");
            EventValues.registerEventValue(PluginDisableEvent.class, Plugin.class, new Getter<>() {
                @Override
                public Plugin get(PluginDisableEvent e) {
                    return e.getPlugin();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.server.RemoteServerCommandEvent")) {
            Skript.registerEvent("Other - Remote Server Command Event", OtherEvents.class, RemoteServerCommandEvent.class,
                            "remove [server] command"
                    )
                    .description("Called when a command is received over RCON.")
                    .examples("on remote command:")
                    .since("2.8");
            EventValues.registerEventValue(RemoteServerCommandEvent.class, String.class, new Getter<>() {
                @Override
                public String get(RemoteServerCommandEvent e) {
                    return e.getCommand();
                }
            }, 0);
            EventValues.registerEventValue(RemoteServerCommandEvent.class, CommandSender.class, new Getter<>() {
                @Override
                public CommandSender get(RemoteServerCommandEvent e) {
                    return e.getSender();
                }
            }, 0);
        }

        if (Skript.classExists("org.bukkit.event.inventory.PrepareGrindstoneEvent")) {
            Skript.registerEvent("Inventory - Prepare Grindstone Event", OtherEvents.class, PrepareGrindstoneEvent.class,
                            "prepare[d] grindstone",
                    "grindstone prepare[d]"
                    )
                    .description("Called when an item is put in a slot for repair or unenchanting in a grindstone.")
                    .examples("on prepare grindston:")
                    .since("2.8");
            EventValues.registerEventValue(PrepareGrindstoneEvent.class, Inventory.class, new Getter<>() {
                @Override
                public Inventory get(PrepareGrindstoneEvent e) {
                    return e.getInventory();
                }
            }, 0);
            EventValues.registerEventValue(PrepareGrindstoneEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(PrepareGrindstoneEvent e) {
                    return e.getResult();
                }
            }, 0);
            EventValues.registerEventValue(PrepareGrindstoneEvent.class, Entity[].class, new Getter<>() {
                @Override
                public Entity[] get(PrepareGrindstoneEvent e) {
                    return e.getViewers().toArray(HumanEntity[]::new);
                }
            }, 0);
        }

        if (Skript.classExists("org.bukkit.event.inventory.InventoryCreativeEvent")) {
            Skript.registerEvent("Inventory - Inventory Creative Event", OtherEvents.class, InventoryCreativeEvent.class,
                            "inventory creative"
                    )
                    .description("This event is called when a player in creative mode puts down or picks up an item in their inventory / hotbar and when they drop items from their Inventory while in creative mode.")
                    .examples("on inventory creative:")
                    .since("2.8");
            EventValues.registerEventValue(InventoryCreativeEvent.class, Inventory.class, new Getter<>() {
                @Override
                public Inventory get(InventoryCreativeEvent e) {
                    return e.getInventory();
                }
            }, 0);
            EventValues.registerEventValue(InventoryCreativeEvent.class, ClickType.class, new Getter<>() {
                @Override
                public ClickType get(InventoryCreativeEvent e) {
                    return e.getClick();
                }
            }, 0);
            EventValues.registerEventValue(InventoryCreativeEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(InventoryCreativeEvent e) {
                    return e.getCurrentItem();
                }
            }, 0);
            EventValues.registerEventValue(InventoryCreativeEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(InventoryCreativeEvent e) {
                    return e.getWhoClicked();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.inventory.SmithItemEvent")) {
            Skript.registerEvent("Inventory - Smith Item Event", OtherEvents.class, SmithItemEvent.class,
                            "item[s] smith[ed]",
                    "smith[ed] item[s]"
                    )
                    .description("Called when the recipe of an Item is completed inside a smithing table.")
                    .examples("on item smith:")
                    .since("2.8");
            EventValues.registerEventValue(SmithItemEvent.class, Inventory.class, new Getter<>() {
                @Override
                public Inventory get(SmithItemEvent e) {
                    return e.getInventory();
                }
            }, 0);
            EventValues.registerEventValue(SmithItemEvent.class, ClickType.class, new Getter<>() {
                @Override
                public ClickType get(SmithItemEvent e) {
                    return e.getClick();
                }
            }, 0);
            EventValues.registerEventValue(SmithItemEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(SmithItemEvent e) {
                    return e.getCurrentItem();
                }
            }, 0);
            EventValues.registerEventValue(SmithItemEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(SmithItemEvent e) {
                    return e.getWhoClicked();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.inventory.FurnaceExtractEvent")) {
            Skript.registerEvent("Inventory - Furnace Extract Event", OtherEvents.class, FurnaceExtractEvent.class,
                            "furnace extract[ed]"
                    )
                    .description("This event is called when a player takes items out of a furnace-like block such as a Furnace, Smoker, or BlastFurnace.")
                    .examples("on furnace extract:")
                    .since("2.8");
            EventValues.registerEventValue(FurnaceExtractEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(FurnaceExtractEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(FurnaceExtractEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(FurnaceExtractEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(FurnaceExtractEvent.class, Material.class, new Getter<>() {
                @Override
                public Material get(FurnaceExtractEvent e) {
                    return e.getItemType();
                }
            }, 0);
            EventValues.registerEventValue(FurnaceExtractEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(FurnaceExtractEvent e) {
                    return e.getExpToDrop();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.inventory.PrepareSmithingEvent")) {
            Skript.registerEvent("Inventory - Prepare Smithing Event", OtherEvents.class, PrepareSmithingEvent.class,
                            "prepare[d] smith[ing]",
                    "smith[ing] prepare[d]"
                    )
                    .description("Called when an item is put in a slot for upgrade by a Smithing Table.")
                    .examples("on prepare smith:")
                    .since("2.8");
            EventValues.registerEventValue(PrepareSmithingEvent.class, Inventory.class, new Getter<>() {
                @Override
                public Inventory get(PrepareSmithingEvent e) {
                    return e.getInventory();
                }
            }, 0);
            EventValues.registerEventValue(PrepareSmithingEvent.class, Entity[].class, new Getter<>() {
                @Override
                public Entity[] get(PrepareSmithingEvent e) {
                    return e.getViewers().toArray(Entity[]::new);
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.inventory.FurnaceStartSmeltEvent")) {
            Skript.registerEvent("Inventory - Furnace Start Smelt", OtherEvents.class, FurnaceStartSmeltEvent.class,
                            "[furnace] start[ed] smelt[ed|ing]"
                    )
                    .description("Called when any of the furnace-like blocks start smelting.\n" +
                            "Furnace-like blocks are Furnace, Smoker, BlastFurnace.")
                    .examples("on start smelt:")
                    .since("2.8");
            EventValues.registerEventValue(FurnaceStartSmeltEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(FurnaceStartSmeltEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(FurnaceStartSmeltEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(FurnaceStartSmeltEvent e) {
                    return e.getSource();
                }
            }, 0);
            EventValues.registerEventValue(FurnaceStartSmeltEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(FurnaceStartSmeltEvent e) {
                    return e.getTotalCookTime();
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        if (event != null) return event.getEventName();
        return "other event";
    }
}
