package lol.aabss.skuishy.elements;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import io.papermc.paper.datapack.Datapack;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Entity;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class Types {
    static{
        Classes.registerClass(new ClassInfo<>(Note.class, "note")
                .user("notes?")
                .name("note")
                .description("Represents a note block note.")
                .since("1.6")
                .parser(new Parser<>() {

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
                .parser(new Parser<>() {

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
                .parser(new Parser<>() {

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
        Classes.registerClass(new ClassInfo<>(Datapack.class, "datapack")
                    .user("datapacks?")
                    .name("datapack")
                    .description("Represents a datapack.")
                    .since("1.7")
                    .parser(new Parser<>() {

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
        EnumUtils<SpawnCategory> categorys = new EnumUtils<>(SpawnCategory.class, "spawncategory");
        Classes.registerClass(new ClassInfo<>(SpawnCategory.class, "spawncategory")
                .user("spawn ?categor(y|ies)")
                .name("spawn category")
                .description("Represents a spawn category.")
                .since("1.7.5")
                .parser(new Parser<>() {

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

        EnumUtils<PotionType> potiontypes = new EnumUtils<>(PotionType.class, "potionitemtype");
        Classes.registerClass(new ClassInfo<>(PotionType.class, "potionitemtype")
                .user("potion[ ]item[ ]type")
                .name("Potion Item Type")
                .description("Represents a potion item type that matches each potion state that can be obtained from the Creative mode inventory.")
                .since("2.1")
                .parser(new Parser<>() {

                    @Override
                    @Nullable
                    public PotionType parse(@NotNull String input, @NotNull ParseContext context) {
                        return potiontypes.parse(input);
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(PotionType type) {
                        return type.name().replaceAll("_", " ").toLowerCase();
                    }

                    @Override
                    public @NotNull String toString(PotionType type, int flags) {
                        return toVariableNameString(type);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(Permission.class, "permission")
                .user("permissions?")
                .name("Permissions - Permission")
                .description("Represents a permission.")
                .since("2.1")
                .parser(new Parser<>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toVariableNameString(Permission perm) {
                        return perm.getName();
                    }

                    @Override
                    public @NotNull String toString(Permission perm, int flags) {
                        return toVariableNameString(perm);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(PermissionAttachment.class, "permissionattachment")
                .user("permission ?attachments?")
                .name("Permissions - Permission Attachment")
                .description("Represents a permission attachment.")
                .since("2.1")
                .parser(new Parser<>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toVariableNameString(PermissionAttachment perm) {
                        return "Permission: " + perm.getPermissions() + " Player: " + ((Entity) perm.getPermissible()).getName();
                    }

                    @Override
                    public @NotNull String toString(PermissionAttachment perm, int flags) {
                        return toVariableNameString(perm);
                    }
                })
        );
    }
}
