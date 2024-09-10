package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Axolotl;
import org.jetbrains.annotations.Nullable;

@Name("Axolotl - Variant")
@Description("Gets/sets the variant of an axolotl.")
@Examples({
        "set axolotl variant of {_axolotl} to blue"
})
@Since("2.8")
public class ExprAxolotlVariant extends EntityExpression<Axolotl, Axolotl.Variant> {

    static {
        register(ExprAxolotlVariant.class, Axolotl.Variant.class, "axolotl variant", "entities");
    }

    @Override
    public Axolotl.Variant get(Axolotl axolotl) {
        return axolotl.getVariant();
    }

    @Override
    public void change(Axolotl axolotl, @Nullable Axolotl.Variant variant, Changer.ChangeMode mode) {
        if (variant != null && mode == Changer.ChangeMode.SET) {
            axolotl.setVariant(variant);
        }
    }

}