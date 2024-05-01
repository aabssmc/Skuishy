package lol.aabss.skuishy.elements.decentholograms.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Decent Holograms - Hologram Exists")
@Description("Returns true if a hologram exists.")
@Examples({
        "if hologram named \"hello\" exists:"
})
@Since("1.~7")
@RequiredPlugins("DecentHolograms")

public class CondHologramExists extends Condition {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerCondition(CondHologramExists.class,
                    "[hologram] [named|with name] %string% [(does)] exist[s]",
                    "[hologram] %string% [named|with name] does(n't| not) exist"
            );
        }
    }

    private Expression<String> name;
    private boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        String name = this.name.getSingle(e);
        if (name != null) {
            List<String> names = new ArrayList<>();
            Hologram.getCachedHolograms().forEach(hologram -> names.add(hologram.getName()));
            if (is){
                return names.contains(name);
            }
            return !names.contains(name);
        }
        return false;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "hologram exists";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        is = matchedPattern == 0;
        return true;
    }
}
