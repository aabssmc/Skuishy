package lol.aabss.skuishy.elements.conditions.can;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.World;
import org.eclipse.jdt.annotation.NonNull;

@Name("World - Can Generate Structures")
@Description("Returns true if the world can generate structures.")
@Examples({
        "if event-world can generate structures:"
})
@Since("2.0")
public class CondCanGenerateStructures extends PropertyCondition<World> {

    static{
        register(CondCanGenerateStructures.class,
                PropertyType.CAN,
                "(generate|make) structures",
                "worlds");
    }

    @Override
    public boolean check(World world) {
        return world.canGenerateStructures();
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "generate structures";
    }
}
