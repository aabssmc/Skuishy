package lol.aabss.skuishy.elements.decentholograms.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.jetbrains.annotations.NotNull;

@Name("Decent Holograms - Hologram Toggle")
@Description("Returns true if a hologram is either disabled or enabled.")
@Examples({
        "if hologram named \"hello\" is disabled:",
        "if hologram named \"hello\" is enabled:"
})
@Since("2.6")
@RequiredPlugins("DecentHolograms")
public class CondHologramToggle extends PropertyCondition<Hologram> {

    static {
        register(CondHologramToggle.class, PropertyType.BE,"(en|:dis)abled", "holograms");
    }

    private static boolean dis;

    @Override
    public boolean check(Hologram hologram) {
        return dis ? hologram.isDisabled() : hologram.isEnabled();
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "hologram is " +(dis?"dis":"en")+ "abled";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        dis = parseResult.hasTag("dis");
        return super.init(exprs, matchedPattern, isDelayed, parseResult);
    }
}
