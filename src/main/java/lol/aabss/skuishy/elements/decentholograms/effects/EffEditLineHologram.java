package lol.aabss.skuishy.elements.decentholograms.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import lol.aabss.skuishy.Skuishy;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerEffect(EffEditLineHologram.class,
                    "add [line] of [hologram] %holograms/hologrampages% to show [text] %string%",
                    "remove [line] %integer% of [hologram] %holograms/hologrampages%",
                    "create line from [hologram] %holograms/hologrampages% to show [text] %string%",
                    "insert [line] %integer% of [hologram] %holograms/hologrampages% to show [text] %string%",
                    "set [line] %integer% of [hologram] %holograms/hologrampages% to show [text] %string%"
            );
        }
    }

    private String changetype;
    private Expression<Object> hologram;
    private Expression<String> text;
    private Expression<Integer> line;
    private Expression<Integer> page;

    @Override
    protected void execute(@NotNull Event event) {
        try {
            for (Object holo : hologram.getArray(event)) {
                HologramPage hologramPage;
                if (holo instanceof Hologram) {
                    hologramPage = ((Hologram) holo).getPage(0);
                } else {
                    hologramPage = (HologramPage) holo;
                }
                if (Objects.equals(changetype, "add")) {
                    String text = this.text.getSingle(event);
                    if (text != null) {
                        DHAPI.addHologramLine(hologramPage, text);
                    }
                } else if (Objects.equals(changetype, "remove")) {
                    Integer line = this.line.getSingle(event);
                    if (line != null) {
                        DHAPI.removeHologramLine(hologramPage, Skuishy.index(line));
                    }
                } else if (Objects.equals(changetype, "create")) {
                    Integer page = this.page.getSingle(event);
                    String text = this.text.getSingle(event);
                    if (page != null && text != null) {
                        DHAPI.createHologramLine(hologramPage, text);
                    }
                } else if (Objects.equals(changetype, "insert")) {
                    Integer line = this.line.getSingle(event);
                    String text = this.text.getSingle(event);
                    if (line != null && text != null) {
                        DHAPI.insertHologramLine(hologramPage, Skuishy.index(line), text);
                    }
                } else if (Objects.equals(changetype, "set")) {
                    Integer line = this.line.getSingle(event);
                    String text = this.text.getSingle(event);
                    if (line != null && text != null) {
                        DHAPI.setHologramLine(hologramPage, Skuishy.index(line), text);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            Skuishy.Logger.exception(e);
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "edit hologram";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            changetype = "add";
            hologram = (Expression<Object>) exprs[0];
            text = (Expression<String>) exprs[1];
        } else if (matchedPattern == 1){
            changetype = "remove";
            line = (Expression<Integer>) exprs[0];
            hologram = (Expression<Object>) exprs[1];
        } else if (matchedPattern == 2){
            changetype = "create";
            page = (Expression<Integer>) exprs[0];
            hologram = (Expression<Object>) exprs[1];
            text = (Expression<String>) exprs[2];
        } else if (matchedPattern == 3){
            changetype = "insert";
            line = (Expression<Integer>) exprs[0];
            hologram = (Expression<Object>) exprs[1];
            text = (Expression<String>) exprs[2];
        } else if (matchedPattern == 4){
            changetype = "set";
            line = (Expression<Integer>) exprs[0];
            hologram = (Expression<Object>) exprs[1];
            text = (Expression<String>) exprs[2];
        }
        return true;
    }
}
