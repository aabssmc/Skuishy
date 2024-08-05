package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.papermc.paper.event.block.BlockFailedDispenseEvent;
import io.papermc.paper.event.block.BlockLockCheckEvent;
import io.papermc.paper.event.block.BlockPreDispenseEvent;
import io.papermc.paper.event.block.CompostItemEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.block.SculkBloomEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.event.world.AsyncStructureGenerateEvent;
import org.bukkit.event.world.AsyncStructureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockEvents extends SkriptEvent {

    static{
        // async structure generate
        if (Skript.classExists("org.bukkit.event.world.AsyncStructureGenerateEvent")) {
            Skript.registerEvent("World - Async Structure Generate", BlockEvents.class, AsyncStructureGenerateEvent.class,
                            "[async] structure generate[d]"
                    )
                    .description("Called when a structure generates asynchronously.")
                    .examples("on structure generate:")
                    .since("2.0");
        }

// async structure spawn
        if (Skript.classExists("org.bukkit.event.world.AsyncStructureSpawnEvent")) {
            Skript.registerEvent("World - Async Structure Spawn", BlockEvents.class, AsyncStructureSpawnEvent.class,
                            "[async] structure spawn[ed]"
                    )
                    .description("Called when a structure spawns asynchronously.")
                    .examples("on structure spawn:")
                    .since("2.0");
        }

// block cook
        Skript.registerEvent("Block - Block Cook", BlockEvents.class, BlockCookEvent.class,
                        "[block] cook[ed]"
                )
                .description("Called when a block cooks.")
                .examples("on block cook:")
                .since("2.0");
        EventValues.registerEventValue(BlockCookEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(BlockCookEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(BlockCookEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(BlockCookEvent e) {
                return e.getResult();
            }
        }, 1);
        EventValues.registerEventValue(BlockCookEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(BlockCookEvent e) {
                return e.getSource();
            }
        }, -1);

// block exp
        Skript.registerEvent("Block - Block Experience", BlockEvents.class, BlockExpEvent.class,
                        "block exp[erience]"
                )
                .description("Called when a block generates experience.")
                .examples("on block exp:")
                .since("2.0");
        EventValues.registerEventValue(BlockExpEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(BlockExpEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(BlockExpEvent.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(BlockExpEvent e) {
                return e.getExpToDrop();
            }
        }, 0);

// block failed dispense
        Skript.registerEvent("Block - Dispense Fail", BlockEvents.class, BlockFailedDispenseEvent.class,
                        "[block] fail[ed] dispense[d]"
                )
                .description("Called when a block fails to dispense an item.")
                .examples("on block failed dispense:")
                .since("2.0");
        EventValues.registerEventValue(BlockFailedDispenseEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(BlockFailedDispenseEvent e) {
                return e.getBlock();
            }
        }, 0);

// block lock check
        if (Skript.classExists("io.papermc.paper.event.block.BlockLockCheckEvent")) {
            Skript.registerEvent("Block - Lock Check", BlockEvents.class, BlockLockCheckEvent.class,
                            "[block] lock check",
                            "[block] check lock"
                    )
                    .description("Called when a lock check is performed on a block.")
                    .examples("on block lock check:")
                    .since("2.0");
            EventValues.registerEventValue(BlockLockCheckEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(BlockLockCheckEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(BlockLockCheckEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(BlockLockCheckEvent e) {
                    return e.getPlayer();
                }
            }, 0);
        }

// block pre dispense
        Skript.registerEvent("Block - Pre-Dispense", BlockEvents.class, BlockPreDispenseEvent.class,
                        "[block] pre[( |-)]dispense"
                )
                .description("Called before a block dispenses an item.")
                .examples("on block pre dispense:")
                .since("2.0");
        EventValues.registerEventValue(BlockPreDispenseEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(BlockPreDispenseEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(BlockPreDispenseEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(BlockPreDispenseEvent e) {
                return e.getItemStack();
            }
        }, 0);

// brew
        Skript.registerEvent("Block - Brew", BlockEvents.class, BrewEvent.class,
                        "[brewing[ |-]stand] brew[ing|s]"
                )
                .description("Called when a brewing stand brews.")
                .examples("on brew:")
                .since("2.0");
        EventValues.registerEventValue(BrewEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(BrewEvent e) {
                return e.getBlock();
            }
        }, 0);

// brewing stand fuel
        Skript.registerEvent("Block - Brewing Stand Fuel", BlockEvents.class, BrewingStandFuelEvent.class,
                        "brewing stand fuel[ed]"
                )
                .description("Called when a brewing stand is fueled.")
                .examples("on brewing stand fuel:")
                .since("2.0");
        EventValues.registerEventValue(BrewingStandFuelEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(BrewingStandFuelEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(BrewingStandFuelEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(BrewingStandFuelEvent e) {
                return e.getFuel();
            }
        }, 0);
        EventValues.registerEventValue(BrewingStandFuelEvent.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(BrewingStandFuelEvent e) {
                return e.getFuelPower();
            }
        }, 0);

// cauldron level change
        Skript.registerEvent("Block - Cauldron Level Change", BlockEvents.class, CauldronLevelChangeEvent.class,
                        "cauldron level change[d]"
                )
                .description("Called when the water level in a cauldron changes.")
                .examples("on cauldron level change:")
                .since("2.0");
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(CauldronLevelChangeEvent e) {
                return e.getBlock();
            }
        }, 0);
        EventValues.registerEventValue(CauldronLevelChangeEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(CauldronLevelChangeEvent e) {
                return e.getEntity();
            }
        }, 0);

// compost item
        if (Skript.classExists("io.papermc.paper.event.block.CompostItemEvent")) {
            Skript.registerEvent("Block - Compost Item", BlockEvents.class, CompostItemEvent.class,
                            "[block] compost[ed] item"
                    )
                    .description("Called when an item is composted.")
                    .examples("on compost item:")
                    .since("2.0");
            EventValues.registerEventValue(CompostItemEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(CompostItemEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(CompostItemEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(CompostItemEvent e) {
                    return e.getItem();
                }
            }, 0);
        }

// sculk bloom
        if (Skript.classExists("org.bukkit.event.block.SculkBloomEvent")){
            Skript.registerEvent("Block - Sculk Bloom", BlockEvents.class, SculkBloomEvent.class,
                            "sculk bloom[ing]"
                    )
                    .description("Represents an event triggered when a new cursor is created by a SculkCatalyst.")
                    .examples("on sculk bloom:", "\tbroadcast \"that was really weird, dont do that again\"")
                    .since("1.5");
            EventValues.registerEventValue(SculkBloomEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(SculkBloomEvent e) {
                    return e.getBlock();
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
        return "block event";
    }
}
