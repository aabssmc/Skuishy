package lol.aabss.skuishy.elements.entities;

import ch.njol.skript.classes.EnumClassInfo;
import ch.njol.skript.registrations.Classes;
import org.bukkit.entity.Axolotl;

public class Types {
    static {
        Classes.registerClass(new EnumClassInfo<>(Axolotl.Variant.class, "axolotlvariant", "axolotlvariant")
                .user("axolotl ?variants?")
                .name("Axolotl Variant")
                .description("Represents a axolotl variant.")
                .since("2.8"));
    }
}
