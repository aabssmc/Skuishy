package lol.aabss.skuishy.elements;

import ch.njol.skript.lang.function.Parameter;
import ch.njol.skript.lang.function.SimpleJavaFunction;
import ch.njol.skript.registrations.DefaultClasses;
import org.jetbrains.annotations.NotNull;

import static ch.njol.skript.lang.function.Functions.registerFunction;

@SuppressWarnings("NullableProblems")
public class Functions {

    static {
        Parameter<?>[] numbersParam = new Parameter[]{new Parameter<>("ns", DefaultClasses.NUMBER, false, null)};
        registerFunction(new SimpleJavaFunction<Number>("mean", numbersParam, DefaultClasses.NUMBER, true) {
            @Override
            public Number @NotNull [] executeSimple(Object[] [] params) {
                Object[] ns = params[0];
                double sum = 0.0;
                for (Object num : ns) {
                    sum += ((Number) num).doubleValue();
                }
                double mean = sum / ns.length;
                return new Number[]{mean};
            }
        }
                .description("Calculates the mean of a list of numbers.")
                .since("1.7"));
    }
}
