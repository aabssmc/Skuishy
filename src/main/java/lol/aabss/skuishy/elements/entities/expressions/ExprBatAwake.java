package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Bat;
import org.jetbrains.annotations.Nullable;

@Name("Bat - Awake")
@Description("Gets/sets the awake state of a bat.")
@Examples({
        "set awake state of {_bat} to true"
})
@Since("2.8")
public class ExprBatAwake extends EntityExpression<Bat, Boolean> {

    static {
        register(ExprBatAwake.class, Boolean.class, "[bat] awake [mode|state]", "entities");
    }


    @Override
    public Boolean get(Bat bat) {
        return bat.isAwake();
    }

    @Override
    public void change(Bat bat, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            bat.setAwake(aBoolean);
        }
    }
}

