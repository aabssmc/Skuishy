package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.Art;
import org.bukkit.entity.Painting;

@Name("Painting - Art")
@Description("Gets/sets the art of a painting.")
@Examples({
        "set painting art of {_painting} to donkey kong"
})
@Since("2.8")
public class ExprPaintingArt extends EntityExpression<Painting, Art> {

    static {
        register(ExprPaintingArt.class, Art.class, "painting art", "entities");
    }

    @Override
    public Art get(Painting painting) {
        return painting.getArt();
    }

    @Override
    public void change(Painting painting, Art art, Changer.ChangeMode mode) {
        if (art != null && mode == Changer.ChangeMode.SET) {
            painting.setArt(art, true);
        }
    }
}
