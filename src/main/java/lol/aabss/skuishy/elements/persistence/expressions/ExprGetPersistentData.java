package lol.aabss.skuishy.elements.persistence.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.other.ClassInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static lol.aabss.skuishy.Skuishy.namespacedKeyFromObject;

@Name("Persistence - Get Persistent Data")
@Description({
        "Gets the data key from a data container, if not found, can optionally return anything.",
        "All classinfo types are supported.",
        "See EffEditPersistentData"})
@Examples({
        "set {_integer} to integer data key namespaced key from \"skuishy:rocks\" of player",
        "set {_string} to string data key \"simple:string\" or default \"\"of player"
})
@Since("2.7")
public class ExprGetPersistentData extends PropertyExpression<PersistentDataContainer, Object> {

    static {
        register(ExprGetPersistentData.class, Object.class,
                "%*classinfo% data key %namespacedkey/string% [default:(or default|if not found) %-object%]",
                "persistentdatacontainers"
        );
    }

    private Literal<ClassInfo<Object>> classInfo;
    private Expression<Object> namespacedKey;
    private Expression<Object> defaultObject;


    @Override
    protected Object @NotNull [] get(@NotNull Event event, PersistentDataContainer @NotNull [] persistentDataContainers) {
        if (namespacedKey == null){
            return new Object[]{};
        }
        Object namespacedKeyObject = namespacedKey.getSingle(event);
        NamespacedKey namespacedKey = namespacedKeyFromObject(namespacedKeyObject);
        if (namespacedKey == null){
            return new Object[]{};
        }
        List<Object> objects = new ArrayList<>();
        Object defaultObject = null;
        if (this.defaultObject != null){
            defaultObject = this.defaultObject.getSingle(event);
        }
        for (PersistentDataContainer container : persistentDataContainers){
            if (defaultObject != null){
                objects.add(container.getOrDefault(namespacedKey, new ClassInfoDataType(classInfo.getSingle()), defaultObject));
            } else {
                objects.add(container.get(namespacedKey, new ClassInfoDataType(classInfo.getSingle())));
            }
        }
        return objects.toArray(Object[]::new);
    }

    @Override
    public @NotNull Class<?> getReturnType() {
        return Object.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "persistent data";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            classInfo = (Literal<ClassInfo<Object>>) expressions[0];
            namespacedKey = (Expression<Object>) expressions[1];
            defaultObject = (Expression<Object>) expressions[2];
        } else {
            classInfo = (Literal<ClassInfo<Object>>) expressions[1];
            namespacedKey = (Expression<Object>) expressions[2];
            defaultObject = (Expression<Object>) expressions[3];
        }
        if (defaultObject != null){
            if (classInfo.getSingle().getC() != defaultObject.getReturnType()){
                Skript.error("The class info must be the same type as the new value. " +
                        "("+classInfo.getSingle().getC().getSimpleName()+" and "+defaultObject.getReturnType().getSimpleName()+")");
                return false;
            }
        }
        return true;
    }
}
