package ru.ifmo.cmath.exceptions;

public class NotCommandFoundException extends RuntimeException {
    public NotCommandFoundException() {
        super("Command not found :(");
    }
    public NotCommandFoundException(String s) {
        super(s);
    }
}
