package lol.aabss.skuishy.other;

import com.google.common.base.Preconditions;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Cat;

public enum CatVariant {
    TABBY("tabby"),
    BLACK("black"),
    RED("red"),
    SIAMESE("siamese"),
    BRITISH_SHORTHAIR("british_shorthair"),
    CALICO("calico"),
    PERSIAN("persian"),
    RAGDOLL("ragdoll"),
    WHITE("white"),
    JELLIE("jellie"),
    ALL_BLACK("all_black");

    private final String key;

    CatVariant(String key) {
        this.key = key;
    }

    public Cat.Type toBukkit(){
        NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
        Cat.Type type = Registry.CAT_VARIANT.get(namespacedKey);
        Preconditions.checkNotNull(type, "No cat type found for %s. This is a bug.", namespacedKey);
        return type;
    }

    public static CatVariant fromBukkit(Cat.Type bukkit) {
        return CatVariant.valueOf(bukkit.key().value().toUpperCase());
    }
}
