package lol.aabss.skuishy.elements.permissions.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static lol.aabss.skuishy.Skuishy.last_permission_attachment;

@Name("Permissions - Last Permission Attachment")
@Description("Gets the last made permission attachment.")
@Examples({
        "set {_perm} to last made permission attachment"
})
@Since("2.1")
public class ExprLastPermissionAttachment extends SimpleExpression<PermissionAttachment> {

    static {
        Skript.registerExpression(ExprLastPermissionAttachment.class, PermissionAttachment.class, ExpressionType.SIMPLE,
                "[the] last[ly] [(created|made)] perm[ission] attachment"
        );
    }

    @Override
    protected PermissionAttachment @NotNull [] get(@NotNull Event event) {
        return new PermissionAttachment[]{last_permission_attachment};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends PermissionAttachment> getReturnType() {
        return PermissionAttachment.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "last permission attachment";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        return true;
    }
}
