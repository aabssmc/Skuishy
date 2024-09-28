package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.TropicalFish;
import org.jetbrains.annotations.Nullable;

@Name("Tropical Fish - Pattern")
@Description("Gets/sets the pattern of an tropical fish.")
@Examples({
        "set tropical fish pattern of {_tropicalfish} to idling"
})
@Since("2.8")
public class ExprTropicalFishPattern extends EntityExpression<TropicalFish, TropicalFish.Pattern> {

    static {
        register(ExprTropicalFishPattern.class, TropicalFish.Pattern.class, "tropical[ ]fish (pattern|type)", "entities");
    }

    @Override
    public TropicalFish.Pattern get(TropicalFish tropicalfish) {
        return tropicalfish.getPattern();
    }

    @Override
    public void change(TropicalFish tropicalfish, @Nullable TropicalFish.Pattern type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            tropicalfish.setPattern(type);
        }
    }
}