package ru.ifmo.cmath.algebra;

public class IntegralSolver {
    public static final double BASE_ACCURACY = 1e-7;
    private static final int LIMIT = 1_000_000_000;
    private double ACCURACY = BASE_ACCURACY;

    public double solveStepBySimpsonsRule(Integral integral, int partitions) {
        double step = 1d * (integral.getUpper() - integral.getLower()) / partitions;
        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < partitions; i++) {
            double functionValue = integral.getFunction().solveForX(integral.getLower() + i * step);
            if (i % 2 == 0) {
                sum1 = sum1 + functionValue;
            } else {
                sum2 = sum2 + functionValue;
            }
        }
        return step / 3 *
                (integral.getFunction().solveForX(integral.getLower())
                        + integral.getFunction().solveForX(integral.getUpper())
                        + 2 * sum1 + 4 * sum2);
    }

    public IntegralAnswer bySimpsonsRule(Integral integral) {
        int partitions = 10;
        double lastAccuracy = 0d, lastNormalValue = 0d;
        double actualAccuracy = Double.MAX_VALUE;
        double oldValue = solveStepBySimpsonsRule(integral, partitions);
        while (actualAccuracy > ACCURACY) {
            partitions = partitions * 2;
            double actualValue = solveStepBySimpsonsRule(integral, partitions);
            actualAccuracy = rungeMethod(actualValue, oldValue);
            oldValue = actualValue;

            if (!Double.isNaN(oldValue) && !Double.isInfinite(oldValue)) {
                lastNormalValue = oldValue;
            }
            if (partitions > LIMIT || partitions < 0) {
                throw new IllegalArgumentException("Partition overflow");
            }
            lastAccuracy = actualAccuracy;
        }

        return new IntegralAnswer(oldValue, lastAccuracy, partitions, lastNormalValue);
    }

    public double rungeMethod(double actualValue, double oldValue) {
        return Math.abs(actualValue - oldValue) / 15;
    }


    public double getAccuracy() {
        return ACCURACY;
    }
    public void setAccuracy(double ACCURACY) {
        this.ACCURACY = ACCURACY;
    }
}
