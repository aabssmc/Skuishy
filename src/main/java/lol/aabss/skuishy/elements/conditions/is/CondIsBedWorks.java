package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.World;
import org.eclipse.jdt.annotation.NonNull;

@Name("World - Is Bed Works")
@Description("Returns true if beds works in the world.")
@Examples({
        "if event-world is bed works:"
})
@Since("2.0")
public class CondIsBedWorks extends PropertyCondition<World> {

    static{
        register(CondIsAutoSave.class,
                PropertyType.BE,
                "bed[ ]work[s]",
                "worlds");
    }

    @Override
    public boolean check(World world) {
        return world.isBedWorks();
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "bedworks";
    }
}
