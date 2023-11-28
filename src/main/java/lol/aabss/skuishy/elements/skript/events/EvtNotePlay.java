package lol.aabss.skuishy.elements.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.block.NotePlayEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Notes - On Note Play")
@Description("Called when a note block is played.")
@Examples({
        "on note play:",
        "\tbroadcast \"%event-block% has been played\""
})
@Since("1.6")

public class EvtNotePlay extends SkriptEvent {

    static{
        Skript.registerEvent("on note play", EvtNotePlay.class, NotePlayEvent.class,
                "note [block] play"
        );
        EventValues.registerEventValue(NotePlayEvent.class, Note.Tone.class, new Getter<>() {
            @Override
            public Note.Tone get(NotePlayEvent e) {
                return e.getNote().getTone();
            }
        }, 0);
        EventValues.registerEventValue(NotePlayEvent.class, Note.class, new Getter<>() {
            @Override
            public Note get(NotePlayEvent e) {
                return e.getNote();
            }
        }, 0);
        EventValues.registerEventValue(NotePlayEvent.class, Instrument.class, new Getter<>() {
            @Override
            public Instrument get(NotePlayEvent e) {
                return e.getInstrument();
            }
        }, 0);
        EventValues.registerEventValue(NotePlayEvent.class, Block.class, new Getter<>() {
            @Override
            public Block get(NotePlayEvent e) {
                return e.getBlock();
            }
        }, 0);
    }

    @Override
    public boolean init(Literal<?> @NotNull [] args, int matchedPattern, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(@NotNull Event e) {
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "note play event";
    }
}
