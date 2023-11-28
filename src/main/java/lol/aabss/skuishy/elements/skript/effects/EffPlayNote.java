package lol.aabss.skuishy.elements.skript.effects;

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

import javax.annotation.Nullable;

@Name("Notes - Play Note")
@Description("Plays a note block note.")
@Examples({
        "play note E flat with instrument banjo at player for player"
})
@Since("1.6")

public class EffPlayNote extends Effect {

    static{
        Skript.registerEffect(EffPlayNote.class,
                "play [note [block sound]] %note% with instrument %instrument% at %locations% for %players%"
        );
    }

    Expression<Note> note;
    Expression<Instrument> instrument;
    Expression<Location> locations;
    Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        note = (Expression<Note>) exprs[0];
        instrument = (Expression<Instrument>) exprs[1];
        locations = (Expression<Location>) exprs[2];
        players = (Expression<Player>) exprs[3];
        return true;
    }

    @Override
    protected void execute(@NotNull Event e) {
        for (final Location l : locations.getArray(e)){
            for (final Player p : players.getArray(e)){
                Instrument inst = instrument.getSingle(e);
                Note note1 = note.getSingle(e);
                assert inst != null;
                assert note1 != null;
                p.playNote(l, inst, note1);
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "play " +
                instrument.toString(e, debug) + " " +
                note.toString(e, debug) + " at " +
                locations.toString(e, debug) + " for " +
                players.toString(e, debug);
    }
}
