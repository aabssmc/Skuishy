package lol.aabss.skuishy.elements.general.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.util.coll.CollectionUtils;
import com.destroystokyo.paper.event.inventory.PrepareResultEvent;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

@Name("Other - Inventory Result")
@Description("Gets/sets the inventory result in a prepare result event.")
@Examples({
        "on prepare result:",
        "\tset the result to diamond block"
})
@Since("2.8")
public class ExprEventResult extends EventValueExpression<ItemStack> {
    static {
        Skript.registerExpression(ExprEventResult.class, ItemStack.class, ExpressionType.SIMPLE,
                "[the] [event[-| ]]result"
        );
    }
    public ExprEventResult() {
        super(ItemStack.class);
    }

    @Override
    protected ItemStack @NotNull [] get(@NotNull Event event) {
        if (event instanceof PrepareResultEvent e){
            return new ItemStack[]{e.getResult()};
        }
        return null;
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            CollectionUtils.array(ItemStack.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (event instanceof PrepareResultEvent e && delta[0] instanceof ItemStack){
            e.setResult((ItemStack) delta[0]);
        }
    }
}
