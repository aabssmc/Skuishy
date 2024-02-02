package lol.aabss.skuishy.elements.permissions.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static lol.aabss.skuishy.Skuishy.last_permission;

public class EffNewPermission extends Effect {

    static {
        Skript.registerEffect(EffNewPermission.class,
                "(create|make) [a] [new] permission (named|(by|with) name) %string% [and (store|save) it in %-object%]",
                "(delete|remove) %permission% [from [the] (plugin|permission) manager"
        );
    }

    private int pat;
    private Expression<String> name;
    private Expression<Permission> perm;
    private Variable<?> var;

    @Override
    protected void execute(Event e) {
        if (pat == 0){
            String perm = this.name.getSingle(e);
            if (perm != null){
                last_permission = new Permission(perm);
                Bukkit.getPluginManager().addPermission(last_permission);
                if (var != null){
                    Variables.setVariable(var.getName().toString(), perm, e, var.isLocal());
                }
            }
        } else {
            Permission perm = this.perm.getSingle(e);
            if (perm != null) {
                if (last_permission == perm){
                    last_permission = null;
                } Bukkit.getPluginManager().removePermission(perm);
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "new permission";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        pat = matchedPattern;
        if (matchedPattern == 0){
            name = (Expression<String>) exprs[0];
            if (exprs[1] instanceof Variable<?>)
                var = (Variable<?>) exprs[1];
            return true;
        }
        perm = (Expression<Permission>) exprs[0];
        return true;
    }
}
