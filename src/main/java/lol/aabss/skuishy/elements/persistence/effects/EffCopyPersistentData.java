package lol.aabss.skuishy.elements.persistence.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Persistence - Copy Persistent Data")
@Description({"Copies and optionally replaces the persistent data from one data container to another"})
@Examples({
        "copy player's data container to arg-1's data container"
})
@Since("2.7")
public class EffCopyPersistentData extends Effect {

    static {
        Skript.registerEffect(EffEditPersistentData.class,
                "(:replace|copy) persistent data [container] from %datacontainer% to %datacontainers%");
    }

    private boolean replace;
    private Expression<PersistentDataContainer> from;
    private Expression<PersistentDataContainer> to;

    @Override
    protected void execute(@NotNull Event event) {
        PersistentDataContainer from = this.from.getSingle(event);
        if (from != null){
            return;
        }
        for (PersistentDataContainer to : this.to.getArray(event)){
            from.copyTo(to, replace);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "copy persistent data container;";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, @NotNull Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        replace = parseResult.hasTag("replace");
        from = (Expression<PersistentDataContainer>) expressions[0];
        to = (Expression<PersistentDataContainer>) expressions[1];
        return true;
    }
}
