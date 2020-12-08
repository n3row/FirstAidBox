package com.FirstAidBox.FirstAidExceptions;

public class ExcessComponentException extends Exception {
    public ExcessComponentException(String message) {
        super("Excess FirstAidComponent: " + message);
    }

    public ExcessComponentException(String message, Throwable cause) {
        super("Excess FirstAidComponent: " + message, cause);
    }
}