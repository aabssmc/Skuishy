package lol.aabss.skuishy.elements.decentholograms;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import eu.decentsoftware.holograms.api.actions.ClickType;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.jetbrains.annotations.NotNull;

public class Types {
    static{
        if (Classes.getClassInfoNoError("hologram") == null) {
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
        }
        if (Classes.getClassInfoNoError("hologramclicktype") == null) {
            Classes.registerClass(new EnumClassInfo<>(ClickType.class, "hologramclicktype", "hologramclicktype")
                    .user("hologram ?click ?types?")
                    .name("hologramclicktype")
                    .description("Represents a decent holograms click type.")
                    .since("2.0")
            );
        }
        if (Classes.getClassInfoNoError("hologrampage") == null) {
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
                            return "page " + holo.getIndex() + " of " + holo.getParent().getName();
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
