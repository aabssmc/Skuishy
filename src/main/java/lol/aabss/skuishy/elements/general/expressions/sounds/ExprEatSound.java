package lol.aabss.skuishy.elements.general.expressions.sounds;

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
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Entity - Eat Sound")
@Description("Gets the eating sound of the entity.")
@Examples({
        "send eating sound of {_e}"
})
@Since("2.1")
public class ExprEatSound extends SimpleExpression<String> {

    static {
        if (Skript.methodExists(LivingEntity.class, "getEatingSound")) {
            Skript.registerExpression(ExprEatSound.class, String.class, ExpressionType.COMBINED,
                    "[the] [entity] eat[ing] sound of %livingentities% (using|with) %itemstack%",
                    "%livingentities%'s [entity] eat[ing] sound (using|with) %itemstack%"
            );
        }
    }

    private Expression<ItemStack> item;
    private Expression<LivingEntity> entity;

    @Override
    protected String @NotNull [] get(@NotNull Event e) {
        ItemStack item = this.item.getSingle(e);
        if (item != null) {
            List<String> sounds = new ArrayList<>();
            for (LivingEntity en : entity.getArray(e)) {
                sounds.add(en.getEatingSound(item).getKey().getKey());
            } return sounds.toArray(String[]::new);
        }
        return new String[]{null};
    }

    @Override
    public boolean isSingle() {
        return entity.isSingle();
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "eating sound";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        entity = (Expression<LivingEntity>) exprs[0];
        item = (Expression<ItemStack>) exprs[1];
        return true;
    }
}
