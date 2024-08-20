package lol.aabss.skuishy.elements.skins;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.util.EnumUtils;
import lol.aabss.skuishy.other.Blueprint;
import lol.aabss.skuishy.other.mineskin.Variant;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class Types {

    static {
        if (Classes.getClassInfoNoError("blueprint") == null) {
            Classes.registerClass(new ClassInfo<>(Blueprint.class, "blueprint")
                    .user("blueprints?")
                    .name("Blueprint - Blueprint")
                    .description("Represents a blueprint.")
                    .since("2.6")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Blueprint blueprint) {
                            return "blueprint";
                        }

                        @Override
                        public @NotNull String toString(Blueprint blueprint, int flags) {
                            return toVariableNameString(blueprint);
                        }
                    })
            );
        }
        if (Classes.getClassInfoNoError("skinmodel") == null) {
            EnumUtils<Variant> skinmodel = new EnumUtils<>(Variant.class, "skinmodel");
            Classes.registerClass(new ClassInfo<>(Variant.class, "skinmodel")
                    .user("skin ?models?")
                    .name("Skin - Skin Model")
                    .description("Represents a skin model.")
                    .since("2.6")
                    .parser(new Parser<>() {

                        @Override
                        public @Nullable Variant parse(@NotNull String s, @NotNull ParseContext context) {
                            return skinmodel.parse(s);
                        }

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return true;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Variant variant) {
                            return variant.name().toLowerCase();
                        }

                        @Override
                        public @NotNull String toString(Variant variant, int flags) {
                            return toVariableNameString(variant);
                        }
                    })
            );
        }
        if (Classes.getClassInfoNoError("blueprintpart") == null) {
            Classes.registerClass(new EnumClassInfo<>(Blueprint.Part.class, "blueprintpart", "blueprintpart")
                            .user("blueprint ?parts?")
                            .name("Skin - Blueprint Part")
                            .description("Represents a part of a blueprint.")
                            .since("2.8"));
        }
    }

}
