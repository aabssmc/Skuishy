package lol.aabss.skuishy.elements.general;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import io.papermc.paper.datapack.Datapack;
import lol.aabss.skuishy.other.FakeSign;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.potion.PotionType;
import org.eclipse.jdt.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

public class Types {
    static{
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
        EnumUtils<SpawnCategory> categorys = new EnumUtils<>(SpawnCategory.class, "spawncategory");
        Classes.registerClass(new ClassInfo<>(SpawnCategory.class, "spawncategory")
                .user("spawn ?categor(y|ies)")
                .name("spawn category")
                .description("Represents a spawn category.")
                .since("1.7.5")
                .parser(new Parser<>() {

                    @Override
                    @Nullable
                    public SpawnCategory parse(@NotNull String input, @NotNull ParseContext context) {
                        return categorys.parse(input);
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(SpawnCategory category) {
                        return category.name().replaceAll("_", " ").toLowerCase();
                    }

                    @Override
                    public @NotNull String toString(SpawnCategory category, int flags) {
                        return toVariableNameString(category);
                    }
                })
        );

        EnumUtils<PotionType> potiontypes = new EnumUtils<>(PotionType.class, "potionitemtype");
        Classes.registerClass(new ClassInfo<>(PotionType.class, "potionitemtype")
                .user("potion[ ]item[ ]type")
                .name("Potion Item Type")
                .description("Represents a potion item type that matches each potion state that can be obtained from the Creative mode inventory.")
                .since("2.1")
                .parser(new Parser<>() {

                    @Override
                    @Nullable
                    public PotionType parse(@NotNull String input, @NotNull ParseContext context) {
                        return potiontypes.parse(input);
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public @NotNull String toVariableNameString(PotionType type) {
                        return type.name().replaceAll("_", " ").toLowerCase();
                    }

                    @Override
                    public @NotNull String toString(PotionType type, int flags) {
                        return toVariableNameString(type);
                    }
                })
        );
        Classes.registerClass(new ClassInfo<>(FakeSign.class, "fakesign")
                .user("fakesigns?")
                .name("Fake Sign")
                .description("Represents a fake sign.")
                .since("2.5")
                .parser(new Parser<>() {

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }

                    @Override
                    public @NotNull String toVariableNameString(FakeSign sign) {
                        return "fakesign";
                    }

                    @Override
                    public @NotNull String toString(FakeSign sign, int flags) {
                        return toVariableNameString(sign);
                    }
                })
        );
    }
}
