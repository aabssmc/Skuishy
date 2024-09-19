package lol.aabss.skuishy.elements.entities;

import ch.njol.skript.registrations.Classes;
import lol.aabss.skuishy.other.EnumWrapper;
import lol.aabss.skuishy.other.KeyedToEnum;
import org.bukkit.entity.*;

public class Types {
    static {
        if (Classes.getClassInfoNoError("axolotlvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(Axolotl.Variant.class).getClassInfo("axolotlvariant")
                    .user("axolotl ?variants?")
                    .name("Axolotl Variant")
                    .description("Represents a axolotl variant.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("rabbitvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(Rabbit.Type.class).getClassInfo("rabbitvariant")
                    .user("rabbit ?variants?")
                    .name("Rabbit Variant")
                    .description("Represents a rabbit variant.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("llamavariant") == null) {
            Classes.registerClass(new EnumWrapper<>(Llama.Color.class).getClassInfo("llamavariant")
                    .user("llama ?variants?")
                    .name("Llama Variant")
                    .description("Represents a llama variant.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("foxvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(Fox.Type.class).getClassInfo("foxvariant")
                    .user("fox ?variants?")
                    .name("Fox Variant")
                    .description("Represents a fox variant.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("boatvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(Boat.Type.class).getClassInfo("boatvariant")
                    .user("boat ?variants?")
                    .name("Boat Variant")
                    .description("Represents a boat variant.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("boatstatus") == null) {
            Classes.registerClass(new EnumWrapper<>(Boat.Status.class).getClassInfo("boatstatus")
                    .user("boat ?status[es]")
                    .name("Boat Status")
                    .description("Represents a boat Status.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("dragonphase") == null) {
            Classes.registerClass(new EnumWrapper<>(EnderDragon.Phase.class).getClassInfo("dragonphase")
                    .user("[ender ?]dragon ?phase[s]")
                    .name("Dragon Phase")
                    .description("Represents the phase of a dragon.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("catvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(KeyedToEnum.CatVariant.class).getClassInfo("catvariant")
                    .user("cat ?variants?")
                    .name("Cat Variant")
                    .description("Represents a variant of a cat.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("frogvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(KeyedToEnum.FrogVariant.class).getClassInfo("frogvariant")
                    .user("frog ?variants?")
                    .name("Frog Variant")
                    .description("Represents a variant of a frog.")
                    .since("2.8"));
        }
    }
}
