package lol.aabss.skuishy.elements.general.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.btk5h.skriptmirror.JavaType;
import lol.aabss.skuishy.Skuishy;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Name("Other - From String")
@Description("Gets a enum by it's string using a classinfo or if skript-reflect is installed, a javatype.")
@Examples({
        "set {_classinfo} to classinfo biome from string \"the void\"",
        "set {_classinfos::*} to all classinfo biomes",
        "",
        "import:",
        "\torg.bukkit.Sound",
        "set {_javatype} to javatype Sound from string \"ambient cave\"",
        "set {_javatype} to all javatype Sounds"
})
@Since("2.8")
public class ExprFromString extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprFromString.class, Object.class, ExpressionType.COMBINED,
                "[skuishy] classinfo %*classinfo% from string %string%",
                Skuishy.skript_reflect_supported ? "[skuishy] [java]type %*javatype% from string %string%" : "",
                "[skuishy] all classinfo %*classinfo%[s]",
                Skuishy.skript_reflect_supported ? "[skuishy] all [java]type %*javatype%[s]" : ""
        );
    }

    private Literal<ClassInfo<Object>> classInfo;
    private Literal<JavaType> javaType;
    private Expression<String> string;

    @Override
    protected Object @NotNull [] get(@NotNull Event e) {
        if (classInfo == null && javaType == null) {
            return new Object[]{};
        }
        Class<?> clazz;
        if (classInfo != null) {
            clazz = classInfo.getSingle().getC();
        } else {
            JavaType type = javaType.getSingle();
            if (type == null) {
                return new Object[]{};
            }
            clazz = type.getJavaClass();
        }
        if (!clazz.isEnum()) {
            return new Object[]{};
        }
        if (string != null) {
            String string = this.string.getSingle(e);
            if (string == null) {
                return new Object[]{};
            }
            List<Enum<?>> enums = new ArrayList<>();
            for (Object enumConstant : clazz.getEnumConstants()) {
                if (((Enum<?>) enumConstant).name().toLowerCase().equals(string.toLowerCase().replaceAll(" ", "_"))) {
                    return new Object[]{enumConstant};
                }
            }
        } else {
            return clazz.getEnumConstants();
        }
        return new Object[]{};
    }

    @Override
    public boolean isSingle() {
        return string != null;
    }

    @Override
    public @NotNull Class<?> getReturnType() {
        if (classInfo == null && javaType == null) {
            return Object.class;
        }
        return classInfo != null ? classInfo.getSingle().getC() : javaType.getSingle().getJavaClass();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return (classInfo != null ? "classinfo" : "javatype") + " from string";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        if (matchedPattern == 0 || matchedPattern == 2) {
            classInfo = (Literal<ClassInfo<Object>>) exprs[0];
        } else {
            javaType = (Literal<JavaType>) exprs[0];
        }
        if (matchedPattern == 0 || matchedPattern == 1) {
            string = (Expression<String>) exprs[1];
        }
        return true;
    }

    public enum TestEnum {
        EXAMPLE1,
        EXAMPLE2,
        EXAMPLE3
    }
}
