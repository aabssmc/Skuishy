package lol.aabss.skuishy.elements.expressions.decentholograms;

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
import java.util.Objects;

public class ExprHologramFromName extends SimpleExpression<Hologram> {

    static{
        Skript.registerExpression(ExprHologramFromName.class, Hologram.class, ExpressionType.SIMPLE,
                "[(decent [hologram[s]]|dh)] hologram (from name|named) %string%"
        );
    }

    Expression<String> name;

    @Override
    protected @Nullable Hologram[] get(@NotNull Event e) {
        Hologram hologram = DHAPI.getHologram(Objects.requireNonNull(name.getSingle(e)));
        return new Hologram[]{hologram};
    }

    @Override
    public boolean isSingle() {
        return true;
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
        name = (Expression<String>) exprs[0];
        return true;
    }
}
