package lol.aabss.skuishy.elements.persistence.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.Skuishy;
import lol.aabss.skuishy.other.ClassInfoDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Persistence - Edit Persistent Data")
@Description({"Sets/Removes the data key from a data container.", "See ExprGetPersistentData"})
@Examples({
        "if player's data container has data key namespacedkey from \"example:key\":"
})
@Since("2.7")
public class EffEditPersistentData extends Effect {

    static {
        Skript.registerEffect(EffEditPersistentData.class,
                "set %*classinfo% data key %namespacedkey/string% of %persistentdatacontainers% to %object%",
                "set %persistentdatacontainers%'s %*classinfo% data key %namespacedkey/string% to %object%",
                "remove data key %namespacedkey/string% from %persistentdatacontainers%",
                "remove %persistentdatacontainers%'s data key %namespacedkey/string%"
        );
    }

    private Literal<ClassInfo<Object>> classInfo;
    private Expression<Object> namespacedKey;
    private Expression<PersistentDataContainer> dataContainer;
    private Expression<Object> newValue;

    @Override
    protected void execute(@NotNull Event event) {
        if (namespacedKey == null || dataContainer == null){
            return;
        }
        NamespacedKey namespacedKey = Skuishy.namespacedKeyFromObject(this.namespacedKey.getSingle(event));
        if (namespacedKey == null){
            return;
        }
        if (classInfo == null || newValue == null){
            for (PersistentDataContainer container : this.dataContainer.getArray(event)){
                container.remove(namespacedKey);
            }
        } else{
            Object newValue = this.newValue.getSingle(event);
            if (newValue == null){
                return;
            }
            for (PersistentDataContainer container : this.dataContainer.getArray(event)){
                container.set(namespacedKey, new ClassInfoDataType(classInfo.getSingle()), newValue);
            }
        }
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean b) {
        return "edit persistent data";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] expressions, int matchedPattern, @NotNull Kleenean kleenean, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0){
            classInfo = (Literal<ClassInfo<Object>>) expressions[0];
            namespacedKey = (Expression<Object>) expressions[1];
            dataContainer = (Expression<PersistentDataContainer>) expressions[2];
            newValue = (Expression<Object>) expressions[3];
        } else if (matchedPattern == 1){
            dataContainer = (Expression<PersistentDataContainer>) expressions[0];
            classInfo = (Literal<ClassInfo<Object>>) expressions[1];
            namespacedKey = (Expression<Object>) expressions[2];
            newValue = (Expression<Object>) expressions[3];
        } else if (matchedPattern == 2){
            namespacedKey = (Expression<Object>) expressions[0];
            dataContainer = (Expression<PersistentDataContainer>) expressions[1];
        } else if (matchedPattern == 3){
            dataContainer = (Expression<PersistentDataContainer>) expressions[0];
            namespacedKey = (Expression<Object>) expressions[1];
        }
        if (classInfo != null && newValue != null){
            if (classInfo.getSingle().getC() != newValue.getReturnType()){
                Skript.error("The class info must be the same type as the new value. " +
                        "("+classInfo.getSingle().getC().getSimpleName()+" and "+newValue.getReturnType().getSimpleName()+")");
                return false;
            }
        }
        return true;
    }
}
