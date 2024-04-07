package lol.aabss.skuishy.elements.plugins;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Types {
    static {
        if (Classes.getClassInfoNoError("plugin") == null) {
            Classes.registerClass(new ClassInfo<>(Plugin.class, "plugin")
                    .user("plugins?")
                    .name("Plugin - Plugin")
                    .description("Represents a plugin.")
                    .since("2.3")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Plugin plugin) {
                            return plugin.getName();
                        }

                        @Override
                        public @NotNull String toString(Plugin plugin, int flags) {
                            return toVariableNameString(plugin);
                        }
                    })
            );
        }
    }
}
