package lol.aabss.skuishy.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.generator.structure.Structure;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static lol.aabss.skuishy.Skuishy.instance;

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
        if (instance.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
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
        }
    }
}
