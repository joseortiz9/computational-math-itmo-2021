package ru.ifmo.cmath.algebra;

import pl.joegreen.lambdaFromString.LambdaFactory;
import pl.joegreen.lambdaFromString.TypeReference;

import java.util.function.Function;

public class LagrangePolynomial {

    LambdaFactory lambdaFactory = LambdaFactory.get();

    private final StringBuilder generatedFunc;
    private final Double[] xData;
    private final Double[] yData;

    public LagrangePolynomial(Double[] xData, Double[] yData) {
        if (xData.length == 0 || yData.length == 0) {
            throw new IllegalArgumentException("Function values can not be empty!");
        }
        this.xData = xData;
        this.yData = yData;
        generatedFunc = new StringBuilder("x -> ");
    }

    public String lagrangeXStep(int i) {
        StringBuilder numerator = new StringBuilder();
        int counter = 0;
        double denominator = 1.0d;
        for (int j = 0; j < xData.length; j++) {
            if (i == j) continue;
            /* Create a numerator for lagrange x step */
            numerator.append(counter == 0 ? "" : "*").append("(x-(").append(xData[j]).append("))");
            /* Calculate a denominator value */
            denominator *= (xData[i] - xData[j]);
            counter++;
        }
        return numerator.append("/").append("(").append(denominator).append(")").toString();
    }

    public IFunction buildFunction() {
        for (int i = 0; i < yData.length; i++) {
            generatedFunc.append(i == 0 ? "" : "+").append("(").append(yData[i])
                    .append("*").append("(").append(lagrangeXStep(i)).append(")").append(")");
        }
        return () -> {
            try {
                return lambdaFactory.createLambda(generatedFunc.toString(), new TypeReference<Function<Double, Double>>(){});
            } catch (Exception e) {
                return null;
            }
        };
    }
}
