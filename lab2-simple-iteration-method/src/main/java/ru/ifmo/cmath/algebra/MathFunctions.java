package ru.ifmo.cmath.algebra;

import java.util.ArrayList;
import java.util.function.Function;

public class MathFunctions {
    private final ArrayList<IFunction> availableFunctions = new ArrayList<>();
    private final ArrayList<IFunction> availableSystems = new ArrayList<>();

    {
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "x^2 + x + 2 = 0";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.pow(x, 2) + x + 2;
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "x^3 - x - 11.0 = 0";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.pow(x,3) - x - 11.0;
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "3x^2 - 14x - 5 = 0";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> 3 * Math.pow(x, 2) - (14 * x) - 5;
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "e^x - 1 = 0";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.pow(Math.E, x) - 1;
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "x^3 - 17 = 0";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.pow(x, 3) -17;
            }
        });
    }

    public ArrayList<IFunction> getAvailableFunctions() {
        return availableFunctions;
    }
}


