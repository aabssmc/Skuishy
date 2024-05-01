package lol.aabss.skuishy.elements.skins.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.blueprints.Blueprint;
import lol.aabss.skuishy.other.blueprints.BlueprintPart;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Name("Blueprint - Blueprint Part")
@Description("Gets a part of a blueprint.")
@Examples({
        "set {_head} to head of {_blueprint}",
        "set {_torso} to torso of {_blueprint}",
        "set {_leftarm} to left arm without the outer layer of {_blueprint}",
})
@Since("2.6")
public class ExprBlueprintPart extends PropertyExpression<Blueprint, Blueprint> {

    static {
        register(ExprBlueprintPart.class, Blueprint.class,
                "(:head|:torso|(rarm:right arm|larm:left arm)|rleg:right leg|lleg:left leg) [without:without [the] outer layer]",
                "blueprints"
        );
    }

    private BlueprintPart.Part part;
    private boolean layer;

    @Override
    protected Blueprint @NotNull [] get(@NotNull Event event, Blueprint[] source) {
        List<Blueprint> parts = new ArrayList<>();
        for (Blueprint print : source) {
            parts.add(BlueprintPart.blueprintPart(print, part, layer));
        }
        return parts.toArray(Blueprint[]::new);
    }

    @Override
    public @NotNull Class<? extends Blueprint> getReturnType() {
        return Blueprint.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "blueprint";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        setExpr((Expression<? extends Blueprint>) exprs[0]);
        if (parseResult.hasTag("head")){
            part = BlueprintPart.Part.HEAD;
        } else if (parseResult.hasTag("torso")){
            part = BlueprintPart.Part.TORSO;
        } else if (parseResult.hasTag("rleg")){
            part = BlueprintPart.Part.RIGHT_ARM;
        } else if (parseResult.hasTag("lleg")){
            part = BlueprintPart.Part.LEFT_LEG;
        } else if (parseResult.hasTag("rarm")){
            part = BlueprintPart.Part.RIGHT_ARM;
        } else if (parseResult.hasTag("larm")){
            part = BlueprintPart.Part.LEFT_ARM;
        }
        layer = !parseResult.hasTag("without");
        return true;
    }
}
