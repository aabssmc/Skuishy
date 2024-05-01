package lol.aabss.skuishy.elements.permissions.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Name("Permissions - Edit Permission Attachment")
@Description("Sets/Removes a permission of a permission attachment or deletes a permission attachment.")
@Examples({
        "set permission of last permission attachment to \"essentials.fly\" with value true",
        "remove \"essentials.fly\" from last permission attachment",
        "delete last permission attachment"
})
@Since("2.1")
public class EffPermissionAttachment extends Effect {

    static {
        Skript.registerEffect(EffPermissionAttachment.class,
                "set permission of [perm[ission] attachment] %permissionattachment% to [perm[ission]] %bukkitpermission% [with value %-boolean%]",
                "(remove|unset) [perm[ission]] %bukkitpermission% (of|from) %permissionattachment%",
                "(remove|delete) [perm[ission] attachment] %permissionattachment%",
                "add [perm[ission]] %bukkitpermission% [with value %-boolean%] to permissions of [perm[ission] attachment] %permissionattachment%"
        );
    }

    private Expression<PermissionAttachment> attach;

    private Expression<Permission> perm;
    private Expression<Boolean> value;
    private String part;


    @Override
    protected void execute(@NotNull Event e) {
        PermissionAttachment attach = this.attach.getSingle(e);
        if (attach != null) {
            if (Objects.equals(part, "set") || Objects.equals(part, "add")){
                Permission perm = this.perm.getSingle(e);
                if (value == null){
                    if (perm != null){
                        attach.setPermission(perm, true);
                    }
                } else{
                    Boolean value = this.value.getSingle(e);
                    if (perm != null && value != null){
                        attach.setPermission(perm, value);
                    }
                }
            } else if (Objects.equals(part, "unset")){
                Permission perm = this.perm.getSingle(e);
                if (perm != null){
                    attach.unsetPermission(perm);
                }
            } else if (Objects.equals(part, "delete")) {
                attach.remove();
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
            perm = (Expression<Permission>) exprs[1];
            value = (Expression<Boolean>) exprs[2];
            part = "set";
            return true;
        } else if (matchedPattern == 1){
            perm = (Expression<Permission>) exprs[0];
            attach = (Expression<PermissionAttachment>) exprs[1];
            part = "unset";
            return true;
        } else if (matchedPattern == 2) {
            attach = (Expression<PermissionAttachment>) exprs[0];
            part = "delete";
            return true;
        }
        perm = (Expression<Permission>) exprs[0];
        value = (Expression<Boolean>) exprs[1];
        attach = (Expression<PermissionAttachment>) exprs[2];
        part = "add";
        return true;
    }
}
