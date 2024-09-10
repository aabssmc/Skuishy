package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Axolotl;
import org.jetbrains.annotations.Nullable;

@Name("Axolotl - Playing Dead")
@Description("Gets/sets the playing dead state of an axolotl.")
@Examples({
        "set playing dead state of {_axolotl} to true"
})
@Since("2.8")
public class ExprAxolotlPlayingDead extends EntityExpression<Axolotl, Boolean> {

    static {
        register(ExprAxolotlPlayingDead.class, Boolean.class, "[axolotl] playing dead [mode|state]", "entities");
    }

    @Override
    public Boolean get(Axolotl axolotl) {
        return axolotl.isPlayingDead();
    }

    @Override
    public void change(Axolotl axolotl, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            axolotl.setPlayingDead(aBoolean);
        }
    }

}

