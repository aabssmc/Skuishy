package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.papermc.paper.datapack.Datapack;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("NullableProblems")
@Name("Server - All Datapacks")
@Description("Gets all the datapacks.")
@Examples({
        "send all enabled datapacks"
})
@Since("1.7")

public class ExprAllDatapacks extends SimpleExpression<Datapack> {

    static {
        Skript.registerExpression(ExprAllDatapacks.class, Datapack.class, ExpressionType.SIMPLE,
                "[all [of]] [the] [:enabled] datapacks"
        );
    }

    private boolean enabled;

    @Override
    protected @Nullable Datapack[] get(@NotNull Event event) {
        if (enabled){
            return Bukkit.getDatapackManager().getEnabledPacks().toArray(Datapack[]::new);
        }
        return Bukkit.getDatapackManager().getPacks().toArray(Datapack[]::new);
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public @NotNull Class<? extends Datapack> getReturnType() {
        return Datapack.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "all datapacks";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        enabled = parseResult.hasTag("enabled");
        return true;
    }
}
