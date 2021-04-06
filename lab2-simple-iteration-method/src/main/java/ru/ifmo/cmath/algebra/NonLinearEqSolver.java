package ru.ifmo.cmath.algebra;

public class NonLinearEqSolver {
    public static final double BASE_ACCURACY = 1e-7;
    public static final int LIMIT_ITERATIONS = 100;

    private double ACCURACY = BASE_ACCURACY;

    /**
     * Решает нелинейное уравнение методом касательных.
     */
    public Object[] tangentsMethod(IFunction fun, double a, double b) {
        double c = 0, funcOnC = 0;
        int iterations = 0;

        if (fun.solveForX(a) * fun.solveForX(b) > 0)
            throw new RuntimeException("Not possible to calculate root in this interval");

        c = (fun.deriveForX(a) * fun.secondDeriveForX(a) > 0) ? a : b;
        do {
            funcOnC = fun.solveForX(c);
		    c = c - funcOnC / fun.deriveForX(c);
            iterations++;
            if(iterations == LIMIT_ITERATIONS) break;
        }
        while (Math.abs(funcOnC) >= ACCURACY);

        if (iterations == LIMIT_ITERATIONS)
            throw new RuntimeException("Specified accuracy is not achieved under limited iterations");

        return new Object[] { c, iterations };
    }


    /**
     * Решает нелинейное уравнение методом хорд.
     */
    public Object[] secantMethod(IFunction fun, double a, double b) {
        double c = 0, left = a, right = b, funcOnC = 0;
        double funcOnLeft = fun.solveForX(left);
        double funcOnRight = fun.solveForX(right);
        int iterations = 0;

        if (funcOnLeft * funcOnRight > 0)
            throw new RuntimeException("Not possible to calculate root in this interval");

        do {
            funcOnLeft = fun.solveForX(left);
            funcOnRight = fun.solveForX(right);
            if(funcOnLeft == funcOnRight)
                throw new RuntimeException("Solution cannot be found as the values of a and b are same");

            c = (left * funcOnRight - right * funcOnLeft) / (funcOnRight - funcOnLeft);
            left = right;
            right = c;
            funcOnC = fun.solveForX(c);
            iterations++;
            if(iterations == LIMIT_ITERATIONS) break;
        } while(Math.abs(funcOnC) >= ACCURACY);

        if (iterations == LIMIT_ITERATIONS)
            throw new RuntimeException("Specified accuracy is not achieved under limited iterations");

        return new Object[] { c, iterations };
    }


    public Object[][] iterationsMethod(ISystem system) {
        double prevX = 0, prevY = 0, x, y, delta1, delta2;
        int iterations = 0;
        do {
            x = system.clearedForX(prevY);
            y = system.clearedForY(prevX);
            delta1 = system.solveFunction1(x, y);
            delta2 = system.solveFunction2(x, y);
            prevX = x;
            prevY = y;
            iterations++;
            if(iterations == LIMIT_ITERATIONS) break;
        }while(Math.abs(delta1) > ACCURACY && Math.abs(delta2) > ACCURACY);

        if (Double.isNaN(x) || Double.isNaN(y) || iterations == LIMIT_ITERATIONS) {
            throw new RuntimeException("Couldn't achieved specified accuracy");
        }

        return new Object[][] {{ x, x - prevX }, { y, y - prevY }, { iterations }};
    }


    public double getAccuracy() {
        return ACCURACY;
    }
    public void setAccuracy(double ACCURACY) {
        this.ACCURACY = ACCURACY;
    }
}
