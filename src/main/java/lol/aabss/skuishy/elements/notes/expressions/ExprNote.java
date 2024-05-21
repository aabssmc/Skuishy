package lol.aabss.skuishy.elements.notes.expressions;

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

import org.jetbrains.annotations.Nullable;
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
    protected Note @Nullable [] get(@NotNull Event event) {
        Integer oct;
        if (octave == null){
            oct = 0;
        } else{
            Integer octave = this.octave.getSingle(event);
            oct = Objects.requireNonNullElse(octave, 0);
        }
        Note.Tone tone = this.tone.getSingle(event);
        if (tone != null) {
            if (Objects.equals(accidental, "sharp")) {
                if (oct == 0 || oct == 1 || oct == 2) {
                    Note note = Note.sharp(oct, tone);
                    return new Note[]{note};
                }
            } else if (Objects.equals(accidental, "flat")) {
                if (oct == 0 || oct == 1) {
                    Note note = Note.flat(oct, tone);
                    return new Note[]{note};
                }
            } else if (Objects.equals(accidental, "natural")) {
                if (oct == 0 || oct == 1) {
                    Note note = Note.natural(oct, tone);
                    return new Note[]{note};
                }
            }
        }
        return new Note[]{null};
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
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "note";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parse) {
        tone = (Expression<Note.Tone>) exprs[0];
        accidental = parse.hasTag("sharp") ? "sharp" : parse.hasTag("flat") ? "flat" : parse.hasTag("natural") ? "natural" : null;
        octave = (Expression<Integer>) exprs[1];
        return true;
    }
}
