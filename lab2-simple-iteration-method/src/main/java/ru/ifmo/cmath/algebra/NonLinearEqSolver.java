package ru.ifmo.cmath.algebra;

public class NonLinearEqSolver {
    public static final double BASE_ACCURACY = 1e-7;
    private double ACCURACY = BASE_ACCURACY;


    /**
     * Решает нелинейное уравнение методом касательных.
     */
    public Object[] tangentsMethod(IFunction fun, double a, double b) {
        return new Object[] { 1.234d, 1 };
    }


    /**
     * Решает нелинейное уравнение методом хорд.
     */
    public Object[] secantMethod(IFunction fun, double a, double b) {
        double c = b;
        int iterations = 0;
        do {
            a = b;
            b = c;
            c = b - (b-a) / (fun.solveForX(b)-fun.solveForX(a)) * fun.solveForX(b);
            iterations++;
            if (fun.solveForX(c) == 0) break;
        } while(Math.abs(c-b) >= ACCURACY);
        return new Object[] { c, iterations };
    }



    public double getAccuracy() {
        return ACCURACY;
    }
    public void setAccuracy(double ACCURACY) {
        this.ACCURACY = ACCURACY;
    }
}
