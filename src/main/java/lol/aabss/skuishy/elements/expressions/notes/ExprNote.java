package lol.aabss.skuishy.elements.expressions.notes;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Note;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;
import java.util.Objects;

@SuppressWarnings("NullableProblems")
@Name("Notes - Note")
@Description("Represents a note.")
@Examples({
        "play note E flat with instrument banjo at player for player"
})
@Since("1.6")

public class ExprNote extends SimpleExpression<Note> {
    static{
        Skript.registerExpression(ExprNote.class, Note.class, ExpressionType.COMBINED,
                "%tone% (:sharp|:flat|:natural) [at [octave] %-integer%]"
        );
    }

    private Expression<Note.Tone> tone;
    private String accidental;
    private Expression<Integer> octave;

    @Override
    protected @Nullable Note [] get(@NotNull Event e) {
        Integer oct;
        if (octave == null){
            oct = 0;
        }
        else{
            oct = octave.getSingle(e);
        }
        Note.Tone tone = this.tone.getSingle(e);
        if (oct != null && tone != null) {
            if (Objects.equals(accidental, "sharp")) {
                Note note = Note.sharp(oct, tone);
                return new Note[]{note};
            } else if (Objects.equals(accidental, "flat")) {
                Note note = Note.flat(oct, tone);
                return new Note[]{note};
            } else if (Objects.equals(accidental, "natural")) {
                Note note = Note.natural(oct, tone);
                return new Note[]{note};
            } else {
                return new Note[]{};
            }
        }
        return new Note[]{};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Note> getReturnType() {
        return Note.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "note";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parse) {
        tone = (Expression<Note.Tone>) exprs[0];
        accidental = parse.hasTag("sharp") ? "sharp" : parse.hasTag("flat") ? "flat" : parse.hasTag("natural") ? "natural" : null;
        octave = (Expression<Integer>) exprs[1];
        if (octave != null){
            int oct = Integer.parseInt(octave.toString());
            if (oct == 0 || oct == 1) {
                return true;
            } else if (oct > 1){
                Skript.error("Octaves can not be greater than 1");
                return false;
            }
            else {
                Skript.error("Octaves can not be less than 0");
                return false;
            }
        }
        else{
            return true;
        }
    }
}
