package lol.aabss.skuishy.elements.entities;

import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.registrations.Classes;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Rabbit;

public class Types {
    static {
        Classes.registerClass(new EnumClassInfo<>(Axolotl.Variant.class, "axolotlvariant", "axolotlvariant")
                .user("axolotl ?variants?")
                .name("Axolotl Variant")
                .description("Represents a axolotl variant.")
                .since("2.8"));

        Classes.registerClass(new EnumClassInfo<>(Rabbit.Type.class, "rabbitvariant", "rabbitvariant")
                .user("rabbit ?variants?")
                .name("Rabbit Variant")
                .description("Represents a rabbit variant.")
                .since("2.8"));

        Classes.registerClass(new EnumClassInfo<>(Llama.Color.class, "llamavariant", "llamavariant")
                .user("llama ?variants?")
                .name("Llama Variant")
                .description("Represents a llama variant.")
                .since("2.8"));
    }
}
