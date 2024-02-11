package lol.aabss.skuishy.elements.decentholograms.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import org.eclipse.jdt.annotation.Nullable;

@SuppressWarnings("NullableProblems")
@Name("Decent Holograms - Hologram Name")
@Description("Gets a hologram by its name.")
@Examples({
        "set {_holo} to hologram named \"poopy\""
})
@Since("1.7")
@RequiredPlugins("DecentHolograms")
public class ExprHologramFromName extends SimpleExpression<Hologram> {

    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Skript.registerExpression(ExprHologramFromName.class, Hologram.class, ExpressionType.SIMPLE,
                    "[(decent [hologram[s]]|dh)] hologram (from name|named) %string%"
            );
        }
    }

    private Expression<String> name;

    @Override
    protected @Nullable Hologram[] get(@NotNull Event e) {
        String name = this.name.getSingle(e);
        if (name != null) {
            try {
                Hologram hologram = DHAPI.getHologram(name);
                return new Hologram[]{hologram};
            } catch (IllegalArgumentException ae){
                return new Hologram[]{null};
            }
        }
        return new Hologram[]{null};
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

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        name = (Expression<String>) exprs[0];
        return true;
    }
}
