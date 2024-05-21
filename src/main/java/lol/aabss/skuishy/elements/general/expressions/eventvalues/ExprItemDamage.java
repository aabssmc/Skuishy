package lol.aabss.skuishy.elements.general.expressions.eventvalues;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
@SuppressWarnings("NullableProblems")
@Name("Item - Item Damage")
@Description("Gets/sets the item damage.")
@Examples({
        "on item damage:",
        "\tincrease item damage by item damage*4"
})
@Since("1.7")
public class ExprItemDamage extends EventValueExpression<Integer> {

    static{
        Skript.registerExpression(ExprItemDamage.class, Integer.class, ExpressionType.SIMPLE,
                "[the] [event-]item[ ]damage"
        );
    }

    public ExprItemDamage() {
        super(Integer.class);
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "item damage";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (getParser().isCurrentEvent(PlayerItemDamageEvent.class)){
            return true;
        }
        Skript.error("'item damage' can not be used outside of Item Damage Event");
        return false;
    }

    @Override
    protected Integer @Nullable [] get(@NotNull Event event) {
        return new Integer[]{((PlayerItemDamageEvent) event).getDamage()};
    }


    @Override
    public void change(@NotNull Event event, Object @Nullable [] delta, Changer.@NotNull ChangeMode mode) {
        if (delta != null) {
            if (mode == Changer.ChangeMode.SET) {
                ((PlayerItemDamageEvent) event).setDamage((Integer) delta[0]);
            } else if (mode == Changer.ChangeMode.ADD) {
                ((PlayerItemDamageEvent) event).setDamage(((PlayerItemDamageEvent) event).getDamage() + (Integer) delta[0]);
            }
        } if (mode == Changer.ChangeMode.REMOVE) {
            ((PlayerItemDamageEvent) event).setDamage(((PlayerItemDamageEvent) event).getDamage() - (Integer) delta[0]);
        } else if (mode == Changer.ChangeMode.REMOVE_ALL) {
            ((PlayerItemDamageEvent) event).setDamage(0);
        }
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(Integer.class);
        }
        return null;
    }

}
