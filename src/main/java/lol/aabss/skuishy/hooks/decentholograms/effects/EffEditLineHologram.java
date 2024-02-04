package lol.aabss.skuishy.hooks.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.NonNull;

import org.eclipse.jdt.annotation.Nullable;
import java.util.Objects;
@Name("Decent Holograms - Edit Line")
@Description("Edits a line of a hologram.")
@Examples({
        "..."
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class EffEditLineHologram extends Effect {

    static{
        if (Bukkit.getServer().getPluginManager().getPlugin("DecentHolograms") != null) {
            Skript.registerEffect(EffEditLineHologram.class,
                    "add [line] of [hologram] %hologram% to show [text] %string%",
                    "remove [line] %integer% of [hologram] %hologram%",
                    "create line from [page] %integer% of [hologram] %hologram% to show [text] %string%",
                    "insert [line] %integer% of [hologram] %hologram% to show [text] %string%",
                    "set [line] %integer% of [hologram] %hologram% to show [text] %string%",
                    "get [line] %integer% of [hologram] %hologram% from [page] %integer% and store it in %object%"
            );
        }
    }

    private String changetype;
    private Expression<Hologram> hologram;
    private Expression<String> text;
    private Expression<Integer> line;
    private Expression<Integer> page;
    private Variable<?> var;

    @Override
    protected void execute(@NonNull Event e) {
        Hologram holo = hologram.getSingle(e);
        if (holo != null) {
            if (Objects.equals(changetype, "add")) {
                DHAPI.addHologramLine(holo, text.getSingle(e));
            } else if (Objects.equals(changetype, "remove")) {
                Integer line = this.line.getSingle(e);
                if (line != null) {
                    DHAPI.removeHologramLine(holo, line);
                }
            } else if (Objects.equals(changetype, "create")) {
                Integer page = this.page.getSingle(e);
                if (page != null) {
                    HologramPage pagee = DHAPI.getHologramPage(holo, page);
                    if (pagee != null) {
                        DHAPI.createHologramLine(pagee, text.getSingle(e));
                    }
                }
            } else if (Objects.equals(changetype, "insert")) {
                Integer line = this.line.getSingle(e);
                if (line != null) {
                    DHAPI.insertHologramLine(holo, line, text.getSingle(e));
                }
            } else if (Objects.equals(changetype, "set")) {
                Integer line = this.line.getSingle(e);
                if (line != null) {
                    DHAPI.setHologramLine(holo, line, text.getSingle(e));
                }
            } else if (Objects.equals(changetype, "get")) {
                Integer pagen = this.page.getSingle(e);
                if (pagen != null) {
                    HologramPage page = DHAPI.getHologramPage(holo, pagen);
                    if (page != null) {
                        HologramLine line = DHAPI.getHologramLine(page, Objects.requireNonNull(this.line.getSingle(e)));
                        Variables.setVariable(var.getName().toString(), line, e, var.isLocal());
                    }
                }
            }
        }
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "edit hologram";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
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
