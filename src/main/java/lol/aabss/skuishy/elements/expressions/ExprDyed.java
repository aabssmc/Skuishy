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
import ch.njol.skript.util.slot.Slot;
import ch.njol.util.Kleenean;
import org.bukkit.FireworkEffect;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
@Name("Other - Item Dyed")
@Description("Returns dyed items (leather armor & fireworks).")
@Examples({
        "give player leather armor dyed with blue",
        "give player firework star dyed as (210, 4, 45)",
        "give player red leather helmet",
        "give player (255, 36, 0) leather tunic"
})
@Since("1.9.5")
public class ExprDyed extends SimpleExpression<Object> {

    private final static boolean SUPPORTS_COLORABlE_META = Skript.classExists("org.bukkit.inventory.meta.ColorableArmorMeta");

    static{
        Skript.registerExpression(ExprDyed.class, Object.class, ExpressionType.COMBINED,
                "%itemtypes/itemstacks/slots% (dy|colo[u]r)ed %color%",
                "%*color% %itemtypes/itemstacks/slots%"
        );
    }

    private Expression<?> items;
    private Expression<Color> color;


    //improved by fusezion
    @Override
    protected @Nullable Object[] get(@NotNull Event event) {
        Color color = this.color.getSingle(event);
        if (color == null) return null;
        List<Object> items = new ArrayList<>();
        for (Object object : this.items.getArray(event)) {
            if (object instanceof Slot slot) {
                ItemStack itemStack = slot.getItem();
                assert itemStack != null; // nullable only when deleted, skript just has a poor handling of this
                itemStack.setItemMeta(dyeItemMeta(itemStack.getItemMeta(),color));
            } else if (object instanceof ItemType itemType) {
                itemType.setItemMeta(dyeItemMeta(itemType.getItemMeta(), color));
            } else if (object instanceof ItemStack itemStack) {
                itemStack.setItemMeta(dyeItemMeta(itemStack.getItemMeta(), color));
            }
            items.add(object);
        }
        return items.toArray(Object[]::new);
    }

    private ItemMeta dyeItemMeta(ItemMeta itemMeta, Color color) {
        if (color == null) return itemMeta;
        org.bukkit.Color bukkitColor = color.asBukkitColor();
        if (SUPPORTS_COLORABlE_META && itemMeta instanceof ColorableArmorMeta armorMeta) {
            armorMeta.setColor(bukkitColor);
        } else if (itemMeta instanceof FireworkEffectMeta fireworkEffectMeta) {
            FireworkEffect fireworkEffect = FireworkEffect.builder().withColor(bukkitColor).build();
            fireworkEffectMeta.setEffect(fireworkEffect);
        } else if (itemMeta instanceof LeatherArmorMeta armorMeta) {
            armorMeta.setColor(bukkitColor);
        } else if (itemMeta instanceof PotionMeta potionMeta) {
            potionMeta.setColor(bukkitColor);
        } else if (itemMeta instanceof MapMeta mapMeta) {
            mapMeta.setColor(bukkitColor);
        }
        return itemMeta;
    }

    @Override
    public boolean isSingle() {
        return items.isSingle();
    }

    @Override
    public @NotNull Class<?> getReturnType() {
        return Slot.class.isAssignableFrom(items.getReturnType()) ? Slot.class : ItemType.class;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return this.items.toString(event, debug) + " dyed " + this.color.toString(event, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.items = exprs[matchedPattern];
        if (matchedPattern == 0) {
            this.color = (Expression<Color>) exprs[1];
            return true;
        }
        this.color = (Expression<Color>) exprs[0];
        return true;
    }
}