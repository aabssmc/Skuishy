package lol.aabss.skuishy.elements.general;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import io.papermc.paper.datapack.Datapack;
import org.bukkit.Statistic;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.potion.PotionType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.skriptlang.skript.lang.converter.Converter;
import org.skriptlang.skript.lang.converter.Converters;

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
            Classes.registerClass(new EnumClassInfo<>(SpawnCategory.class, "spawncategory", "spawncategory")
                    .user("spawn ?categor(y|ies)")
                    .name("spawn category")
                    .description("Represents a spawn category.")
                    .since("1.7.5")
            );
        }

        if (Classes.getClassInfoNoError("potionitemtype") == null) {
            Classes.registerClass(new EnumClassInfo<>(PotionType.class, "potionitemtype", "potionitemtype")
                    .user("potion[ ]item[ ]type")
                    .name("Potion Item Type")
                    .description("Represents a potion item type that matches each potion state that can be obtained from the Creative mode inventory.")
                    .since("2.1")
            );
        }
        if (Classes.getClassInfoNoError("statistic") == null) {
            Classes.registerClass(new EnumClassInfo<>(Statistic.class, "statistic", "statistic")
                    .user("statistics?")
                    .name("Statistic")
                    .description("Represents a statistic of a player.")
                    .since("2.8")
            );
        }
        if (Classes.getClassInfoNoError("skipreason") == null) {
            Classes.registerClass(new EnumClassInfo<>(TimeSkipEvent.SkipReason.class, "skipreason", "skipreason")
                    .user("skipreasons?")
                    .name("Time Skip Reason")
                    .description("Represents a reason for a time skip.")
                    .since("2.8")
            );
        }
        if (Classes.getClassInfoNoError("eulerangle") == null) {
            Classes.registerClass(new ClassInfo<>(EulerAngle.class, "eulerangle")
                    .user("euler ?angles?")
                    .name("Euler Angle")
                    .description("Represents a euler angle.")
                    .since("2.8")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(EulerAngle angle) {
                            return angle.getX()+", "+angle.getY()+", "+angle.getZ();
                        }

                        @Override
                        public @NotNull String toString(EulerAngle angle, int flags) {
                            return toVariableNameString(angle);
                        }
                    })
            );
            Converters.registerConverter(EulerAngle.class, Vector.class, new Converter<>() {
                @Override
                public @NotNull Vector convert(EulerAngle from) {
                    return new Vector(from.getX(), from.getY(), from.getZ());
                }
            });
            Converters.registerConverter(Vector.class, EulerAngle.class, new Converter<>() {
                @Override
                public @NotNull EulerAngle convert(Vector from) {
                    return new EulerAngle(from.getX(), from.getY(), from.getZ());
                }
            });
        }
    }
}
