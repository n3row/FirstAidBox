package com.FirstAidBox.FirstAidExceptions;

public class AlreadyFullException extends Throwable {
    public AlreadyFullException() {
        super("This First Aid Box is already been full!");
    }
}