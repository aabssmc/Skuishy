package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Nullable;

@Name("Entity - Custom Name Visibility")
@Description("Gets/sets the custom name visibility of entities.")
@Examples({
        "set custom name visibility of target entity to true"
})
@Since("1.7.5")
public class ExprCustomNameVisibility extends EntityExpression<Entity, Boolean> {

    static {
        register(ExprCustomNameVisibility.class, Boolean.class, "custom[ ]name visib(le|ility)", "entities");
    }

    @Override
    public Boolean get(Entity entity) {
        return entity.isCustomNameVisible();
    }

    @Override
    public void change(Entity entity, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            entity.setCustomNameVisible(aBoolean);
        }
    }
}
