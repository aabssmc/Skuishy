package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.World;

@Name("World - Has Raids")
@Description("Returns true if the world has raids.")
@Examples({
        "if target entity has caravan trail:"
})
@Since("2.0")
public class CondHasRaids extends PropertyCondition<World> {

    static {
        register(CondHasRaids.class, PropertyType.HAVE, "raids", "worlds");
    }

    @Override
    public boolean check(World world) {
        return world.hasRaids();
    }

    @Override
    protected String getPropertyName() {
        return null;
    }
}
