package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Allay;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Name("Allay - Duplication Cooldown")
@Description("Gets/sets the duplication cooldown of an allay in ticks.")
@Examples({
        "set duplication cooldown of {_allay} to 20 # 1 second"
})
@Since("2.8")
public class ExprAllayDuplicationCooldown extends EntityExpression<Allay, Long> {

    static {
        register(ExprAllayDuplicationCooldown.class, Long.class, "[allay] duplication cooldown [ticks]", "entities");
    }

    @Override
    public Long get(Allay allay) {
        return allay.getDuplicationCooldown();
    }

    @Override
    public void change(Allay allay, @Nullable Long aLong, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET && aLong != null) {
            allay.setDuplicationCooldown(aLong);
        } else {
            allay.resetDuplicationCooldown();
        }
    }

    @Override
    public List<Changer.ChangeMode> acceptedChanges() {
        return List.of(Changer.ChangeMode.SET, Changer.ChangeMode.RESET);
    }
}
