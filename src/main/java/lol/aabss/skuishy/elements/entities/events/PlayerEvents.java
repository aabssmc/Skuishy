package lol.aabss.skuishy.elements.entities.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.destroystokyo.paper.event.player.PlayerAttackEntityCooldownResetEvent;
import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import io.papermc.paper.event.block.PlayerShearBlockEvent;
import io.papermc.paper.event.player.*;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEvents extends SkriptEvent {

    static{
// player leash entity
        Skript.registerEvent("Player - Leash Entity", PlayerEvents.class, PlayerLeashEntityEvent.class,
                        "[player] leash[ed] entity"
                )
                .description("Called when a player leashes an entity.")
                .examples("on player leash entity:")
                .since("2.0");
        EventValues.registerEventValue(PlayerLeashEntityEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerLeashEntityEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerLeashEntityEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(PlayerLeashEntityEvent e) {
                return e.getEntity();
            }
        }, 0);

// player attack entity cooldown reset
        Skript.registerEvent("Player - Attack Cooldown Reset", PlayerEvents.class, PlayerAttackEntityCooldownResetEvent.class,
                        "[player] attack [entity] cool[ ]down reset"
                )
                .description("Called when a player's attack entity cooldown is reset.")
                .examples("on attack cooldown reset:")
                .since("2.0");
        EventValues.registerEventValue(PlayerAttackEntityCooldownResetEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerAttackEntityCooldownResetEvent e) {
                return e.getPlayer();
            }
        }, 0);

// player bed fail enter
        Skript.registerEvent("Player - Bed Enter Fail", PlayerEvents.class, PlayerBedFailEnterEvent.class,
                        "[player] bed fail[ed] enter",
                "[player] enter bed fail[ed]",
                "[player] bed enter fail[ed]"
                )
                .description("Called when a player fails to enter a bed.")
                .examples("on player bed fail enter:")
                .since("2.0");
        EventValues.registerEventValue(PlayerBedFailEnterEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerBedFailEnterEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerBedFailEnterEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(PlayerBedFailEnterEvent e) {
                return e.getBed();
            }
        }, 0);

// player exp cooldown change
        if (Skript.classExists("org.bukkit.event.player.PlayerExpCooldownChangeEvent")) {
            Skript.registerEvent("Player - Exp Cooldown Change", PlayerEvents.class, PlayerExpCooldownChangeEvent.class,
                            "[player] [e]xp[erience] cool[ ]down change"
                    )
                    .description("Called when a player's experience cooldown changes.")
                    .examples("on exp cooldown change:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerExpCooldownChangeEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerExpCooldownChangeEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerExpCooldownChangeEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(PlayerExpCooldownChangeEvent e) {
                    return e.getNewCooldown();
                }
            }, 1);
        }

// player fail move
        if (Skript.classExists("io.papermc.paper.event.player.PlayerFailMoveEvent")) {
            Skript.registerEvent("Player - Fail Move", PlayerEvents.class, PlayerFailMoveEvent.class,
                            "[player] fail[ed] mov(e|ing)"
                    )
                    .description("Called when a player fails to move.")
                    .examples("on fail move:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerFailMoveEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerFailMoveEvent e) {
                    return e.getPlayer();
                }
            }, 0);
        }

// player flower pot manipulate
        Skript.registerEvent("Player - Flower Pot Manipulate", PlayerEvents.class, PlayerFlowerPotManipulateEvent.class,
                        "[player] flower[ ]pot manipulate"
                )
                .description("Called when a player manipulates a flower pot.")
                .examples("on flower pot manipulate:")
                .since("2.0");
        EventValues.registerEventValue(PlayerFlowerPotManipulateEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerFlowerPotManipulateEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerFlowerPotManipulateEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(PlayerFlowerPotManipulateEvent e) {
                return e.getFlowerpot();
            }
        }, 0);
        EventValues.registerEventValue(PlayerFlowerPotManipulateEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(PlayerFlowerPotManipulateEvent e) {
                return e.getItem();
            }
        }, 0);

// player harvest block
        Skript.registerEvent("Player - Harvest Block", PlayerEvents.class, PlayerHarvestBlockEvent.class,
                        "[player] harvest[ed] [block]"
                )
                .description("Called when a player harvests a block.")
                .examples("on harvest block:")
                .since("2.0");
        EventValues.registerEventValue(PlayerHarvestBlockEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerHarvestBlockEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerHarvestBlockEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(PlayerHarvestBlockEvent e) {
                return e.getHarvestedBlock();
            }
        }, 0);

// player item cooldown
        Skript.registerEvent("Player - Item Cooldown", PlayerEvents.class, PlayerItemCooldownEvent.class,
                        "[player] item cool[ ]down"
                )
                .description("Called when a player's item cooldown changes.")
                .examples("on item cooldown:")
                .since("2.0");
        EventValues.registerEventValue(PlayerItemCooldownEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerItemCooldownEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerItemCooldownEvent.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(PlayerItemCooldownEvent e) {
                return e.getCooldown();
            }
        }, 0);

