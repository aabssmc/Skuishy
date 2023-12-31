package lol.aabss.skuishy.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
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
import org.bukkit.inventory.meta.PotionMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Other - Item Dyed")
@Description("Returns dyed items (leather armor & fireworks).")
@Examples({
        "give player leather armor dyed with blue",
        "give player firework star dyed as (210, 4, 45)",
        "give player red leather helmet",
        "give player (255, 36, 0) leather tunic"
})
@Since("1.9.5")

public class ExprDyed extends SimpleExpression<ItemStack> {

    static{
        Skript.registerExpression(ExprDyed.class, ItemStack.class, ExpressionType.COMBINED,
                "%itemtypes% (dy|colo[u]r)ed [(as|with)] %color%",
                "%itemtypes% (dy|colo[u]r)ed [(as|with)] \\(%integer%,[ ]%integer%,[ ]%integer%\\)",
                "%color% %itemtypes%",
                "\\(%integer%,[ ]%integer%,[ ]%integer%\\) %itemtypes%"
        );
    }

    private Expression<ItemType> items;
    private Expression<Color> color;
    private Expression<Integer> red;
    private Expression<Integer> green;
    private Expression<Integer> blue;

    @Override
    protected ItemStack @NotNull [] get(@NotNull Event e) {
        ItemType im = items.getSingle(e);
        if (im != null ) {
            ItemStack item = new ItemStack(im.getMaterial());
            Color c = this.color.getSingle(e);
            org.bukkit.Color color;
            if (c != null) {
                color = c.asBukkitColor();
            } else {
                Integer r = red.getSingle(e);
                Integer g = green.getSingle(e);
                Integer b = blue.getSingle(e);
                if (r != null && g != null && b != null) {
                    if (r <= 255 && g <= 255 && b <= 255) {
                        color = org.bukkit.Color.fromRGB(r, g, b);
                    } else {
                        color = org.bukkit.Color.fromRGB(0, 0, 0);
                    }
                }
                else{
                    color = org.bukkit.Color.fromRGB(0, 0, 0);
                }
            }
            if (item.getItemMeta() instanceof LeatherArmorMeta) {
                LeatherArmorMeta meta = ((LeatherArmorMeta) item.getItemMeta());
                meta.setColor(color);
                item.setItemMeta(meta);
                return new ItemStack[]{item};
            } else if (item.getItemMeta() instanceof FireworkEffectMeta) {
                FireworkEffect effect = FireworkEffect.builder()
                        .withColor(color)
                        .build();
                FireworkEffectMeta meta = ((FireworkEffectMeta) item.getItemMeta());
                meta.setEffect(effect);
                item.setItemMeta(meta);
                return new ItemStack[]{item};
            } else if (item.getItemMeta() instanceof PotionMeta) {
                PotionMeta meta = ((PotionMeta) item.getItemMeta());
                meta.setColor(color);
                item.setItemMeta(meta);
                return new ItemStack[]{item};
            }
            return new ItemStack[]{};
        }
        return new ItemStack[]{};
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
        if (matchedPattern == 0){
            items = (Expression<ItemType>) exprs[0];
            color = (Expression<Color>) exprs[1];
            return true;
        }
        else if (matchedPattern == 1) {
            items = (Expression<ItemType>) exprs[0];
            red = (Expression<Integer>) exprs[1];
            green = (Expression<Integer>) exprs[2];
            blue = (Expression<Integer>) exprs[3];
            return true;
        }
        else if (matchedPattern == 2) {
            color = (Expression<Color>) exprs[0];
            items = (Expression<ItemType>) exprs[1];
            return true;
        }
        red = (Expression<Integer>) exprs[0];
        green = (Expression<Integer>) exprs[1];
        blue = (Expression<Integer>) exprs[2];
        items = (Expression<ItemType>) exprs[3];
        return true;
    }
}
