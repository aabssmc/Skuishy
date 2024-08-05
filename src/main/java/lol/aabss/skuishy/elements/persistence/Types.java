package lol.aabss.skuishy.elements.persistence;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import lol.aabss.skuishy.Skuishy;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.io.StreamCorruptedException;

public class Types {
    static {
        if (Classes.getExactClassInfo(NamespacedKey.class) == null) {
            Classes.registerClass(new ClassInfo<>(NamespacedKey.class, "namespacedkey")
                    .user("namespacedkeys?")
                    .name("Persistence - NamespacedKey")
                    .description("Mirror of [SkBee](https://github.com/ShaneBeee/SkBee)'s namespaced key type. For use when [SkBee](https://github.com/ShaneBeee/SkBee) is not installed.",
                            "Represents a minecraft namespaced key. For use when SkBee is not installed.",
                            "which can identify built-in and user-defined objects without potential ambiguity or conflicts.",
                            "For more information see [**Resource Location**](https://minecraft.wiki/w/Resource_location) on McWiki.")
                    .since("2.7")
                    .serializer(new Serializer<>() {
                        @Override
                        public @NotNull Fields serialize(NamespacedKey namespacedKey) {
                            Fields fields = new Fields();
                            fields.putObject("key", namespacedKey.toString());
                            return fields;
                        }

                        @SuppressWarnings("NullableProblems")
                        @Override
                        public void deserialize(NamespacedKey o, Fields f) {
                        }

                        @SuppressWarnings("NullableProblems")
                        @Override
                        protected NamespacedKey deserialize(Fields fields) throws StreamCorruptedException {
                            String key = fields.getObject("key", String.class);
                            if (key == null) {
                                Skuishy.Logger.exception(StreamCorruptedException.class, "NamespacedKey string is null");
                            }
                            return NamespacedKey.fromString(key);
                        }

                        @Override
                        public boolean mustSyncDeserialization() {
                            return true;
                        }

                        @Override
                        protected boolean canBeInstantiated() {
                            return false;
                        }
                    }));
        }

        if (Classes.getExactClassInfo(PersistentDataContainer.class) == null){
            Classes.registerClass(new ClassInfo<>(PersistentDataContainer.class, "persistentdatacontainer")
                    .user("[persistent ?]data ?containers?")
                    .name("Persistence - Persistent Data Container")
                    .description("Represents a data container of something",
                            "Can be used as an alternative as variables")
                    .since("2.7")
                    .parser(new Parser<>() {
                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toString(PersistentDataContainer persistentDataContainer, int i) {
                            return "persistent data container";
                        }

                        @Override
                        public @NotNull String toVariableNameString(PersistentDataContainer persistentDataContainer) {
                            return "persistent data container";
                        }
                    })
            );
        }
    }
}
