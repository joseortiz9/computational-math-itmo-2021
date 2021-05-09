package ru.ifmo.cmath.algebra;

import java.util.ArrayList;
import java.util.function.Function;

public class MathFunctions {
    private final ArrayList<IFunction> availableFunctions = new ArrayList<>();
    private final ArrayList<ISystem> availableSystems = new ArrayList<>();

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
            @Override
            public int getAmountOfRoots() {
                return 2;
            }
        });
        availableFunctions.add(new IFunction() {
            @Override
            public String toString() {
                return "2x^2 + 5x - 12 = 0";
            }
            @Override
            public Function<Double, Double> getFunction() {
                return x -> (2 * Math.pow(x,2)) + (5 * x) - 12;
            }
            @Override
            public int getAmountOfRoots() {
                return 2;
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
            @Override
            public int getAmountOfRoots() {
                return 2;
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
            @Override
            public int getAmountOfRoots() {
                return 1;
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
            @Override
            public int getAmountOfRoots() {
                return 3;
            }
        });

        availableSystems.add(new ISystem() {
            @Override
            public ArrayList<Function<Double, Double>> getDraw() {
                ArrayList<Function<Double, Double>> ar = new ArrayList<>();
                ar.add(x -> (1-2*x)/3);
                ar.add(x -> (x>0 && x<Math.pow(3, 0.5)) ? -Math.abs(Math.pow(3-Math.pow(x,2), 0.5)) : 0);
                return ar;
            }
            @Override
            public String toString() {
                return "[x^2 + y^2 = 3 ; 2x + 3y = 1]";
            }
            @Override
            public double clearedForX(double y) {
                return Math.pow(3-Math.pow(y, 2), 0.5);
            }
            @Override
            public double clearedForY(double x) {
                return (1-2*x)/3;
            }
            @Override
            public double solveFunction1(double x, double y) {
                return Math.pow(x,2)+Math.pow(y,2)-3;
            }
            @Override
            public double solveFunction2(double x, double y) {
                return 2*x+3*y-1;
            }
        });

        availableSystems.add(new ISystem() {
            @Override
            public ArrayList<Function<Double, Double>> getDraw() {
                ArrayList<Function<Double, Double>> ar = new ArrayList<>();
                ar.add(x -> 3 - Math.pow(x, 2));
                ar.add(x -> x+1);
                return ar;
            }
            @Override
            public String toString() {
                return "[3 - x^2 = y ; x + 1 = y]";
            }
            @Override
            public double clearedForX(double y) {
                return Math.sqrt(3-y);
            }
            @Override
            public double clearedForY(double x) {
                return x+1;
            }
            @Override
            public double solveFunction1(double x, double y) {
                return 3 - Math.pow(x,2) - y;
            }
            @Override
            public double solveFunction2(double x, double y) {
                return x+1-y;
            }
        });
    }

    public ArrayList<IFunction> getAvailableFunctions() {
        return availableFunctions;
    }
    public ArrayList<ISystem> getAvailableSystems() {
        return availableSystems;
    }
}


