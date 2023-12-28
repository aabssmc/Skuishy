package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Color;
import ch.njol.util.Kleenean;
import org.bukkit.FireworkEffect;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ExprDyed extends SimpleExpression<ItemStack> {

    static{
        Skript.registerExpression(ExprDyed.class, ItemStack.class, ExpressionType.COMBINED,
                "%itemtypes% (dy|colo[u]r)ed [(as|with)] %color%",
                "%itemtypes% (dy|colo[u]r)ed [(as|with)] \\(%integer%, %integer%, %integer%\\)"
        );
    }

    private Expression<ItemType> items;
    private Expression<Color> color;
    private Expression<Integer> red;
    private Expression<Integer> green;
    private Expression<Integer> blue;

    @Override
    protected @Nullable ItemStack[] get(@NotNull Event e) {
        ItemStack item = new ItemStack(items.getSingle(e).getMaterial());
        org.bukkit.Color color;
        if (this.color != null){
            color = this.color.getSingle(e).asBukkitColor();
        }
        else{
            if (red.getSingle(e) <= 255 && green.getSingle(e) <= 255 && blue.getSingle(e) <= 255) {
                color = org.bukkit.Color.fromRGB(red.getSingle(e), green.getSingle(e), blue.getSingle(e));
            }
            else{
                color = org.bukkit.Color.fromRGB(0,0,0);
            }
        }
        if (item.getItemMeta() instanceof LeatherArmorMeta){
            LeatherArmorMeta meta = ((LeatherArmorMeta) item.getItemMeta());
            meta.setColor(color);
            item.setItemMeta(meta);
            return new ItemStack[]{item};
        }
        else if (item.getItemMeta() instanceof FireworkEffectMeta){
            FireworkEffect effect = FireworkEffect.builder()
                    .withColor(color)
                    .build();
            FireworkEffectMeta meta = ((FireworkEffectMeta) item.getItemMeta());
            meta.setEffect(effect);
            item.setItemMeta(meta);
            return new ItemStack[]{item};
        }
        return new ItemStack[0];
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "dyed item";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        items = (Expression<ItemType>) exprs[0];
        if (matchedPattern == 0){
            color = (Expression<Color>) exprs[1];
            return true;
        }
        red = (Expression<Integer>) exprs[1];
        green = (Expression<Integer>) exprs[2];
        blue = (Expression<Integer>) exprs[3];
        return true;
    }
}
