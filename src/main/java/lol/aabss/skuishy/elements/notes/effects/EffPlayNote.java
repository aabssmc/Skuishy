package lol.aabss.skuishy.elements.notes.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;

@Name("Notes - Play Note")
@Description("Plays a note block note.")
@Examples({
        "play note E flat with instrument banjo at player for player"
})
@Since("1.6")
public class EffPlayNote extends Effect {

    static{
        Skript.registerEffect(EffPlayNote.class,
                "play [note] %note% with [instrument] %instrument% at [location] %locations% for %players%"
        );
    }

    private Expression<Note> note;
    private Expression<Instrument> instrument;
    private Expression<Location> locations;
    private Expression<Player> players;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        note = (Expression<Note>) exprs[0];
        instrument = (Expression<Instrument>) exprs[1];
        locations = (Expression<Location>) exprs[2];
        players = (Expression<Player>) exprs[3];
        return true;
    }

    @Override
    protected void execute(@NotNull Event event) {
        for (final Location l : locations.getArray(event)){
            for (final Player p : players.getArray(event)){
                Instrument inst = instrument.getSingle(event);
                Note note = this.note.getSingle(event);
                if (inst != null && note != null){
                    p.playNote(l, inst, note);
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "play note";
    }
}
