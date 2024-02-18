package lol.aabss.skuishy.elements.notes;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class Types {
    static {
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
                    public Note.@Nullable Tone parse(@NotNull String input, @NotNull ParseContext context) {
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
    }
}
