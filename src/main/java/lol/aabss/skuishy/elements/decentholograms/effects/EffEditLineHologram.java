package lol.aabss.skuishy.elements.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramLine;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class EffEditLineHologram extends Effect {

    String changetype;
    Expression<Hologram> hologram;
    Expression<String> text;
    Expression<Integer> line;
    Expression<Integer> page;
    Variable<?> var;

    static{
        Skript.registerEffect(EffEditLineHologram.class,
                "add [line] of [hologram] %hologram% to show [text] %string%",
                "remove [line] %integer% of [hologram] %hologram%",
                "create line from [page] %integer% of [hologram] %hologram% to show [text] %string%",
                "insert [line] %integer% of [hologram] %hologram% to show [text] %string%",
                "set [line] %integer% of [hologram] %hologram% to show [text] %string%",
                "get [line] %integer% of [hologram] %hologram% from [page] %integer% and store it in %object%"
        );
    }

    @Override
    protected void execute(@NotNull Event e) {
        if (Objects.equals(changetype, "add")){
            DHAPI.addHologramLine(hologram.getSingle(e), text.getSingle(e));
        }
        else if (Objects.equals(changetype, "remove")){
            DHAPI.removeHologramLine(hologram.getSingle(e), line.getSingle(e));
        }
        else if (Objects.equals(changetype, "create")){
            DHAPI.createHologramLine(DHAPI.getHologramPage(hologram.getSingle(e), page.getSingle(e)), text.getSingle(e));
        }
        else if (Objects.equals(changetype, "insert")){
            DHAPI.insertHologramLine(hologram.getSingle(e), line.getSingle(e), text.getSingle(e));
        }
        else if (Objects.equals(changetype, "set")){
            DHAPI.setHologramLine(hologram.getSingle(e), line.getSingle(e), text.getSingle(e));
        }
        else if (Objects.equals(changetype, "get")){
            HologramPage page = DHAPI.getHologramPage(hologram.getSingle(e), this.page.getSingle(e));
            HologramLine line = DHAPI.getHologramLine(page, this.line.getSingle(e));
            Variables.setVariable(var.getName().toString(), line, e, var.isLocal());
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
            text = (Expression<String>) exprs[1];
        }
        else if (matchedPattern == 1){
            changetype = "remove";
            line = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
        }
        else if (matchedPattern == 2){
            changetype = "create";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
            text = (Expression<String>) exprs[2];
        }
        else if (matchedPattern == 3){
            changetype = "insert";
            line = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
            text = (Expression<String>) exprs[2];
        }
        else if (matchedPattern == 4){
            changetype = "set";
            line = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
            text = (Expression<String>) exprs[2];
        }
        else if (matchedPattern == 5){
            changetype = "get";
            line = (Expression<Integer>) exprs[0];
            hologram = (Expression<Hologram>) exprs[1];
            page = (Expression<Integer>) exprs[2];
            if (exprs[3] instanceof Variable<?>){
                var = (Variable<?>) exprs[3];
            }
            else{
                Skript.error("Object must be a variable!");
                return false;
            }
        }
        return true;
    }
}
