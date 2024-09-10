package lol.aabss.skuishy.other.skript;

import com.google.common.reflect.TypeToken;
import org.bukkit.entity.Entity;

import java.lang.reflect.Method;

public interface EntityStatement<T extends Entity> {

    default boolean accepts(Entity entity) {
        return new TypeToken<T>(getClass()){}.getRawType().isAssignableFrom(entity.getClass());
    }

    default boolean setExists() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("set")) {
                return true;
            }
        }
        return false;
    }
}
