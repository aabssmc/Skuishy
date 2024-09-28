package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.WitherSkull;
import org.jetbrains.annotations.Nullable;

@Name("Wither Skull - Charged")
@Description("Gets/sets the wither skull charged.")
@Examples({
        "set bullet charged of {_witherskull} to charged player"
})
@Since("2.8")
public class ExprWitherSkullCharged extends EntityExpression<WitherSkull, Boolean> {

    static {
        register(ExprWitherSkullCharged.class, Boolean.class, "[shulker] bullet charged", "entities");
    }

    @Override
    public Boolean get(WitherSkull bullet) {
        return bullet.isCharged();
    }

    @Override
    public void change(WitherSkull glowSquid, @Nullable Boolean bool, Changer.ChangeMode mode) {
        if (bool != null && mode == Changer.ChangeMode.SET) {
            glowSquid.setCharged(bool);
        }
    }
}