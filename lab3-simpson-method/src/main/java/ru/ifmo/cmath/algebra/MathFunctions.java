package ru.ifmo.cmath.algebra;

import java.util.ArrayList;
import java.util.function.Function;

public class MathFunctions {
    private final ArrayList<IFunction> availableFunctions = new ArrayList<>();

    {
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = x^2";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.pow(x, 2);
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = sqrt(x)";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.sqrt(x);
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = 1/x";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> 1/x;
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "y = sin(x)/x";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> Math.sin(x)/x;
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


