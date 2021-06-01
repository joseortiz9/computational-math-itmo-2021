package ru.ifmo.cmath.algebra;

import ru.ifmo.cmath.utils.Point;

import java.util.List;

public class LagrangePolynomial {
    private final StringBuilder lagrangianPolynomial;
    private List<Point> axisData;

    public LagrangePolynomial() {
        this.lagrangianPolynomial = new StringBuilder();
    }

    public LagrangePolynomial setAxisData(List<Point> axisData) {
        if (axisData.size() < 2) {
            throw new IllegalArgumentException("lagrangian polynomial: points can not be less than 2");
        }
        this.axisData = axisData;
        return this;
    }

    public Function build() {
        if (axisData.isEmpty()) {
            throw new IllegalArgumentException("lagrangian polynomial: experimental data is empty!");
        }
        for (int i = 0; i < axisData.size(); i++) {
            if (axisData.get(i).getY().isNaN() || axisData.get(i).getY().isInfinite()) {
                throw new IllegalArgumentException("lagrangian polynomial: function undefined at x=" + axisData.get(i).getX());
            }
            /* Create a Lagrangian Polynomial */
            lagrangianPolynomial.append("+").append(axisData.get(i).getY())
                    .append("(")
                    .append(lagrangianMultiplier(i))
                    .append(")");
        }
        return new Function(lagrangianPolynomial.toString());
    }

    private StringBuilder lagrangianMultiplier(int i) {
        StringBuilder numerator = new StringBuilder();
        Double denominator = 1.0D;

        for (int j = 0; j < axisData.size(); j++) {
            if (i == j) continue;
            /* Create a numerator of lagrangian multiplier */
            numerator.append("(x-").append(axisData.get(j).getX()).append(")");
            /* Calculate a dominator value */
            denominator *= (axisData.get(i).getX() - axisData.get(j).getX());

            if (-1E-9 < denominator && denominator < 1E-9) {
                throw new RuntimeException("lagrangian polynomial: too small steps!");
            }
        }
        return numerator.append("/").append(denominator);
    }
}
