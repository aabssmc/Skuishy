package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffPermissionAttachment extends Effect {

    static {
        Skript.registerEffect(EffPermissionAttachment.class,
                "set permission of [perm[ission] attachment] %permissionattachment% to [perm[ission]] %string% with value %boolean%",
                "(remove|unset) [perm[ission]] %string% of %permissionattachment%",
                "(remove|delete) [perm[ission] attachment] %permissionattachment%"
        );
    }

    private Expression<PermissionAttachment> attach;

    private Expression<String> perm;
    private Expression<Boolean> value;


    @Override
    protected void execute(@NotNull Event e) {
        PermissionAttachment attach = this.attach.getSingle(e);
        String perm = this.perm.getSingle(e);
        Boolean value = this.value.getSingle(e);
        if (attach != null) {
            if (value == null) {
                if (perm == null) {
                    attach.remove();
                } else{
                    attach.unsetPermission(perm);
                }
            } else{
                if (perm != null) {
                    attach.setPermission(perm, value);
                }
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "permission attachment";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            attach = (Expression<PermissionAttachment>) exprs[0];
            perm = (Expression<String>) exprs[1];
            value = (Expression<Boolean>) exprs[2];
            return true;
        } else if (matchedPattern == 1){
            perm = (Expression<String>) exprs[0];
            attach = (Expression<PermissionAttachment>) exprs[1];
            return true;
        }
        attach = (Expression<PermissionAttachment>) exprs[0];
        return true;
    }
}
