package ru.ifmo.cmath.exceptions;

public class NotFileFoundException extends RuntimeException {
    public NotFileFoundException() {
        super();
    }
    public NotFileFoundException(String s) {
        super(s);
    }
}
