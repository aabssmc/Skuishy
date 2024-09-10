package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.minecart.HopperMinecart;
import org.jetbrains.annotations.Nullable;

@Name("Minecart - Enabled State")
@Description("Gets/sets the enabled state of a hopper minecart.")
@Examples({
        "set enabled state of {_minecart} to true"
})
@Since("2.8")
public class ExprMinecartEnabled extends EntityExpression<HopperMinecart, Boolean> {

    static {
        register(ExprMinecartEnabled.class, Boolean.class, "[minecart|hopper] enabled (mode|state)", "entities");
    }

    @Override
    public Boolean get(HopperMinecart hopperMinecart) {
        return hopperMinecart.isEnabled();
    }

    @Override
    public void change(HopperMinecart hopperMinecart, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            hopperMinecart.setEnabled(aBoolean);
        }
    }
}

