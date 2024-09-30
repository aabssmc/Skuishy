package lol.aabss.skuishy.elements.general.conditions.is;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityCondition;
import org.bukkit.entity.Bat;

@Name("Bat - Is Awake")
@Description("Returns true if the bat is awake.")
@Examples({
        "if last spawned bat is awake:"
})
@Since("2.8")
public class CondIsAwake extends EntityCondition<Bat> {

    static{
        register(CondIsAwake.class, "awake", "entities");
    }

    @Override
    protected boolean run(Bat bat) {
        return bat.isAwake();
    }
}
