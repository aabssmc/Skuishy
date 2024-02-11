package lol.aabss.skuishy.elements.general.effects;

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
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.eclipse.jdt.annotation.Nullable;

@Name("Server - Reload")
@Description({
        """
                Reloads the server, refreshing settings and plugin information.
                
                Reloads the whitelist from disk.
                
                Reloads only the Minecraft data for the server. This includes custom advancements and loot tables.
                
                Reloads the Permissions in permissions.yml
                
                Reloads the Command Aliases in commands.yml
                """
})
@Examples({
        "reload the server # use at risk!",
        "reload the whitelist",
        "reload all data",
        "reload all of the permissions",
        "reload command aliases"
})
@Since("2.1")
public class EffReload extends Effect {

    static {
        Skript.registerEffect(EffReload.class,
                "reload [the] server",
                "reload [the] whitelist",
                "reload [all [of the]] data",
                "reload [all [of the]] permissions",
                "reload [all [of the]] command aliases"
        );
    }

    private int pat;

    @Override
    protected void execute(@NotNull Event e) {
        if (pat == 0){
            Bukkit.reload();
        } else if (pat == 1){
            Bukkit.reloadWhitelist();
        } else if (pat == 2){
            Bukkit.reloadData();
        } else if (pat == 3){
            Bukkit.reloadPermissions();
        } else if (pat == 4){
            Bukkit.reloadCommandAliases();
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "reload";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        pat = matchedPattern;
        return true;
    }
}
