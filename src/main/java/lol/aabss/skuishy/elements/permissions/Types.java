package lol.aabss.skuishy.elements.permissions;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import lol.aabss.skuishy.other.EnumWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionDefault;
import org.jetbrains.annotations.NotNull;

public class Types {
    static {
        if (Classes.getClassInfoNoError("bukkitpermission") == null) {
            Classes.registerClass(new ClassInfo<>(Permission.class, "bukkitpermission")
                    .user("bukkit ?permissions?")
                    .name("Permissions - Bukkit Permission")
                    .description("Represents a bukkit permission.")
                    .since("2.1")
                    .parser(new Parser<>() {

                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(Permission perm) {
                            return perm.getName();
                        }

                        @Override
                        public @NotNull String toString(Permission perm, int flags) {
                            return toVariableNameString(perm);
                        }
                    })
            );
        }
        if (Classes.getClassInfoNoError("permissionattachment") == null) {
            Classes.registerClass(new ClassInfo<>(PermissionAttachment.class, "permissionattachment")
                    .user("permission ?attachments?")
                    .name("Permissions - Permission Attachment")
                    .description("Represents a permission attachment.")
                    .since("2.1")
                    .parser(new Parser<>() {


                        @Override
                        public boolean canParse(@NotNull ParseContext context) {
                            return false;
                        }

                        @Override
                        public @NotNull String toVariableNameString(PermissionAttachment perm) {
                            return "Permission: " + perm.getPermissions() + " Player: " + ((Entity) perm.getPermissible()).getName();
                        }

                        @Override
                        public @NotNull String toString(PermissionAttachment perm, int flags) {
                            return toVariableNameString(perm);
                        }
                    })
            );
        }
        if (Classes.getClassInfoNoError("permissiondefault") == null) {
            Classes.registerClass(new EnumWrapper<>(PermissionDefault.class).getClassInfo("permissiondefault")
                    .user("permission ?defaults?")
                    .name("Permissions - Permission Default")
                    .description("Represents the possible default values for permissions.")
                    .since("2.1"));
        }
    }
}
