package lol.aabss.skuishy.other;

import ch.njol.skript.classes.ClassInfo;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

/**
 * Makes a {@link PersistentDataType} from a ClassInfo
 * <p>
 * Example: <code>PersistentDataType&lt;Entity, Entity&gt; dataType = new ClassInfoDataType(Classes.getClassInfo("entity"))</code>
 */
public class ClassInfoDataType implements PersistentDataType<Object, Object> {

    private final Class<Object> clazz;

    public ClassInfoDataType(ClassInfo<Object> clazz) {
        this.clazz = clazz.getC();
    }

    @Override
    public @NotNull Class<Object> getPrimitiveType() {
        return clazz;
    }

    @Override
    public @NotNull Class<Object> getComplexType() {
        return clazz;
    }

    @Override
    public @NotNull Object toPrimitive(@NotNull Object object, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return object;
    }

    @Override
    public @NotNull Object fromPrimitive(@NotNull Object object, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return object;
    }
}
