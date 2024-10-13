package lol.aabss.skuishy.elements.entities;

import ch.njol.skript.registrations.Classes;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import lol.aabss.skuishy.other.EnumWrapper;
import lol.aabss.skuishy.other.RegistryClassInfo;
import org.bukkit.Art;
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

        if (Classes.getClassInfoNoError("frogvariant") == null) {
            Classes.registerClass(RegistryClassInfo.create(RegistryAccess.registryAccess().getRegistry(RegistryKey.FROG_VARIANT),
                            Frog.Variant.class, "frogvariant")
                    .user("frog ?variants?")
                    .name("Frog Variant")
                    .description("Represents a variant of a frog.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("horsecolor") == null) {
            Classes.registerClass(new EnumWrapper<>(Horse.Color.class).getClassInfo("horsecolor")
                    .user("horse ?colors?")
                    .name("Horse Color")
                    .description("Represents a color of a horse.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("horsestyle") == null) {
            Classes.registerClass(new EnumWrapper<>(Horse.Style.class).getClassInfo("horsestyle")
                    .user("horse ?styles?")
                    .name("Horse Style")
                    .description("Represents a style of a horse.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("mushroomcowvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(MushroomCow.Variant.class).getClassInfo("mushroomcowvariant")
                    .user("mushroom ?cow ?variants?")
                    .name("Mushroom Cow Variant")
                    .description("Represents a variant of a mushroom cow.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("parrotvariant") == null) {
            Classes.registerClass(new EnumWrapper<>(Parrot.Variant.class).getClassInfo("parrotvariant")
                    .user("parrot ?variants?")
                    .name("Parrot Variant")
                    .description("Represents a variant of a parrot.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("snifferstate") == null) {
            Classes.registerClass(new EnumWrapper<>(Sniffer.State.class).getClassInfo("snifferstate")
                    .user("sniffer ?states?")
                    .name("Sniffer States")
                    .description("Represents a state of a sniffer.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("tropicalfishpattern") == null) {
            Classes.registerClass(new EnumWrapper<>(TropicalFish.Pattern.class).getClassInfo("tropicalfishpattern")
                    .user("tropical ?fish ?patterns?")
                    .name("Tropical Fish Pattern")
                    .description("Represents a pattern of a tropical fish.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("villagerprofession") == null) {
            Classes.registerClass(RegistryClassInfo.create(RegistryAccess.registryAccess().getRegistry(RegistryKey.VILLAGER_PROFESSION),
                            Villager.Profession.class, "villagerprofession")
                    .user("villager ?professions?")
                    .name("Villager Profession")
                    .description("Represents a profession of a villager.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("villagertype") == null) {
            Classes.registerClass(RegistryClassInfo.create(RegistryAccess.registryAccess().getRegistry(RegistryKey.VILLAGER_TYPE),
                            Villager.Type.class, "villagertype")
                    .user("villager ?types?")
                    .name("Villager Type")
                    .description("Represents a type of a villager.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("art") == null) {
            Classes.registerClass(new EnumWrapper<>(Art.class).getClassInfo("art")
                    .user("arts?")
                    .name("Art")
                    .description("Represents a piece of art.")
                    .since("2.8"));
        }

        if (Classes.getClassInfoNoError("angerlevel") == null) {
            Classes.registerClass(new EnumWrapper<>(Warden.AngerLevel.class).getClassInfo("angerlevel")
                    .user("anger ?levels?")
                    .name("Anger Level")
                    .description("Represents an anger level.")
                    .since("2.8"));
        }
    }
}
