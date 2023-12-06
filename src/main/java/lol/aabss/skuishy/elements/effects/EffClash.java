package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Other - MasterClashers")
@Description("makes masterclashers say something.")
@Examples({
        "make masterclashers say \"ok\""
})
@Since("1.7")

public class EffClash extends Effect {

    static{
        Skript.registerEffect(EffClash.class,
                "make master[ ]clashers say %string%"
        );
    }

    private Expression<String> msg;

    @Override
    protected void execute(@NotNull Event e) {
        Player p = Bukkit.getPlayer("MasterClashers");
        String msg = this.msg.getSingle(e);
        assert msg != null; assert p != null;
        p.chat(msg);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "make masterclashers say something";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (Bukkit.getPlayer("MasterClashers") == null){
            Skript.error("MasterClashers is not online!");
            return false;
        }
        this.msg = (Expression<String>) exprs[0];
        return true;
    }
}
