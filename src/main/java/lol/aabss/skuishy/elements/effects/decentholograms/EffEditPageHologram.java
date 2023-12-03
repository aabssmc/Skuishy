package lol.aabss.skuishy.elements.effects.decentholograms;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
@Name("Decent Holograms - Edit Page")
@Description("Edits a page of a hologram.")
@Examples({
        "..."
})
@Since("1.7")
public class EffEditPageHologram extends Effect {

    static{
        Skript.registerEffect(EffEditPageHologram.class,
                "add [page] of [hologram] %hologram%",
                "remove [page] %integer% of [hologram] %hologram%",
                "insert [page] %integer% of [hologram] %hologram%",
                "get [page] %integer% of [hologram] %hologram% and store it in %object%"
        );
    }

    private String changetype;
    private Expression<Hologram> hologram;
    private Expression<Integer> page;
    private Variable<?> var;

    @Override
    protected void execute(@NotNull Event e) {
        if (Objects.equals(changetype, "add")){
            DHAPI.addHologramPage(Objects.requireNonNull(hologram.getSingle(e)));
        }
        else if (Objects.equals(changetype, "remove")){
            DHAPI.removeHologramPage(Objects.requireNonNull(hologram.getSingle(e)),
                    Objects.requireNonNull(page.getSingle(e)));
        }
        else if (Objects.equals(changetype, "insert")){
            DHAPI.insertHologramPage(Objects.requireNonNull(hologram.getSingle(e)),
                    Objects.requireNonNull(page.getSingle(e)));
        }
        else if (Objects.equals(changetype, "get")){
            HologramPage page = DHAPI.getHologramPage(Objects.requireNonNull(hologram.getSingle(e)),
                    Objects.requireNonNull(this.page.getSingle(e)));
            Variables.setVariable(var.getName().toString(), page, e, var.isLocal());
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "edit hologram";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            changetype = "add";
            hologram = (Expression<Hologram>) exprs[0];
        }
        else if (matchedPattern == 1){
            changetype = "remove";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
        }
        else if (matchedPattern == 2){
            changetype = "insert";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
        }
        else if (matchedPattern == 3){
            changetype = "get";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
            if (exprs[3] instanceof Variable<?>){
                var = (Variable<?>) exprs[2];
            }
            else{
                Skript.error("Object must be a variable!");
                return false;
            }
        }
        return true;
    }
}
