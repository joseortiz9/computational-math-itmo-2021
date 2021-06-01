package ru.ifmo.cmath.algebra;

import java.util.ArrayList;

public class MathFunctions {
    private final ArrayList<Function> availableFunctions = new ArrayList<>();

    {
        availableFunctions.add(new Function("sin(x)/x"));
    }

    public ArrayList<Function> getAvailableFunctions() {
        return availableFunctions;
    }
}


