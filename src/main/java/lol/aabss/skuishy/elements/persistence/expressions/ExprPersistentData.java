package lol.aabss.skuishy.elements.persistence.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.UnparsedLiteral;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.ClassInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static lol.aabss.skuishy.Skuishy.namespacedKeyFromObject;

@Name("Persistence - Persistent Data")
@Description({
        "Gets the data key from a data container, if not found, can optionally return anything.",
        "All classinfo types are supported."})
@Examples({
        "set {_integer} to integer data key namespaced key from \"skuishy:rocks\" of player's data container",
        "set {_string} to string data key \"simple:string\" or default \"\" of player's data container"
})
@Since("2.7")
public class ExprPersistentData extends PropertyExpression<PersistentDataContainer, Object> {

    static {
        register(ExprPersistentData.class, Object.class,
                "[%-*classinfo%] data key %namespacedkey/string% [default:(or default|if not found) %-object%]",
                "persistentdatacontainers"
        );
    }

    private Literal<ClassInfo<Object>> classInfo;
    private Expression<Object> namespacedKey;
    private Expression<Object> defaultObject;

    @Override
    protected Object @NotNull [] get(@NotNull Event event, PersistentDataContainer @NotNull [] persistentDataContainers) {
        if (namespacedKey == null || classInfo == null){
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
            if (namespacedKey instanceof UnparsedLiteral) {
                namespacedKey = LiteralUtils.defendExpression(namespacedKey);
            }
            defaultObject = (Expression<Object>) expressions[2];
            if (defaultObject instanceof UnparsedLiteral) {
                defaultObject = LiteralUtils.defendExpression(defaultObject);
            }
            setExpr((Expression<? extends PersistentDataContainer>) expressions[3]);
        } else {
            setExpr((Expression<? extends PersistentDataContainer>) expressions[0]);
            classInfo = (Literal<ClassInfo<Object>>) expressions[1];
            namespacedKey = (Expression<Object>) expressions[2];
            if (namespacedKey instanceof UnparsedLiteral) {
                namespacedKey = LiteralUtils.defendExpression(namespacedKey);
            }
            defaultObject = (Expression<Object>) expressions[3];
            if (defaultObject instanceof UnparsedLiteral) {
                defaultObject = LiteralUtils.defendExpression(namespacedKey);
            }
        }
        if (defaultObject != null){
            if (classInfo.getSingle().getC() != defaultObject.getReturnType()){
                Skript.error("The class info must be the same type as the new value. " +
                        "("+classInfo.getSingle().getC().getSimpleName()+" and "+defaultObject.getReturnType().getSimpleName()+")");
                return false;
            }
            return LiteralUtils.canInitSafely(namespacedKey, classInfo, defaultObject);
        }
        return LiteralUtils.canInitSafely(namespacedKey, classInfo);
    }

    @Override
    public Class<?> @NotNull [] acceptChange(Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE){
            return CollectionUtils.array(Object.class);
        }
        return null;
    }

    @Override
    public void change(@NotNull Event event, Object @NotNull [] delta, Changer.@NotNull ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET){
            if (namespacedKey == null || classInfo == null){
                return;
            }
            NamespacedKey namespacedKey = Skuishy.namespacedKeyFromObject(this.namespacedKey.getSingle(event));
            if (namespacedKey == null){
                return;
            }
            for (PersistentDataContainer container : this.getExpr().getArray(event)){
                container.set(namespacedKey, new ClassInfoDataType(classInfo.getSingle()), delta[0]);
            }
        } else if (mode == Changer.ChangeMode.REMOVE){
            if (namespacedKey == null){
                return;
            }
            NamespacedKey namespacedKey = Skuishy.namespacedKeyFromObject(this.namespacedKey.getSingle(event));
            if (namespacedKey == null){
                return;
            }
            for (PersistentDataContainer container : this.getExpr().getArray(event)){
                container.remove(namespacedKey);
            }
        }
    }
}
