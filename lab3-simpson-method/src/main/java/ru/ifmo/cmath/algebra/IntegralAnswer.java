package ru.ifmo.cmath.algebra;

public class IntegralAnswer {
    private final Double result, error;
    private final Integer parts;

    public IntegralAnswer(Double result, Double error, Integer parts) {
        this.result = result;
        this.error = error;
        this.parts = parts;
    }

    public Double getResult() {
        return result;
    }

    public Double getError() {
        return error;
    }

    public Integer getParts() {
        return parts;
    }
}
