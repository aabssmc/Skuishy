package lol.aabss.skuishy.elements.entities.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import org.bukkit.entity.Panda;
import org.jetbrains.annotations.NotNull;

@Name("Panda Gene - Is Recessive")
@Description("Returns true if the panda gene is recessive.")
@Examples({
        "if normal is recessive:"
})
@Since("2.8")
public class CondGeneIsRecessive extends PropertyCondition<Panda.Gene> {

    static {
        register(CondGeneIsRecessive.class, "recessive", "pandagenes");
    }

    @Override
    public boolean check(Panda.Gene gene) {
        return gene.isRecessive();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "recessive";
    }
}
