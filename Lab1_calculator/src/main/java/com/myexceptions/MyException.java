package com.myexceptions;

public class MyException extends RuntimeException {
    protected String message;
    @Override
    public String getMessage() {
        return message;
    }
}
