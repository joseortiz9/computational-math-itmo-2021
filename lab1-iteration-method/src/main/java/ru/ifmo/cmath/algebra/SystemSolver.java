package ru.ifmo.cmath.algebra;

import java.util.Arrays;

public class SystemSolver {

    private final static int MAX_ITERATIONS = 100;

    public void solveWithJacoby(Jacobi jacobi, double accuracy) {
        int iterations = 0;
        int n = jacobi.getSize();
        double[] actual = new double[n]; // Approximations
        double[] prev = new double[n]; // Previous
        Arrays.fill(actual, 0);
        Arrays.fill(prev, 0);
        do {
            prev = actual.clone();

            for (int i = 0; i < n; i++) {
                double sum = jacobi.getFreeMember(i);

                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum -= jacobi.get(i,j) * prev[j];

                actual[i] = 1/jacobi.get(i,i) * sum;
            }
            iterations++;
        } while (getMaxAbsValue(subtractFromVector(actual, prev)) > accuracy && iterations != MAX_ITERATIONS);

        jacobi.setIterations(iterations);
        jacobi.setRootsSolution(actual);
        jacobi.setErrorMargins(subtractFromVector(actual, prev));
    }


    public double[] subtractFromVector(double[] v1, double[] v2) {
        double[] r = new double[v1.length];
        for (int i = 0; i < r.length; i++)
            r[i] = v1[i] - v2[i];
        return r;
    }

    public double getMaxAbsValue(double[] v1) {
        return Arrays.stream(v1).map(Math::abs).max().getAsDouble();
    }
}
