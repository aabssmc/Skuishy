package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.World;
import org.eclipse.jdt.annotation.NonNull;

@Name("World - Has Ceiling")
@Description("Returns true if the world has a ceiling.")
@Examples({
        "if target entity has caravan trail:"
})
@Since("2.0")
public class CondHasCeiling extends PropertyCondition<World> {

    static{
        register(CondHasCeiling.class, PropertyType.HAVE, "[a] ceiling", "worlds");
    }

    @Override
    public boolean check(World world) {
        return world.hasCeiling();
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "ceiling";
    }
}
