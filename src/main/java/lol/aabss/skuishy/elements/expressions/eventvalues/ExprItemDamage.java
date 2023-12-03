package lol.aabss.skuishy.elements.expressions.eventvalues;

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

import javax.annotation.Nullable;
@Name("Other - Item Damage")
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
    public @NotNull String toString(@Nullable Event e, boolean debug) {
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
    protected @Nullable Integer[] get(@NotNull Event e) {
        return new Integer[]{((PlayerItemDamageEvent) e).getDamage()};
    }


    @Override
    public void change(@NotNull Event e, @Nullable Object[] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            assert delta != null;
            ((PlayerItemDamageEvent) e).setDamage((Integer) delta[0]);
        }
        else if (mode == Changer.ChangeMode.ADD) {
            assert delta != null;
            ((PlayerItemDamageEvent) e).setDamage(((PlayerItemDamageEvent) e).getDamage() + (Integer) delta[0]);
        }
        else if (mode == Changer.ChangeMode.REMOVE) {
            assert delta != null;
            ((PlayerItemDamageEvent) e).setDamage(((PlayerItemDamageEvent) e).getDamage() - (Integer) delta[0]);
        }
        else if (mode == Changer.ChangeMode.REMOVE_ALL) {
            assert delta != null;
            ((PlayerItemDamageEvent) e).setDamage(0);
        }
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.REMOVE_ALL) {
            return CollectionUtils.array(Integer.class);
        }
        return CollectionUtils.array();
    }

}
