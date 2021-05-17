package ru.ifmo.cmath.algebra;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Function {
    private final String exp;
    private final Expression expression;

    public Function(String exp, String... variables) {
        this.exp = exp;
        this.expression = new ExpressionBuilder(exp).variables(variables).build();
    }

    public Double apply(Double... values) {
        try {
            int index = 0;
            for (String variable : expression.getVariableNames()) {
                this.expression.setVariable(variable, values[index++]);
            }
            return this.expression.evaluate();
        } catch (RuntimeException e) {
            return Double.NaN;
        }
    }

    @Override
    public String toString() {
        return exp;
    }
}
