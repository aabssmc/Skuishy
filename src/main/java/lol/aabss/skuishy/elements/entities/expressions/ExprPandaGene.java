package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.Panda;
import org.jetbrains.annotations.Nullable;

@Name("Panda - Gene")
@Description("Gets/sets the main, hidden or combined gene of a panda.")
@Examples({
        "send combined gene of {_panda}"
})
@Since("2.8")
public class ExprPandaGene extends EntityExpression<Panda, Panda.Gene> {

    static {
        register(ExprPandaGene.class, Panda.Gene.class, "(main|:hidden|:combined) gene", "entities");
    }

    @Override
    public Panda.Gene get(Panda panda) {
        return tags.contains("hidden") ? panda.getHiddenGene() : tags.contains("combined") ? panda.getCombinedGene() : panda.getMainGene();
    }

    @Override
    public void change(Panda panda, Panda.@Nullable Gene gene, Changer.ChangeMode mode) {
        if (gene != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("hidden")) {
                panda.setHiddenGene(gene);
            } else {
                panda.setMainGene(gene);
            }
        }
    }
}
