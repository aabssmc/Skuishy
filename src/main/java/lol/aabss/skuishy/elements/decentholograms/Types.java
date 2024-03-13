package lol.aabss.skuishy.elements.decentholograms;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import eu.decentsoftware.holograms.api.actions.ClickType;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.bukkit.Bukkit;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class Types {
    static{
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("DecentHolograms")){
            Classes.registerClass(new ClassInfo<>(Hologram.class, "hologram")
                    .user("holograms?")
                    .name("hologram")
                    .description("Represents a decent hologram hologram.")
                    .since("1.6")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Hologram holo) {
                            return holo.getName().toLowerCase().replaceAll("_", " ");
                        }

                        @Override
                        public @NotNull String toString(Hologram holo, int flags) {
                            return toVariableNameString(holo);
                        }
                    })
            );
            EnumUtils<ClickType> clicktypes = new EnumUtils<>(ClickType.class, "hologramclicktype");
            Classes.registerClass(new ClassInfo<>(ClickType.class, "hologramclicktype")
                    .user("hologram ?click ?types?")
                    .name("hologramclicktype")
                    .description("Represents a decent holograms click type.")
                    .since("2.0")
                    .parser(new Parser<>() {

                        @Override
                        @Nullable
                        public ClickType parse(@NotNull String input, @NotNull ParseContext context) {
                            return clicktypes.parse(input);
                        }

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return true;
                        }

                        @Override
                        public @NotNull String toVariableNameString(ClickType holo) {
                            return holo.name().toLowerCase().replaceAll("_", " ");
                        }

                        @Override
                        public @NotNull String toString(ClickType holo, int flags) {
                            return toVariableNameString(holo);
                        }
                    })
            );
            Classes.registerClass(new ClassInfo<>(HologramPage.class, "hologrampage")
                    .user("hologram ?pages?")
                    .name("DecentHolograms - Hologram Page")
                    .description("Represents a decent holograms page.")
                    .since("2.5")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(HologramPage holo) {
                            return "page "+holo.getIndex()+" of "+holo.getParent().getName();
                        }

                        @Override
                        public @NotNull String toString(HologramPage holo, int flags) {
                            return toVariableNameString(holo);
                        }
                    })
            );
        }
    }
}
