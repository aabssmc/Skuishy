package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.Nullable;

@Name("Zombie - Can Break Doors")
@Description("Gets/sets the can break doors state of a zombie.")
@Examples({
        "set break doors state of {_zombie} to true"
})
@Since("2.8")
public class ExprZombieBreakDoors extends EntityExpression<Zombie, Boolean> {

    static {
        register(ExprZombieBreakDoors.class, Boolean.class, "[zombie] [can] break doors [mode|state]", "entities");
    }

    @Override
    public Boolean get(Zombie zombie) {
        return zombie.canBreakDoors();
    }

    @Override
    public void change(Zombie zombie, @Nullable Boolean aBoolean, Changer.ChangeMode mode) {
        if (aBoolean != null && mode == Changer.ChangeMode.SET) {
            zombie.setCanBreakDoors(aBoolean);
        }
    }
}