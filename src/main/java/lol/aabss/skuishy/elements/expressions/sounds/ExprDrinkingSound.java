package lol.aabss.skuishy.elements.expressions.sounds;

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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Entity - Drinking Sound")
@Description("Gets the drinking sound of the entity.")
@Examples({
        "send drinking sound of {_e}"
})
@Since("2.1")
public class ExprDrinkingSound extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprDrinkingSound.class, String.class, ExpressionType.COMBINED,
                "[the] [entity] drink[ing] sound of %livingentity% (using|with) %itemstack%",
                "%livingentity%'s [entity] drink[ing] sound (using|with) %itemstack%"
        );
    }

    private Expression<ItemStack> item;
    private Expression<LivingEntity> entity;

    @Override
    protected String @NonNull [] get(@NonNull Event e) {
        LivingEntity entity = this.entity.getSingle(e);
        ItemStack item = this.item.getSingle(e);
        if (entity != null && item != null){
            return new String[]{entity.getDrinkingSound(item).name().replaceAll("_", ".").toUpperCase()};
        }
        return new String[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NonNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NonNull String toString(@Nullable Event e, boolean debug) {
        return "drinking sound";
    }

    @Override
    public boolean init(Expression<?> @NonNull [] exprs, int matchedPattern, @NonNull Kleenean isDelayed, SkriptParser.@NonNull ParseResult parseResult) {
        entity = (Expression<LivingEntity>) exprs[0];
        item = (Expression<ItemStack>) exprs[1];
        return true;
    }
}