// player item frame change
        if (Skript.classExists("io.papermc.paper.event.player.PlayerItemFrameChangeEvent")) {
            Skript.registerEvent("Player - Item Frame Change", PlayerEvents.class, PlayerItemFrameChangeEvent.class,
                            "[player] item[ ]frame chang(e[d]|ing)"
                    )
                    .description("Called when a player changes an item frame.")
                    .examples("on  item frame change:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerItemFrameChangeEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerItemFrameChangeEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerItemFrameChangeEvent.class, ItemFrame.class, new Getter<>() {
                @Override
                public ItemFrame get(PlayerItemFrameChangeEvent e) {
                    return e.getItemFrame();
                }
            }, 0);
        }
// player lectern page change
        Skript.registerEvent("Player - Lectern Page Change", PlayerEvents.class, PlayerLecternPageChangeEvent.class,
                        "[player] lectern page change[d]"
                )
                .description("Called when a player changes a lectern page.")
                .examples("on lectern page change:")
                .since("2.0");
        EventValues.registerEventValue(PlayerLecternPageChangeEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerLecternPageChangeEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerLecternPageChangeEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(PlayerLecternPageChangeEvent e) {
                return e.getLectern().getBlock();
            }
        }, 0);
        EventValues.registerEventValue(PlayerLecternPageChangeEvent.class, Integer.class, new Getter<>() {
            @Override
            public Integer get(PlayerLecternPageChangeEvent e) {
                return e.getNewPage();
            }
        }, 0);

// player loom pattern select
        Skript.registerEvent("Player - Loom Pattern Select", PlayerEvents.class, PlayerLoomPatternSelectEvent.class,
                        "[player] loom pattern select[ed]"
                )
                .description("Called when a player selects a pattern in a loom.")
                .examples("on loom pattern select:")
                .since("2.0");
        EventValues.registerEventValue(PlayerLoomPatternSelectEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerLoomPatternSelectEvent e) {
                return e.getPlayer();
            }
        }, 0);

// player main hand change
        Skript.registerEvent("Player - Main Hand Change", PlayerEvents.class, PlayerChangedMainHandEvent.class,
                        "[player] main[(-| )]hand (switch|swap|change)"
                )
                .description("Called when a player changes their main hand in the client settings.")
                .examples("on main hand change:", "\tif event-string is \"left\":", "\t\tsend \"eww! weirdo!!!\" to player")
                .since("1.3");
        EventValues.registerEventValue(PlayerChangedMainHandEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerChangedMainHandEvent e) {
                return e.getPlayer();
            }
        }, 0);

        EventValues.registerEventValue(PlayerChangedMainHandEvent.class, String.class, new Getter<>() {
            @Override
            public String get(PlayerChangedMainHandEvent e) {
                return e.getMainHand().toString().toLowerCase();
            }
        }, 0);

// player name entity
        Skript.registerEvent("Player - Name Entity", PlayerEvents.class, PlayerNameEntityEvent.class,
                        "[player] name[d] entity"
                )
                .description("Called when a player names an entity.")
                .examples("on name entity:")
                .since("2.0");
        EventValues.registerEventValue(PlayerNameEntityEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerNameEntityEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerNameEntityEvent.class, Entity.class, new Getter<>() {
            @Override
            public Entity get(PlayerNameEntityEvent e) {
                return e.getEntity();
            }
        }, 0);

// player open sign
        if (Skript.classExists("io.papermc.paper.event.player.PlayerOpenSignEvent")) {
            Skript.registerEvent("Player - Open Sign", PlayerEvents.class, PlayerOpenSignEvent.class,
                            "[player] open[ed] sign"
                    )
                    .description("Called when a player opens a sign.")
                    .examples("on open sign:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerOpenSignEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerOpenSignEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerOpenSignEvent.class, Block.class, new Getter<>() {
                @Override
                public Block get(PlayerOpenSignEvent e) {
                    return e.getSign().getBlock();
                }
            }, 0);
        }

// player post respawn
        Skript.registerEvent("Player - Post Respawn", PlayerEvents.class, PlayerPostRespawnEvent.class,
                        "[player] post respawn[ed]"
                )
                .description("Called after a player respawns.")
                .examples("on post respawn:")
                .since("2.0");
        EventValues.registerEventValue(PlayerPostRespawnEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerPostRespawnEvent e) {
                return e.getPlayer();
            }
        }, 0);

