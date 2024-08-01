package lol.aabss.skuishy.elements.persistence.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Persistence - Persistent Data Container")
@Description("Gets the persistent data container of an entity.")
@Examples({
        "set {_container} to persistent data container of player"
})
@Since("2.7")
public class ExprPersistentDataContainer extends SimplePropertyExpression<Object, PersistentDataContainer> {
    static {
        register(ExprPersistentDataContainer.class, PersistentDataContainer.class,
                "[persistent] data container",
                "objects");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "persistent data container";
    }

    @Override
    public @Nullable PersistentDataContainer convert(Object object) {
        if (object instanceof PersistentDataHolder){
            return ((PersistentDataHolder) object).getPersistentDataContainer();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends PersistentDataContainer> getReturnType() {
        return PersistentDataContainer.class;
    }
}
