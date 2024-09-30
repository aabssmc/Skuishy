package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Vex;

import javax.annotation.Nullable;

@Name("Vex - Summoner")
@Description("Gets/sets the summoner of a vex.")
@Examples({
        "set summoner of {_vex} to {_mob}"
})
@Since("2.8")
public class ExprVexSummoner extends EntityExpression<Vex, LivingEntity> {

    static {
        register(ExprVexSummoner.class, LivingEntity.class, "[vex] summoner", "entities");
    }

    @Override
    public LivingEntity get(Vex vex) {
        return vex.getSummoner();
    }

    @Override
    public void change(Vex vex, @Nullable LivingEntity summoner, Changer.ChangeMode mode) {
        if (summoner instanceof Mob && mode == Changer.ChangeMode.SET) {
            vex.setSummoner((Mob) summoner);
        }
    }
}
