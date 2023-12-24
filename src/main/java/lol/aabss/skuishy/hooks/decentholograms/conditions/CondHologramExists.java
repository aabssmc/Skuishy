package lol.aabss.skuishy.hooks.decentholograms.conditions;

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

import javax.annotation.Nullable;
import java.util.Objects;

@Name("Decent Holograms - Hologram Exists")
@Description("Returns true if a hologram exists.")
@Examples({
        "if hologram named \"hello\" exists:"
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")

public class CondHologramExists extends Condition {

    static{
        if (Bukkit.getServer().getPluginManager().getPlugin("DecentHolograms") != null) {
            Skript.registerCondition(CondHologramExists.class,
                    "[hologram] %hologram% (does) exists",
                    "[hologram] %hologram% does(n't| not) exist"
            );
        }
    }

    private Expression<String> name;
    private boolean is;

    @Override
    public boolean check(@NotNull Event e) {
        if (is){
            return DHAPI.getHologram(Objects.requireNonNull(name.getSingle(e))) != null;
        }
        return DHAPI.getHologram(Objects.requireNonNull(name.getSingle(e))) == null;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "hologram exists";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        is = matchedPattern == 0;
        return true;
    }
}