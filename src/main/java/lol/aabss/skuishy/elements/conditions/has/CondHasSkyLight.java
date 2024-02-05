package lol.aabss.skuishy.elements.conditions.has;

import ch.njol.skript.Skript;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

@Name("World - Has SkyLight")
@Description("Returns true if the world has skylight.")
@Examples({
        "if target entity has skylight:"
})
@Since("2.0")
public class CondHasSkyLight extends PropertyCondition<World> {

    static {
        if (Skript.methodExists(World.class, "hasSkyLight")) {
            register(CondHasSkyLight.class, PropertyType.HAVE, "skylight", "worlds");
        }
    }

    @Override
    public boolean check(World world) {
        return world.hasSkyLight();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "skylight";
    }
}
