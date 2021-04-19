package ru.ifmo.cmath.algebra;

public class IntegralAnswer {
    private Double result, error, lastNormalValue;
    private final Integer parts;

    public IntegralAnswer(Double result, Double error, Integer parts) {
        this.result = result;
        this.error = error;
        this.parts = parts;
    }

    public IntegralAnswer(Double result, Double error, Integer parts, Double lastNormalValue) {
        this.result = result;
        this.error = error;
        this.parts = parts;
        this.lastNormalValue = lastNormalValue;
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

    public void setResult(double result) {
        this.result = result;
    }
    public void setError(Double error) {
        this.error = error;
    }
    public void setLastNormalValue(Double lastNormalValue) {
        this.lastNormalValue = lastNormalValue;
    }
}
