package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Tadpole;
import org.jetbrains.annotations.Nullable;

@Name("Tadpole - Age Lock")
@Description("Gets/sets the age lock of the tadpole.")
@Examples({
        "set age lock of {_tadpole} to true"
})
@Since("2.8")
public class ExprTadpoleAgeLock extends EntityExpression<Tadpole, Boolean> {

    static {
        if (Skript.classExists("org.bukkit.entity.Tadpole")) {
            register(ExprTadpoleAgeLock.class, Boolean.class, "[tadpole] age lock", "entities");
        }
    }

    @Override
    public Boolean get(Tadpole tadpole) {
        return tadpole.getAgeLock();
    }

    @Override
    public void change(Tadpole tadpole, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            tadpole.setAgeLock(aBoolean);
        }
    }
}
