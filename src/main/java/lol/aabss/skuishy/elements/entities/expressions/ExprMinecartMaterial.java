package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;

@Name("Minecart - Material")
@Description("Gets/sets the material of a minecart.")
@Examples({
        "set {_material} to minecart material of {_minecart}"
})
@Since("2.8")
public class ExprMinecartMaterial extends EntityExpression<Minecart, Material> {

    static {
        register(ExprMinecartMaterial.class, Material.class, "minecart material", "entities");
    }

    @Override
    public Material get(Minecart minecart) {
        return minecart.getMinecartMaterial();
    }

}
