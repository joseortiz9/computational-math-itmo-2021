package ru.ifmo.cmath.algebra;

public class IntegralSolver {
    public static final double BASE_ACCURACY = 1e-7;
    public static final int LIMIT_ITERATIONS = 100;

    private double ACCURACY = BASE_ACCURACY;


    public IntegralAnswer bySimpsonsRule(Integral integral) {
        return null;
    }


    public double getAccuracy() {
        return ACCURACY;
    }
    public void setAccuracy(double ACCURACY) {
        this.ACCURACY = ACCURACY;
    }
}
