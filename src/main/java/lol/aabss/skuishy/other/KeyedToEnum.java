package lol.aabss.skuishy.other;

import com.google.common.base.Preconditions;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;

public class KeyedToEnum {
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

    public enum FrogVariant {
        TEMPERATE("temperate"),
        WARM("warm"),
        COLD("cold");

        private final String key;

        FrogVariant(String key) {
            this.key = key;
        }

        public Frog.Variant toBukkit(){
            NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
            Frog.Variant type = Registry.FROG_VARIANT.get(namespacedKey);
            Preconditions.checkNotNull(type, "No frog type found for %s. This is a bug.", namespacedKey);
            return type;
        }

        public static FrogVariant fromBukkit(Frog.Variant bukkit) {
            return FrogVariant.valueOf(bukkit.key().value().toUpperCase());
        }
    }

    public enum WolfVariant {
        TEMPERATE("temperate"),
        WARM("warm"),
        COLD("cold"),
        PALE("pale"),
        SPOTTED("spotted"),
        SNOWY("snowy"),
        BLACK("black"),
        ASHEN("ashen"),
        RUSTY("rusty"),
        WOODS("woods"),
        CHESTNUT("chestnut"),
        STRIPED("striped");

        private final String key;

        WolfVariant(String key) {
            this.key = key;
        }

        public Wolf.Variant toBukkit(){
            NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
            Wolf.Variant type = RegistryAccess.registryAccess().getRegistry(RegistryKey.WOLF_VARIANT).get(namespacedKey);
            Preconditions.checkNotNull(type, "No wolf type found for %s. This is a bug.", namespacedKey);
            return type;
        }

        public static WolfVariant fromBukkit(Wolf.Variant bukkit) {
            return WolfVariant.valueOf(bukkit.key().value().toUpperCase());
        }
    }

    public enum VillagerProfession {
        NONE("none"),
        ARMORER("armorer"),
        BUTCHER("butcher"),
        CARTOGRAPHER("cartographer"),
        CLERIC("cleric"),
        FARMER("farmer"),
        FISHERMAN("fisherman"),
        FLETCHER("fletcher"),
        VLEATHERWORKER("leatherworker"),
        LIBRARIAN("librarian"),
        MASON("mason"),
        NITWIT("nitwit"),
        SHEPHERD("shepherd"),
        TOOLSMITH("toolsmith"),
        WEAPONSMITH("weaponsmith");

        private final String key;

        VillagerProfession(String key) {
            this.key = key;
        }

        public Villager.Profession toBukkit(){
            NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
            Villager.Profession type = RegistryAccess.registryAccess().getRegistry(RegistryKey.VILLAGER_PROFESSION).get(namespacedKey);
            Preconditions.checkNotNull(type, "No villager profession found for %s. This is a bug.", namespacedKey);
            return type;
        }

        public static VillagerProfession fromBukkit(Villager.Profession bukkit) {
            return VillagerProfession.valueOf(bukkit.key().value().toUpperCase());
        }
    }

    public enum VillagerType {
        DESERT("desert"),
        JUNGLE("jungle"),
        PLAINS("plains"),
        SAVANNA("savanna"),
        SNOW("snow"),
        SWAMP("swamp"),
        TAIGA("taiga");

        private final String key;

        VillagerType(String key) {
            this.key = key;
        }

        public Villager.Type toBukkit(){
            NamespacedKey namespacedKey = NamespacedKey.minecraft(key);
            Villager.Type type = RegistryAccess.registryAccess().getRegistry(RegistryKey.VILLAGER_TYPE).get(namespacedKey);
            Preconditions.checkNotNull(type, "No villager type found for %s. This is a bug.", namespacedKey);
            return type;
        }

        public static VillagerType fromBukkit(Villager.Type bukkit) {
            return VillagerType.valueOf(bukkit.key().value().toUpperCase());
        }
    }

}