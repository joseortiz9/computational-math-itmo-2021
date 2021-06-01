package ru.ifmo.cmath.algebra;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {
    private final String exp;
    private final Expression expression;

    public Function(String expression) {
        this.exp = expression;
        this.expression = new ExpressionBuilder(expression).variable("x").build();
    }

    public Double apply(double x) {
        try {
            return this.expression.setVariable("x", x).evaluate();
        } catch (RuntimeException exception) {
            return Double.NaN;
        }
    }

    @Override
    public String toString() {
        return exp;
    }
}
