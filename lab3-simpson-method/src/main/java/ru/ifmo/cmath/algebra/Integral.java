package ru.ifmo.cmath.algebra;

public class Integral {
    private final IFunction function;
    private double lower;
    private double upper;

    public Integral(IFunction function) {
        this.function = function;
    }

    public Integral from(double lowerBound) {
        this.lower = lowerBound;
        return this;
    }

    public Integral to(double upperBound) {
        this.upper = upperBound;
        return this;
    }

    public IFunction getFunction() {
        return function;
    }

    public double getLower() {
        return lower;
    }

    public double getUpper() {
        return upper;
    }
}
