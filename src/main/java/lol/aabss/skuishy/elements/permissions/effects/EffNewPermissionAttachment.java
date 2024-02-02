package lol.aabss.skuishy.elements.permissions.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.*;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static lol.aabss.skuishy.Skuishy.instance;
import static lol.aabss.skuishy.Skuishy.last_permission_attachment;

@Name("Permissions - Create Permission Attachment")
@Description("Creates a new permission attachment for an entity.")
@Examples({
        "# gives the player essentials.fly for 10 seconds",
        "# method 1:",
        "make a empty permission attachment for player and store it in {_var}",
        "set permission of {_var} to \"essentials.fly\" with value true",
        "wait 10 seconds",
        "delete attachment {_var}",
        "#method 2:",
        "make a empty permission attachment for player for 10 seconds and store it in {_var}",
        "set permission of {_var} to \"essentials.fly\" with value true",
        "method 3:",
        "make a permission attachment with permission \"essentials.fly\" with value true for player for 10 seconds and store it in {_var}"
})
@Since("2.1")
public class EffNewPermissionAttachment extends EffectSection {

    static {
        Skript.registerSection(EffNewPermissionAttachment.class,
                "(create|make) [a] [new] empty perm[ission] attachment for %entity% [time:for %-timespan%] [and (store|save) it in %-object%]",
                "(create|make) [a] [new] perm[ission] attachment [with perm[ission]] %permission%" +
                        " [[and] with value %-boolean%] for %entity% [time:for %-timespan%] [and (store|save) it in %-object%]"

        );
    }

    private Expression<Permission> perm;
    private Expression<Boolean> value;

    private Expression<Entity> entity;
    private Expression<Timespan> time;
    private Variable<?> variable;

    @Override
    protected @Nullable TriggerItem walk(Event e) {
        Entity entity = this.entity.getSingle(e);
        if (entity != null) {
            PermissionAttachment attach;
            if (this.perm == null || this.value == null) {
                if (this.time != null) {
                    Timespan time = this.time.getSingle(e);
                    if (time != null) {
                        attach = entity.addAttachment(instance, (int) time.getTicks_i());
                    } else {
                        attach = entity.addAttachment(instance);
                    }
                } else {
                    attach = entity.addAttachment(instance);
                }
            } else {
                Permission perm = this.perm.getSingle(e);
                if (this.time != null) {
                    Timespan time = this.time.getSingle(e);
                    if (perm != null && time != null) {
                        if (value == null) {
                            attach = entity.addAttachment(instance, perm.getName(), true, (int) time.getTicks_i());
                        } else {
                            Boolean value = this.value.getSingle(e);
                            attach = entity.addAttachment(instance, perm.getName(), Objects.requireNonNullElse(value, true), (int) time.getTicks_i());
                        }
                    } else {
                        attach = entity.addAttachment(instance);
                    }
                } else {
                    if (perm != null) {
                        if (value == null) {
                            attach = entity.addAttachment(instance, perm.getName(), true);
                        } else {
                            Boolean value = this.value.getSingle(e);
                            attach = entity.addAttachment(instance, perm.getName(), Objects.requireNonNullElse(value, true));
                        }
                    } else {
                        attach = entity.addAttachment(instance);
                    }
                }
            }
            if (this.variable != null) {
                Variables.setVariable(
                        variable.getName().toString(),
                        attach, e, variable.isLocal()
                );
            }
            last_permission_attachment = attach;
        }
        return getTrigger();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "new permission attachment";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult, @Nullable SectionNode sectionNode, @Nullable List<TriggerItem> triggerItems) {
        if (matchedPattern == 0) {
            entity = (Expression<Entity>) exprs[0];
            time = (Expression<Timespan>) exprs[1];
            variable = (Variable<?>) exprs[2];
            return true;
        }
        perm = (Expression<Permission>) exprs[0];
        value = (Expression<Boolean>) exprs[1];
        entity = (Expression<Entity>) exprs[2];
        time = (Expression<Timespan>) exprs[3];
        variable = (Variable<?>) exprs[4];
        return true;
    }
}
