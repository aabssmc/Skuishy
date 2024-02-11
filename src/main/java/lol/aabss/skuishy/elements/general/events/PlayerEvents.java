package lol.aabss.skuishy.elements.general.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.destroystokyo.paper.event.player.PlayerAttackEntityCooldownResetEvent;
import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.destroystokyo.paper.event.player.PlayerReadyArrowEvent;
import io.papermc.paper.event.block.PlayerShearBlockEvent;
import io.papermc.paper.event.player.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

public class PlayerEvents extends SkriptEvent {

    static{
// player leash entity
        Skript.registerEvent("player leash entity", PlayerEvents.class, PlayerLeashEntityEvent.class,
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
        Skript.registerEvent("player attack entity cool[ ]down reset", PlayerEvents.class, PlayerAttackEntityCooldownResetEvent.class,
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
        Skript.registerEvent("player bed fail enter", PlayerEvents.class, PlayerBedFailEnterEvent.class,
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
            Skript.registerEvent("player exp cooldown change", PlayerEvents.class, PlayerExpCooldownChangeEvent.class,
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
            Skript.registerEvent("player fail move", PlayerEvents.class, PlayerFailMoveEvent.class,
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
        Skript.registerEvent("player flower pot manipulate", PlayerEvents.class, PlayerFlowerPotManipulateEvent.class,
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
        Skript.registerEvent("player harvest block", PlayerEvents.class, PlayerHarvestBlockEvent.class,
                        "[player] harvest[ed] block"
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
        Skript.registerEvent("player item cooldown", PlayerEvents.class, PlayerItemCooldownEvent.class,
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
            Skript.registerEvent("player item frame change", PlayerEvents.class, PlayerItemFrameChangeEvent.class,
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
        Skript.registerEvent("player lectern page change", PlayerEvents.class, PlayerLecternPageChangeEvent.class,
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
        Skript.registerEvent("player loom pattern select", PlayerEvents.class, PlayerLoomPatternSelectEvent.class,
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

// player name entity
        Skript.registerEvent("player name entity", PlayerEvents.class, PlayerNameEntityEvent.class,
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
            Skript.registerEvent("player open sign", PlayerEvents.class, PlayerOpenSignEvent.class,
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
        Skript.registerEvent("player post respawn", PlayerEvents.class, PlayerPostRespawnEvent.class,
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

// player ready arrow
        Skript.registerEvent("player ready arrow", PlayerEvents.class, PlayerReadyArrowEvent.class,
                        "[player] ready arrow"
                )
                .description("Called when a player readies an arrow.")
                .examples("on ready arrow:")
                .since("2.0");
        EventValues.registerEventValue(PlayerReadyArrowEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerReadyArrowEvent e) {
                return e.getPlayer();
            }
        }, 0);

// player recipe book settings change
        if (Skript.classExists("org.bukkit.event.player.PlayerRecipeBookSettingsChangeEvent")) {
            Skript.registerEvent("recipe book settings change", PlayerEvents.class, PlayerRecipeBookSettingsChangeEvent.class,
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
        Skript.registerEvent("player shear block", PlayerEvents.class, PlayerShearBlockEvent.class,
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

// player show entity
        if (Skript.classExists("org.bukkit.event.player.PlayerShowEntityEvent")) {
            Skript.registerEvent("player show entity", PlayerEvents.class, PlayerShowEntityEvent.class,
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

// player stonecutter recipe select
        Skript.registerEvent("player stonecutter recipe select", PlayerEvents.class, PlayerStonecutterRecipeSelectEvent.class,
                        "[player] stone[ ]cutter recipe select[ed]"
                )
                .description("Called when a player selects a recipe in a stonecutter.")
                .examples("on stonecutter recipe select:")
                .since("2.0");
        EventValues.registerEventValue(PlayerStonecutterRecipeSelectEvent.class, Player.class, new Getter<>() {
            @Override
            public Player get(PlayerStonecutterRecipeSelectEvent e) {
                return e.getPlayer();
            }
        }, 0);

// player take lectern book
        Skript.registerEvent("player take lectern book", PlayerEvents.class, PlayerTakeLecternBookEvent.class,
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

// player track entity
        if (Skript.classExists("io.papermc.paper.event.player.PlayerTrackEntityEvent")) {
            Skript.registerEvent("player track entity", PlayerEvents.class, PlayerTrackEntityEvent.class,
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

// player untrack entity
        if (Skript.classExists("io.papermc.paper.event.player.PlayerUntrackEntityEvent")) {
            Skript.registerEvent("player untrack entity", PlayerEvents.class, PlayerUntrackEntityEvent.class,
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

// pre player attack entity
        if (Skript.classExists("io.papermc.paper.event.player.PrePlayerAttackEntityEvent")) {
            Skript.registerEvent("pre player attack entity", PlayerEvents.class, PrePlayerAttackEntityEvent.class,
                            "pre[ |-]player attack[ed] entity"
                    )
                    .description("Called before a player attacks an entity.")
                    .examples("on pre player attack entity:")
                    .since("2.0");
            EventValues.registerEventValue(PrePlayerAttackEntityEvent.class, Player.class, new Getter<>() {
                @Override
                public Player get(PrePlayerAttackEntityEvent e) {
                    return e.getPlayer();
                }
            }, 0);
            EventValues.registerEventValue(PrePlayerAttackEntityEvent.class, Entity.class, new Getter<>() {
                @Override
                public Entity get(PrePlayerAttackEntityEvent e) {
                    return e.getAttacked();
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
        return "player event";
    }
}
