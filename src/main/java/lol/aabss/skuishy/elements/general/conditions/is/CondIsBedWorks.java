package lol.aabss.skuishy.elements.general.conditions.is;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

@Name("World - Is Bed Works")
@Description("Returns true if beds works in the world.")
@Examples({
        "if event-world is bed works:"
})
@Since("2.0")
public class CondIsBedWorks extends PropertyCondition<World> {

    static{
        if (Skript.methodExists(World.class, "isBedWorks")) {
            register(CondIsBedWorks.class,
                    PropertyType.BE,
                    "bed[ ]work[s]",
                    "worlds");
        }
    }

    @Override
    public boolean check(World world) {
        return world.isBedWorks();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "bedworks";
    }
}
