package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Camel;
import org.jetbrains.annotations.Nullable;

@Name("Camel - Dashing State")
@Description("Gets/sets the dashing state of a camel.")
@Examples({
        "set dashing state of {_camel} to true"
})
@Since("2.8")
public class ExprCamelDash extends EntityExpression<Camel, Boolean> {

    static {
        register(ExprCamelDash.class, Boolean.class, "[camel] dash[ing] [state|mode]", "entities");
    }

    @Override
    public Boolean get(Camel camel) {
        return camel.isDashing();
    }

    @Override
    public void change(Camel camel, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            camel.setDashing(aBoolean);
        }
    }
}