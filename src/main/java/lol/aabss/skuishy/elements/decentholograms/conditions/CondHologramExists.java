package lol.aabss.skuishy.elements.decentholograms.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@Name("Decent Holograms - Hologram Exists")
@Description("Returns true if a hologram exists.")
@Examples({
        "if hologram named \"hello\" exists:"
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class CondHologramExists extends Condition {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerCondition(CondHologramExists.class,
                    "[hologram] [named|with name] %string% (does) exists",
                    "[hologram] %string% [named|with name] does(n't| not) exist"
            );
        }
    }

    private Expression<String> name;
    private boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        String name = this.name.getSingle(e);
        if (name == null) return false;
        if (is) {
            return DHAPI.getHologram(name) != null;
        }
        return DHAPI.getHologram(name) == null;
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
