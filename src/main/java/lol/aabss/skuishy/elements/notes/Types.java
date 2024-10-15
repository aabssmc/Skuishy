package lol.aabss.skuishy.elements.notes;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import lol.aabss.skuishy.other.EnumWrapper;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.jetbrains.annotations.NotNull;

public class Types {
    static {
        if (Skript.classExists("org.bukkit.Note") && Classes.getExactClassInfo(Note.class) == null) {
            Classes.registerClass(new ClassInfo<>(Note.class, "note")
                    .user("notes?")
                    .name("Note")
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
        }

        if (Skript.classExists("org.bukkit.Note"/*$Tone*/) && Classes.getExactClassInfo(Note.Tone.class) == null) {
            Classes.registerClass(new EnumWrapper<>(Note.Tone.class).getClassInfo("tone")
                    .user("tones?")
                    .name("Tone")
                    .description("Represents a note block note's tone.")
                    .since("1.6")
            );
        }


        if (Skript.classExists("org.bukkit.Instrument") && Classes.getExactClassInfo(Instrument.class) == null) {
            Classes.registerClass(new EnumWrapper<>(Instrument.class).getClassInfo("instrument")
                    .user("instruments?")
                    .name("Instrument")
                    .description("Represents a note block instrument.")
                    .since("1.6")
            );
        }
    }
}
