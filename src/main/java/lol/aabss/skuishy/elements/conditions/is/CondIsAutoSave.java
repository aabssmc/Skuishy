package lol.aabss.skuishy.elements.conditions.is;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.World;
import org.eclipse.jdt.annotation.NonNull;

@Name("World - Is Auto Save")
@Description("Returns true if the world auto saves.")
@Examples({
        "if event-world is auto save:"
})
@Since("2.0")
public class CondIsAutoSave extends PropertyCondition<World> {

    static{
        register(CondIsAutoSave.class,
                PropertyType.BE,
                "auto[ ]save[s]",
                "worlds");
    }

    @Override
    public boolean check(World world) {
        return world.isAutoSave();
    }

    @Override
    protected @NonNull String getPropertyName() {
        return "autosave";
    }
}
