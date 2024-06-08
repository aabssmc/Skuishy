package lol.aabss.skuishy.elements.persistence.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.ClassInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Name("Persistence - Has Data Key")
@Description("Returns true of the data container has a certain data key.")
@Examples({
        "if player's data container has data key \"example:key\":"
})
@Since("2.7")
public class CondHasDataKey extends Condition {

    static {
        Skript.registerCondition(CondHasDataKey.class,
                "%datacontainer% (has|have) [%-*classinfo%] data key %namespacedkey/string%",
                "%datacontainer% (doesn't|does not|do not|don't) have [%-*classinfo%] data key %namespacedkey/string%"
        );
    }

    private boolean has;
    private Expression<PersistentDataContainer> container;
    private Literal<ClassInfo<Object>> classInfo;
    private Expression<Object> namespacedkey;

    @Override
    public boolean check(@NotNull Event event) {
        PersistentDataType<?, ?> dataType = null;
        if (this.classInfo != null){
            ClassInfo<Object> classInfo = this.classInfo.getSingle();
            dataType = new ClassInfoDataType(classInfo);
        }
        PersistentDataContainer container = this.container.getSingle(event);
        Object namespacedkeyObject = this.namespacedkey.getSingle(event);
        if (container != null){
            NamespacedKey namespacedKey = Skuishy.namespacedKeyFromObject(namespacedkeyObject);
            if (namespacedKey == null){
                return false;
            }
            return dataType != null ? has == container.has(namespacedKey, dataType) : has == container.has(namespacedKey);
        }
        return false;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "data container has data key";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        has = matchedPattern == 0;
        container = (Expression<PersistentDataContainer>) expressions[0];
        classInfo = (Literal<ClassInfo<Object>>) expressions[1];
        namespacedkey = (Expression<Object>) expressions[2];
        return true;
    }
}
