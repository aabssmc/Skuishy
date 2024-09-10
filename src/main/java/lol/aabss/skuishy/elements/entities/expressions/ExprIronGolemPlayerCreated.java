package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.IronGolem;
import org.jetbrains.annotations.Nullable;

@Name("Iron Golem - Player Created ")
@Description("Gets/sets the player created state of a iron golem.")
@Examples({
        "set player created state of {_irongolem} to true"
})
@Since("2.8")
public class ExprIronGolemPlayerCreated extends EntityExpression<IronGolem, Boolean> {

    static {
        register(ExprIronGolemPlayerCreated.class, Boolean.class, "[[iron] golem] player created [state|mode]", "entities");
    }

    @Override
    public Boolean get(IronGolem ironGolem) {
        return ironGolem.isPlayerCreated();
    }

    @Override
    public void change(IronGolem ironGolem, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            ironGolem.setPlayerCreated(aBoolean);
        }
    }
}

