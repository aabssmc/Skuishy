package lol.aabss.skuishy.decentholograms;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.jetbrains.annotations.NotNull;

public class Types {
    static{
        Classes.registerClass(new ClassInfo<>(Hologram.class, "hologram")
                .user("holograms?")
                .name("hologram")
                .description("Represents a decent hologram hologram.")
                .since("1.6")
                .parser(new Parser<Hologram>() {

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
}
