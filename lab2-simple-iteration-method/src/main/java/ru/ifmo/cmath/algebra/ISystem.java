package ru.ifmo.cmath.algebra;

import java.util.ArrayList;
import java.util.function.Function;

public interface ISystem {
    ArrayList<Function<Double, Double>> getDraw();
    double clearedForX(double y);
    double clearedForY(double x);
    double solveFunction1(double x, double y);
    double solveFunction2(double x, double y);
}
