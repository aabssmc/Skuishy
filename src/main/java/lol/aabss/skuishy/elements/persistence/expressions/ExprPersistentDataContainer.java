package lol.aabss.skuishy.elements.persistence.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Persistence - Persistent Data Container")
@Description("Gets the persistent data container of an entity.")
@Examples({
        "set {_container} to persistent data container of player"
})
@Since("2.7")
public class ExprPersistentDataContainer extends SimplePropertyExpression<Entity, PersistentDataContainer> {
    static {
        register(ExprPersistentDataContainer.class, PersistentDataContainer.class,
                "[persistent] data container",
                "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "persistent data container";
    }

    @Override
    public @Nullable PersistentDataContainer convert(Entity entity) {
        return entity.getPersistentDataContainer();
    }

    @Override
    public @NotNull Class<? extends PersistentDataContainer> getReturnType() {
        return PersistentDataContainer.class;
    }
}
