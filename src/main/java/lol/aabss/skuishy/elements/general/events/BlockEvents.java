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
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.event.world.AsyncStructureGenerateEvent;
import org.bukkit.event.world.AsyncStructureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
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

        if (Skript.classExists("org.bukkit.event.block.BlockDispenseArmorEvent")){
            Skript.registerEvent("Block - Dispense Armor", BlockEvents.class, BlockDispenseArmorEvent.class,
                            "[block] dispense[d] armor"
                    )
                    .description("Called when an equippable item is dispensed from a block and equipped on a nearby entity.\n" +
                            "If a Block Dispense Armor event is cancelled, the equipment will not be equipped on the target entity.")
                    .examples("on dispense armor:")
                    .since("2.8");
            EventValues.registerEventValue(BlockDispenseArmorEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(BlockDispenseArmorEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(BlockDispenseArmorEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(BlockDispenseArmorEvent e) {
                    return e.getItem();
                }
            }, 0);
            EventValues.registerEventValue(BlockDispenseArmorEvent.class, Vector.class, new Getter<>() {
                @Override
                public Vector get(BlockDispenseArmorEvent e) {
                    return e.getVelocity();
                }
            }, 0);
            EventValues.registerEventValue(BlockDispenseArmorEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(BlockDispenseArmorEvent e) {
                    return e.getTargetEntity();
                }
            }, 0);
        }

        if (Skript.classExists("org.bukkit.event.block.BlockMultiPlaceEvent")){
            Skript.registerEvent("Block - Mutli Place", BlockEvents.class, BlockMultiPlaceEvent.class,
                            "[block] multi[-| ]place"
                    )
                    .description("Fired when a single block placement action of a player triggers the creation of multiple blocks(e. g. placing a bed block).")
                    .examples("on multi place:")
                    .since("2.8");
            EventValues.registerEventValue(BlockMultiPlaceEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(BlockMultiPlaceEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(BlockMultiPlaceEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(BlockMultiPlaceEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(BlockMultiPlaceEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(BlockMultiPlaceEvent e) {
                    return e.getItemInHand();
                }
            }, 0);
            EventValues.registerEventValue(BlockMultiPlaceEvent.class, Boolean.class, new Getter<>() {
                @Override
                public Boolean get(BlockMultiPlaceEvent e) {
                    return e.canBuild();
                }
            }, 0);
        }

        if (Skript.classExists("org.bukkit.event.block.CampfireStartEvent")){
            Skript.registerEvent("Block - Campfire Start Event", BlockEvents.class, CampfireStartEvent.class,
                            "[block] campfire start[ed]"
                    )
                    .description("Called when a Campfire starts to cook.\uD83D\uDD25")
                    .examples("on campfire start:")
                    .since("2.8");
            EventValues.registerEventValue(CampfireStartEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(CampfireStartEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(CampfireStartEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(CampfireStartEvent e) {
                    return e.getSource();
                }
            }, 0);
            EventValues.registerEventValue(CampfireStartEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(CampfireStartEvent e) {
                    return e.getTotalCookTime();
                }
            }, 0);
        }

        if (Skript.classExists("org.bukkit.event.block.BrewingStartEvent")){
            Skript.registerEvent("Block - Brewing Start Event", BlockEvents.class, BrewingStartEvent.class,
                            "[block] brew[ing [stand]] start[ed]"
                    )
                    .description("Called when a brewing stand starts to brew.")
                    .examples("on brewing stand start:")
                    .since("2.8");
            EventValues.registerEventValue(BrewingStartEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(BrewingStartEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(BrewingStartEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public ItemStack get(BrewingStartEvent e) {
                    return e.getSource();
                }
            }, 0);
            EventValues.registerEventValue(BrewingStartEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(BrewingStartEvent e) {
                    return e.getTotalBrewTime();
                }
            }, 0);
        }

        if (Skript.classExists("org.bukkit.event.block.EntityBlockFormEvent")){
            Skript.registerEvent("Block - Entity Block Form", BlockEvents.class, EntityBlockFormEvent.class,
                            "[block] form[ed] event"
                    )
                    .description("""
                            Called when a block is formed by entities.
                            Examples:
                            - Snow formed by a org. bukkit. entity. Snowman.
                            - Frosted Ice formed by the Frost Walker enchantment.""")
                    .examples("on brewing stand start:")
                    .since("2.8");
            EventValues.registerEventValue(EntityBlockFormEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(EntityBlockFormEvent e) {
                    return e.getBlock();
                }
            }, 0);
            EventValues.registerEventValue(EntityBlockFormEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(EntityBlockFormEvent e) {
                    return e.getEntity();
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
