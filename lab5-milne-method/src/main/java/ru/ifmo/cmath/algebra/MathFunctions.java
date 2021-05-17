package ru.ifmo.cmath.algebra;

import java.util.ArrayList;

public class MathFunctions {
    private final ArrayList<Function> availableFunctions = new ArrayList<>();

    {
        availableFunctions.add(new Function("sin(x)", "x"));
        availableFunctions.add(new Function("log(xy)", "x", "y"));
        availableFunctions.add(new Function("sin(x) + cos(y)", "x", "y"));
        availableFunctions.add(new Function("x^2y", "x", "y"));
        availableFunctions.add(new Function("log(x)", "x"));
        availableFunctions.add(new Function("3x^2", "x", "y"));
    }

    public ArrayList<Function> getAvailableFunctions() {
        return availableFunctions;
    }
}


