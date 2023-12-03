package lol.aabss.skuishy.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import io.papermc.paper.datapack.Datapack;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.generator.structure.Structure;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class Types {
    static{
        Classes.registerClass(new ClassInfo<>(Note.class, "note")
                .user("notes?")
                .name("note")
                .description("Represents a note block note.")
                .since("1.6")
                .parser(new Parser<Note>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toVariableNameString(Note note) {
                        int octave = note.getOctave();
                        String accidental = (note.isSharped() ? "#" : "");
                        return note.getTone() + accidental + " at octave " + octave;
                    }

                    @Override
                    public @NotNull String toString(Note note, int flags) {
                        return toVariableNameString(note);
                    }
                })
        );

        EnumUtils<Note.Tone> tones = new EnumUtils<>(Note.Tone.class, "tone");
        Classes.registerClass(new ClassInfo<>(Note.Tone.class, "tone")
                .user("tones?")
                .name("tone")
                .description("Represents a note block note's tone.")
                .since("1.6")
                .parser(new Parser<Note.Tone>() {

                    @Override
                    @Nullable
                    public Note.Tone parse(@NotNull String input, @NotNull ParseContext context) {
                        return tones.parse(input);
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(Note.Tone tone) {
                        return tone.name();
                    }

                    @Override
                    public @NotNull String toString(Note.Tone tone, int flags) {
                        return toVariableNameString(tone);
                    }
                })
        );

        EnumUtils<Instrument> instruments = new EnumUtils<>(Instrument.class, "instrument");
        Classes.registerClass(new ClassInfo<>(Instrument.class, "instrument")
                .user("instruments?")
                .name("instrument")
                .description("Represents a note block instrument.")
                .since("1.6")
                .parser(new Parser<Instrument>() {

                    @Override
                    @Nullable
                    public Instrument parse(@NotNull String input, @NotNull ParseContext context) {
                        return instruments.parse(input);
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(Instrument instrument) {
                        return instrument.name().toLowerCase().replaceAll("_", " ");
                    }

                    @Override
                    public @NotNull String toString(Instrument instrument, int flags) {
                        return toVariableNameString(instrument);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(Hologram.class, "hologram")
                    .user("holograms?")
                    .name("hologram")
                    .description("Represents a decent hologram hologram.")
                    .since("1.6")
                    .parser(new Parser<Hologram>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Hologram holo) {
                            return holo.getName().toLowerCase().replaceAll("_", " ");
                        }

                        @Override
                        public @NotNull String toString(Hologram holo, int flags) {
                            return toVariableNameString(holo);
                        }
                    })
        );
        Classes.registerClass(new ClassInfo<>(Structure.class, "generatedstructure")
                    .user("generated ?structures?")
                    .name("generated structure")
                    .description("Represents a generated structure.")
                    .since("1.7")
                    .parser(new Parser<Structure>() {
                        @Override
                        @Nullable
                        public Structure parse(@NotNull String input, @NotNull ParseContext context) {
                            return switch (input) {
                                case "ancient city" -> Structure.ANCIENT_CITY;
                                case "bastion remnant" -> Structure.BASTION_REMNANT;
                                case "buried treasure" -> Structure.BURIED_TREASURE;
                                case "desert pyramid" -> Structure.DESERT_PYRAMID;
                                case "end city" -> Structure.END_CITY;
                                case "fortress", "nether fortress" -> Structure.FORTRESS;
                                case "igloo" -> Structure.IGLOO;
                                case "jungle pyramid" -> Structure.JUNGLE_PYRAMID;
                                case "mansion", "woodland mansion" -> Structure.MANSION;
                                case "mineshaft" -> Structure.MINESHAFT;
                                case "mineshaft mesa", "mesa mineshaft" -> Structure.MINESHAFT_MESA;
                                case "monument", "ocean monument" -> Structure.MONUMENT;
                                case "nether fossil" -> Structure.NETHER_FOSSIL;
                                case "ocean ruin cold", "cold ocean ruin" -> Structure.OCEAN_RUIN_COLD;
                                case "ocean ruin warm", "warm ocean ruin" -> Structure.OCEAN_RUIN_WARM;
                                case "pillager outpost" -> Structure.PILLAGER_OUTPOST;
                                case "ruined portal", "ruinedportal" -> Structure.RUINED_PORTAL;
                                case "ruined portal desert", "desert ruined portal", "ruinedportal desert", "desert ruinedportal" -> Structure.RUINED_PORTAL_DESERT;
                                case "ruined portal jungle", "jungle ruined portal", "ruinedportal jungle", "jungle ruinedportal" -> Structure.RUINED_PORTAL_JUNGLE;
                                case "ruined portal mountain", "mountain ruined portal", "ruinedportal mountain", "mountain ruinedportal" -> Structure.RUINED_PORTAL_MOUNTAIN;
                                case "ruined portal nether", "nether ruined portal", "ruinedportal nether", "nether ruinedportal" -> Structure.RUINED_PORTAL_NETHER;
                                case "ruined portal ocean", "ocean ruined portal", "ruinedportal ocean", "ocean ruinedportal" -> Structure.RUINED_PORTAL_OCEAN;
                                case "ruined portal swamp", "swamp ruined portal", "ruinedportal swamp", "swamp ruinedportal" -> Structure.RUINED_PORTAL_SWAMP;
                                case "shipwreck" -> Structure.SHIPWRECK;
                                case "shipwreck beached", "beached shipwreck" -> Structure.SHIPWRECK_BEACHED;
                                case "stronghold" -> Structure.STRONGHOLD;
                                case "swamp hut", "witch village" -> Structure.SWAMP_HUT;
                                case "trail ruins" -> Structure.TRAIL_RUINS;
                                case "village desert", "desert village" -> Structure.VILLAGE_DESERT;
                                case "village plains", "plains village" -> Structure.VILLAGE_PLAINS;
                                case "village savanna", "savanna village" -> Structure.VILLAGE_SAVANNA;
                                case "village snowy", "snowy village" -> Structure.VILLAGE_SNOWY;
                                case "village taiga", "taiga village" -> Structure.VILLAGE_TAIGA;
                                default -> null;
                            };
                        }

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return true;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Structure structure) {
                            return structure.getStructureType().toString().toLowerCase().replaceAll("_", " ");
                        }

                        @Override
                        public @NotNull String toString(Structure structure, int flags) {
                            return toVariableNameString(structure);
                        }
                    })
        );
        Classes.registerClass(new ClassInfo<>(MemoryKey.class, "memorykey")
                    .user("memory ?keys?")
                    .name("memory key")
                    .description("Represents a living entity's memory key.")
                    .since("1.7")
                    .parser(new Parser<MemoryKey<?>>() {
                        @Override
                        @Nullable
                        public MemoryKey<?> parse(@NotNull String input, @NotNull ParseContext context) {
                            return switch (input) {
                                case "admiring disabled" -> MemoryKey.ADMIRING_DISABLED;
                                case "admiring item" -> MemoryKey.ADMIRING_ITEM;
                                case "angry at" -> MemoryKey.ANGRY_AT;
                                case "golem detected recently" -> MemoryKey.GOLEM_DETECTED_RECENTLY;
                                case "has hunting cooldown" -> MemoryKey.HAS_HUNTING_COOLDOWN;
                                case "home" -> MemoryKey.HOME;
                                case "hunted recently" -> MemoryKey.HUNTED_RECENTLY;
                                case "is tempted" -> MemoryKey.IS_TEMPTED;
                                case "item pickup cooldown ticks" -> MemoryKey.ITEM_PICKUP_COOLDOWN_TICKS;
                                case "job site" -> MemoryKey.JOB_SITE;
                                case "last slept" -> MemoryKey.LAST_SLEPT;
                                case "last woken" -> MemoryKey.LAST_WOKEN;
                                case "last worked at poi" -> MemoryKey.LAST_WORKED_AT_POI;
                                case "liked noteblock cooldown ticks" -> MemoryKey.LIKED_NOTEBLOCK_COOLDOWN_TICKS;
                                case "liked noteblock position" -> MemoryKey.LIKED_NOTEBLOCK_POSITION;
                                case "liked player" -> MemoryKey.LIKED_PLAYER;
                                case "long jump cooling down" -> MemoryKey.LONG_JUMP_COOLING_DOWN;
                                case "meeting point" -> MemoryKey.MEETING_POINT;
                                case "play dead ticks" -> MemoryKey.PLAY_DEAD_TICKS;
                                case "potential job site" -> MemoryKey.POTENTIAL_JOB_SITE;
                                case "ram cooldown ticks" -> MemoryKey.RAM_COOLDOWN_TICKS;
                                case "sniffer explored positions" -> MemoryKey.SNIFFER_EXPLORED_POSITIONS;
                                case "temptation cooldown ticks" -> MemoryKey.TEMPTATION_COOLDOWN_TICKS;
                                case "universal anger" -> MemoryKey.UNIVERSAL_ANGER;
                                default -> null;
                            };
                        }

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return true;
                        }

                        @Override
                        public @NotNull String toVariableNameString(MemoryKey memoryKey) {
                            return memoryKey.toString().toLowerCase().replaceAll("_", " ");
                        }

                        @Override
                        public @NotNull String toString(MemoryKey memoryKey, int flags) {
                            return toVariableNameString(memoryKey);
                        }
                    })
        );
        Classes.registerClass(new ClassInfo<>(Datapack.class, "datapack")
                    .user("datapacks?")
                    .name("datapack")
                    .description("Represents a datapack.")
                    .since("1.7")
                    .parser(new Parser<Datapack>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Datapack data) {
                            return data.getName().toLowerCase().replaceAll("_", " ");
                        }

                        @Override
                        public @NotNull String toString(Datapack data, int flags) {
                            return toVariableNameString(data);
                        }
                    })
        );
        EnumUtils<SpawnCategory> categorys = new EnumUtils<>(SpawnCategory.class, "spawn category");
        Classes.registerClass(new ClassInfo<>(SpawnCategory.class, "spawncategry")
                .user("spawn ?categor(y|ies)")
                .name("spawn category")
                .description("Represents a spawn category.")
                .since("1.8")
                .parser(new Parser<SpawnCategory>() {

                    @Override
                    @Nullable
                    public SpawnCategory parse(@NotNull String input, @NotNull ParseContext context) {
                        return categorys.parse(input);
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(SpawnCategory category) {
                        return category.name().replaceAll("_", " ").toLowerCase();
                    }

                    @Override
                    public @NotNull String toString(SpawnCategory category, int flags) {
                        return toVariableNameString(category);
                    }
                })
        );
    }
}
