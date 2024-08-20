package lol.aabss.skuishy.elements.notes;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.jetbrains.annotations.NotNull;

public class Types {
    static {
        if (Classes.getClassInfoNoError("note") == null) {
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
        }

        if (Classes.getClassInfoNoError("tone") == null) {
            Classes.registerClass(new EnumClassInfo<>(Note.Tone.class, "tone", "tone")
                    .user("tones?")
                    .name("tone")
                    .description("Represents a note block note's tone.")
                    .since("1.6")
            );
        }


        if (Classes.getClassInfoNoError("instrument") == null) {
            Classes.registerClass(new EnumClassInfo<>(Instrument.class, "instrument", "instrument")
                    .user("instruments?")
                    .name("instrument")
                    .description("Represents a note block instrument.")
                    .since("1.6")
            );
        }
    }
}
