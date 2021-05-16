package ru.ifmo.cmath.algebra;

import java.util.ArrayList;
import java.util.function.Function;

public class MathFunctions {
    private final ArrayList<IFunction> availableFunctions = new ArrayList<>();

    {
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = sin(x)";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.sin(x);
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = sin(x) + cos(x)";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.sin(x) + Math.cos(x+12);
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = 3x^3 - 2x^2 +2";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> 3 * Math.pow(x, 3) - 2 * Math.pow(x, 2) + 2;
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = 1/((x-1)(x-3)(x-5))";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> 1/((x-1) * (x-3) * (x-5));
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = log(|x|)";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.log(Math.abs(x));
            }
        });
    }

    public ArrayList<IFunction> getAvailableFunctions() {
        return availableFunctions;
    }
}


