package ru.ifmo.cmath.exceptions;

public class NotCommandFoundException extends RuntimeException {
    public NotCommandFoundException() {
        super();
    }
    public NotCommandFoundException(String s) {
        super(s);
    }
}
