package ru.ifmo.cmath.algebra;

import java.util.ArrayList;

public class MathFunctions {
    private final ArrayList<Function> availableFunctions = new ArrayList<>();

    {
        availableFunctions.add(new Function("sin(x)/x"));
        availableFunctions.add(new Function("sin(x) + cos(x)"));
        availableFunctions.add(new Function("log(x)"));
        availableFunctions.add(new Function("3x^2"));
        availableFunctions.add(new Function("x^3"));
    }

    public ArrayList<Function> getAvailableFunctions() {
        return availableFunctions;
    }
}


