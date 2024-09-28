package lol.aabss.skuishy.elements.entities.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.util.Color;
import ch.njol.skript.util.SkriptColor;
import lol.aabss.skuishy.other.skript.EntityExpression;
import org.bukkit.entity.TropicalFish;
import org.jetbrains.annotations.Nullable;

@Name("Tropical Fish - Color")
@Description("Gets/sets the state of an tropical fish.")
@Examples({
        "set body color of {_tropicalfish} to red"
})
@Since("2.8")
public class ExprTropicalFishColor extends EntityExpression<TropicalFish, Color> {

    static {
        register(ExprTropicalFishColor.class, Color.class, "[tropical[ ]fish] (:body|pattern) color", "entities");
    }

    @Override
    public Color get(TropicalFish tropicalfish) {
        return tags.contains("body") ? SkriptColor.fromDyeColor(tropicalfish.getBodyColor()) : SkriptColor.fromDyeColor(tropicalfish.getPatternColor());
    }

    @Override
    public void change(TropicalFish tropicalfish, @Nullable Color type, Changer.ChangeMode mode) {
        if (type != null && mode == Changer.ChangeMode.SET) {
            if (tags.contains("body")) {
                tropicalfish.setBodyColor(type.asDyeColor());
            } else {
                tropicalfish.setPatternColor(type.asDyeColor());
            }
        }
    }
}