// player recipe book settings change
        if (Skript.classExists("org.bukkit.event.player.PlayerRecipeBookSettingsChangeEvent")) {
            Skript.registerEvent("Player - Recipe Book Settings Edit", PlayerEvents.class, PlayerRecipeBookSettingsChangeEvent.class,
                            "[player] recipe[ ]book settings [change|edit]"
                    )
                    .description("Called when a player's recipe book settings change.")
                    .examples("on recipe book settings change:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerRecipeBookSettingsChangeEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerRecipeBookSettingsChangeEvent e) {
                    return e.getPlayer();
                }
            }, 0);
        }

// player shear block
        Skript.registerEvent("Player - Shear Block", PlayerEvents.class, PlayerShearBlockEvent.class,
                        "[player] shear[ed] block"
                )
                .description("Called when a player shears a block.")
                .examples("on shear block:")
                .since("2.0");
        EventValues.registerEventValue(PlayerShearBlockEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerShearBlockEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerShearBlockEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(PlayerShearBlockEvent e) {
                return e.getBlock();
            }
        }, 0);

// player shield break
        Skript.registerEvent("Player - Shield Break", PlayerEvents.class, PlayerItemCooldownEvent.class,
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

        if (Skript.classExists("org.bukkit.event.player.PlayerShowEntityEvent")) {
            Skript.registerEvent("Player - Show Entity", PlayerEvents.class, PlayerShowEntityEvent.class,
                            "[player] show[n] entity"
                    )
                    .description("Called when a player shows an entity.")
                    .examples("on show entity:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerShowEntityEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerShowEntityEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerShowEntityEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(PlayerShowEntityEvent e) {
                    return e.getEntity();
                }
            }, 0);
        }

        Skript.registerEvent("Player - Take Lectern Book", PlayerEvents.class, PlayerTakeLecternBookEvent.class,
                        "[player] (take|took) lectern book"
                )
                .description("Called when a player takes a book from a lectern.")
                .examples("on take lectern book:")
                .since("2.0");
        EventValues.registerEventValue(PlayerTakeLecternBookEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerTakeLecternBookEvent e) {
                return e.getPlayer();
            }
        }, 0);
        EventValues.registerEventValue(PlayerTakeLecternBookEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(PlayerTakeLecternBookEvent e) {
                return e.getLectern().getBlock();
            }
        }, 0);
        EventValues.registerEventValue(PlayerTakeLecternBookEvent.class, ItemStack.class, new Getter<>() {
            @Override
            public ItemStack get(PlayerTakeLecternBookEvent e) {
                return e.getBook();
            }
        }, 0);

        if (Skript.classExists("io.papermc.paper.event.player.PlayerTrackEntityEvent")) {
            Skript.registerEvent("Player - Track Entity", PlayerEvents.class, PlayerTrackEntityEvent.class,
                            "[player] track[ed] entity"
                    )
                    .description("Called when a player tracks an entity.")
                    .examples("on [player] track entity:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerTrackEntityEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerTrackEntityEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerTrackEntityEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(PlayerTrackEntityEvent e) {
                    return e.getEntity();
                }
            }, 0);
        }

        if (Skript.classExists("io.papermc.paper.event.player.PlayerUntrackEntityEvent")) {
            Skript.registerEvent("Player - Untrack Entity", PlayerEvents.class, PlayerUntrackEntityEvent.class,
                            "[player] untrack[ed] entity"
                    )
                    .description("Called when a player untracks an entity.")
                    .examples("on untrack entity:")
                    .since("2.0");
            EventValues.registerEventValue(PlayerUntrackEntityEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerUntrackEntityEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerUntrackEntityEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(PlayerUntrackEntityEvent e) {
                    return e.getEntity();
                }
            }, 0);
        }

        if (Skript.classExists("org.bukkit.event.player.PlayerStatisticIncrementEvent")) {
            Skript.registerEvent("Player - Statistic Increment", PlayerEvents.class, PlayerStatisticIncrementEvent.class,
                            "[player] stat[istic] increment"
                    )
                    .description("Called when a player statistic is incremented.\n" +
                            "This event is not called for some high frequency statistics, e. g. movement based statistics.")
                    .examples("on statistic increment:")
                    .since("2.8");
            EventValues.registerEventValue(PlayerStatisticIncrementEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PlayerStatisticIncrementEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerStatisticIncrementEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(PlayerStatisticIncrementEvent e) {
                    return e.getNewValue();
                }
            }, 1);
            EventValues.registerEventValue(PlayerStatisticIncrementEvent.class, Integer.class, new Getter<>() {
                @Override
                public Integer get(PlayerStatisticIncrementEvent e) {
                    return e.getPreviousValue();
                }
            }, -1);
            EventValues.registerEventValue(PlayerStatisticIncrementEvent.class, org.bukkit.entity.EntityType.class, new Getter<>() {
                @Override
                public org.bukkit.entity.EntityType get(PlayerStatisticIncrementEvent e) {
                    return e.getEntityType();
                }
            }, -1);
            EventValues.registerEventValue(PlayerStatisticIncrementEvent.class, Material.class, new Getter<>() {
                @Override
                public Material get(PlayerStatisticIncrementEvent e) {
                    return e.getMaterial();
                }
            }, -1);
            EventValues.registerEventValue(PlayerStatisticIncrementEvent.class, Statistic.class, new Getter<>() {
                @Override
                public Statistic get(PlayerStatisticIncrementEvent e) {
                    return e.getStatistic();
                }
            }, -1);
        }
        if (Skript.classExists("org.bukkit.event.player.PlayerVelocityEvent")) {
            Skript.registerEvent("Player - Velocity", PlayerEvents.class, PlayerVelocityEvent.class,
                            "player velocity"
                    )
                    .description("Called when the velocity of a player changes.")
                    .examples("on player velocity:")
                    .since("2.8");
            EventValues.registerEventValue(PlayerVelocityEvent.class, Player.class, new Getter<>() {
                @Override
                public @Nullable Player get(PlayerVelocityEvent event) {
                    return event.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerVelocityEvent.class, Vector.class, new Getter<>() {
                @Override
                public @Nullable Vector get(PlayerVelocityEvent event) {
                    return event.getVelocity();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.player.PlayerBucketEntityEvent")) {
            Skript.registerEvent("Player - Bucket Entity", PlayerEvents.class, PlayerBucketEntityEvent.class,
                            "player bucket entity"
                    )
                    .description("Called when a player captures a player in a bucket.")
                    .examples("on player bucket entity:")
                    .since("2.8");
            EventValues.registerEventValue(PlayerBucketEntityEvent.class, Player.class, new Getter<>() {
                @Override
                public @Nullable Player get(PlayerBucketEntityEvent event) {
                    return event.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerBucketEntityEvent.class, Entity.class, new Getter<>() {
                @Override
                public @Nullable Entity get(PlayerBucketEntityEvent event) {
                    return event.getEntity();
                }
            }, 0);
            EventValues.registerEventValue(PlayerBucketEntityEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public @Nullable ItemStack get(PlayerBucketEntityEvent event) {
                    return event.getEntityBucket();
                }
            }, 0);
            EventValues.registerEventValue(PlayerBucketEntityEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public @Nullable ItemStack get(PlayerBucketEntityEvent event) {
                    return event.getOriginalBucket();
                }
            }, -1);
        }
        if (Skript.classExists("org.bukkit.event.player.PlayerHideEntityEvent")) {
            Skript.registerEvent("Player - Hide Entity", PlayerEvents.class, PlayerHideEntityEvent.class,
                            "player hide entity"
                    )
                    .description("""
                            Called when a visible entity is hidden from a player.
                            This event is only called when the entity's visibility status is actually changed.
                            This event is called regardless of if the entity was within tracking range.""")
                    .examples("on player hide entity:")
                    .since("2.8");
            EventValues.registerEventValue(PlayerHideEntityEvent.class, Player.class, new Getter<>() {
                @Override
                public @Nullable Player get(PlayerHideEntityEvent event) {
                    return event.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerHideEntityEvent.class, Entity.class, new Getter<>() {
                @Override
                public @Nullable Entity get(PlayerHideEntityEvent event) {
                    return event.getEntity();
                }
            }, 0);
        }
        if (Skript.classExists("org.bukkit.event.player.PlayerArmorStandManipulateEvent")) {
            Skript.registerEvent("Player - Armor Stand Manipulate", PlayerEvents.class, PlayerArmorStandManipulateEvent.class,
                            "[player] armor[ |-]stand manipulate"
                    )
                    .description("Called when a player interacts with an armor stand and will either swap, retrieve or place an item")
                    .examples("on armorstand manipulate:")
                    .since("2.8");
            EventValues.registerEventValue(PlayerArmorStandManipulateEvent.class, Player.class, new Getter<>() {
                @Override
                public @Nullable Player get(PlayerArmorStandManipulateEvent event) {
                    return event.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PlayerArmorStandManipulateEvent.class, Entity.class, new Getter<>() {
                @Override
                public @Nullable Entity get(PlayerArmorStandManipulateEvent event) {
                    return event.getRightClicked();
                }
            }, 0);
            EventValues.registerEventValue(PlayerArmorStandManipulateEvent.class, ItemStack.class, new Getter<>() {
                @Override
                public @Nullable ItemStack get(PlayerArmorStandManipulateEvent event) {
                    return event.getPlayerItem();
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
        if (event instanceof PlayerItemCooldownEvent) {
            if (((PlayerItemCooldownEvent) event).getType() == Material.SHIELD) {
                return ((PlayerItemCooldownEvent) event).getCooldown() > 0;
            }
            return false;
        }
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        if (event != null) return event.getEventName();
        return "player event";
    }
}
