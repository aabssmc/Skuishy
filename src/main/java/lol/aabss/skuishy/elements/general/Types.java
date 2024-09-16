package lol.aabss.skuishy.elements.general;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import io.papermc.paper.datapack.Datapack;
import lol.aabss.skuishy.other.EnumWrapper;
import org.bukkit.Statistic;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

public class Types {
    static{
        if (Classes.getClassInfoNoError("datapack") == null) {
            Classes.registerClass(new ClassInfo<>(Datapack.class, "datapack")
                    .user("datapacks?")
                    .name("datapack")
                    .description("Represents a datapack.")
                    .since("1.7")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Datapack data) {
                            return data.getName().toLowerCase().replaceAll("_", " ");
                        }

                        @Override
                        public @NotNull String toString(Datapack data, int flags) {
                            return toVariableNameString(data);
                        }
                    })
            );
        }
        if (Classes.getClassInfoNoError("spawncategory") == null) {
            Classes.registerClass(new EnumWrapper<>(SpawnCategory.class).getClassInfo("spawncategory")
                    .user("spawn ?categor(y|ies)")
                    .name("spawn category")
                    .description("Represents a spawn category.")
                    .since("1.7.5")
            );
        }

        if (Classes.getClassInfoNoError("potionitemtype") == null) {
            Classes.registerClass(new EnumWrapper<>(PotionType.class).getClassInfo("potionitemtype")
                    .user("potion[ ]item[ ]type")
                    .name("Potion Item Type")
                    .description("Represents a potion item type that matches each potion state that can be obtained from the Creative mode inventory.")
                    .since("2.1")
            );
        }
        if (Classes.getClassInfoNoError("statistic") == null) {
            Classes.registerClass(new EnumWrapper<>(Statistic.class).getClassInfo("statistic")
                    .user("statistics?")
                    .name("Statistic")
                    .description("Represents a statistic of a player.")
                    .since("2.8")
            );
        }
        if (Classes.getClassInfoNoError("skipreason") == null) {
            Classes.registerClass(new EnumWrapper<>(TimeSkipEvent.SkipReason.class).getClassInfo("skipreason")
                    .user("skipreasons?")
                    .name("Time Skip Reason")
                    .description("Represents a reason for a time skip.")
                    .since("2.8")
            );
        }
    }
}
