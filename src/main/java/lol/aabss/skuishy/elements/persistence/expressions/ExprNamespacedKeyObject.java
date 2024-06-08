package lol.aabss.skuishy.elements.persistence.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.bukkitutil.EntityUtils;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.entity.EntityData;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.LiteralUtils;
import ch.njol.skript.util.slot.Slot;
import ch.njol.util.Kleenean;
import lol.aabss.skuishy.Skuishy;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Name("Persistence - Get NamespacedKey")
@Description({"Mirror of [SkBee](https://github.com/ShaneBeee/SkBee)'s ExprNamespacedKeyObject. For use when [SkBee](https://github.com/ShaneBeee/SkBee) is not installed.",
        "Gets the namespaced key of an object."})
@Examples({
        "set {_key} to namespacedkey from \"aabss:skuishy\""})
@Since("2.7")
public class ExprNamespacedKeyObject extends SimplePropertyExpression<Object, NamespacedKey> {

    static {
        if (!Skript.classExists("com.shanebeestudios.skbee.elements.other.expressions.ExprNamespacedKeyObject")) {
            Skript.registerExpression(ExprNamespacedKeyObject.class, NamespacedKey.class, ExpressionType.COMBINED,
                    "(mc:(minecraft|mc)|namespaced|resource)[ ](key|id[entifier]|location)[s] [(from|of)] %objects%");
        }
    }

    private boolean useMinecraftNamespace;

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, @NotNull Kleenean isDelayed, ParseResult parseResult) {
        this.useMinecraftNamespace = parseResult.hasTag("mc");
        Expression<Object> objects = LiteralUtils.defendExpression(exprs[0]);
        setExpr(objects);
        return LiteralUtils.canInitSafely(objects);
    }

    @SuppressWarnings("ConstantValue")
    @Override
    public @Nullable NamespacedKey convert(Object object) {
        if (object instanceof String string) {
            if (this.useMinecraftNamespace) {
                return getMCNamespacedKey(string);
            } else {
                return getNamespacedKey(string);
            }
        } else if (object instanceof Keyed keyed) {
            return keyed.getKey();
        } else if (object instanceof Block block) {
            return block.getBlockData().getMaterial().getKey();
        } else if (object instanceof BlockData blockData) {
            return blockData.getMaterial().getKey();
        } else if (object instanceof Entity entity) {
            return entity.getType().getKey();
        } else if (object instanceof EntityData<?> entityData) {
            EntityType entityType = EntityUtils.toBukkitEntityType(entityData);
            if (entityType != null) return entityType.getKey();
        } else if (object instanceof ItemType itemType) {
            return itemType.getMaterial().getKey();
        } else if (object instanceof ItemStack itemStack) {
            return itemStack.getType().getKey();
        } else if (object instanceof Slot slot) {
            ItemStack item = slot.getItem();
            if (item != null) return item.getType().getKey();
        }
        return null;
    }

    @Override
    public @NotNull Class<? extends NamespacedKey> getReturnType() {
        return NamespacedKey.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "namespaced key";
    }

    @Nullable
    public static NamespacedKey getMCNamespacedKey(@NotNull String key) {
        if (!key.contains(":")) key = "minecraft:" + key;
        return getNamespacedKey(key);
    }

    @Nullable
    public static NamespacedKey getNamespacedKey(@NotNull String key) {
        if (key.length() > 255) {
            Skript.error("An invalid key was provided, key must be less than 256 characters: "+key);
            return null;
        }
        key = key.toLowerCase();
        if (key.contains(" ")) {
            key = key.replace(" ", "_");
        }

        NamespacedKey namespacedKey = null;
        if (key.contains(":")) {
            namespacedKey = NamespacedKey.fromString(key);
        } else {
            try {
                namespacedKey = new NamespacedKey(Skuishy.instance, key);
            } catch (Exception exception) {
                Skript.error(exception.getMessage());

            }
        }
        if (namespacedKey == null)
            Skript.error("An invalid key was provided, that didn't follow [a-z0-9/._-:]. key: "+ key);
        return namespacedKey;
    }



}