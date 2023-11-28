package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static lol.aabss.skuishy.Skuishy.instance;

public class ExprHologramFromName extends SimpleExpression<Hologram> {

    Expression<String> name;

    static{
        Skript.registerExpression(ExprHologramFromName.class, Hologram.class, ExpressionType.SIMPLE,
                "[(decent [hologram[s]]|dh)] hologram (from name|named) %string%"
        );
    }

    @Override
    protected @Nullable Hologram[] get(@NotNull Event e) {
        assert name != null;
        Hologram hologram = DHAPI.getHologram(name.getSingle(e));
        return new Hologram[]{hologram};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Hologram> getReturnType() {
        return Hologram.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "hologram from name";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (!instance.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.error("Decent holograms is not installed");
        }
        name = (Expression<String>) exprs[0];
        return true;
    }
}
