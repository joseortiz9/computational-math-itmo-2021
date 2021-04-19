package ru.ifmo.cmath.exceptions;

public class DivergeException extends RuntimeException {
    public DivergeException() {
        super();
    }
    public DivergeException(String message) {
        super(message);
    }
}
