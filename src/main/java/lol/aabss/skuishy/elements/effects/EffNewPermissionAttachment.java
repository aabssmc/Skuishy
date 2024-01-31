package lol.aabss.skuishy.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.variables.Variables;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
public class EffNewPermissionAttachment extends Effect {

    static {
        Skript.registerEffect(EffNewPermissionAttachment.class,
                "(create|make) [a] [new] empty perm[ission] attachment for %entity% [time:for %-timespan%] [and (store|save) it in %-object%]",
                "(create|make) [a] [new] perm[ission] attachment (with perm[ission]|with name)|named) %string%" +
                        " [and] with value %boolean% for %entity% [time:for %-timespan%] [and (store|save) it in %-object%]"

        );
    }

    private Expression<String> perm;
    private Expression<Boolean> value;

    private Expression<Entity> entity;
    private Expression<Timespan> time;
    private Variable<?> variable;

    @Override
    protected void execute(@NotNull Event e) {
        Entity entity = this.entity.getSingle(e);
        if (entity != null) {
            PermissionAttachment attach;
            if (this.perm == null || this.value == null) {
                if (this.time != null) {
                    Timespan time = this.time.getSingle(e);
                    if (time != null) {
                        attach = entity.addAttachment(instance, (int) time.getTicks_i());
                    } else{
                        attach = entity.addAttachment(instance);
                    }
                } else {
                    attach = entity.addAttachment(instance);
                }
            } else {
                String perm = this.perm.getSingle(e);
                Boolean value = this.value.getSingle(e);
                if (this.time != null) {
                    Timespan time = this.time.getSingle(e);
                    if (perm != null && value != null && time != null) {
                        attach = entity.addAttachment(instance, perm, value, (int) time.getTicks_i());
                    } else{
                        attach = entity.addAttachment(instance);
                    }
                } else {
                    if (perm != null && value != null) {
                        attach = entity.addAttachment(instance, perm, value);
                    } else{
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
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "new permission attachment";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            entity = (Expression<Entity>) exprs[0];
            time = (Expression<Timespan>) exprs[1];
            variable = (Variable<?>) exprs[2];
            return true;
        }
        perm = (Expression<String>) exprs[0];
        value = (Expression<Boolean>) exprs[1];
        entity = (Expression<Entity>) exprs[2];
        time = (Expression<Timespan>) exprs[3];
        variable = (Variable<?>) exprs[4];
        return true;
    }
}
