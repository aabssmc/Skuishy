package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Material")
@Description("Gets/sets the material of a minecart.")
@Examples({
        "set {_material} to minecart material of {_minecart}"
})
@Since("2.8")
public class ExprMinecartMaterial extends SimplePropertyExpression<Entity, Material> {

    static {
        register(ExprMinecartMaterial.class, Material.class, "minecart material", "entities");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "minecart material";
    }

    @Override
    public @Nullable Material convert(Entity entity) {
        if (entity instanceof Minecart) {
            return ((Minecart) entity).getMinecartMaterial();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends Material> getReturnType() {
        return Material.class;
    }
}
