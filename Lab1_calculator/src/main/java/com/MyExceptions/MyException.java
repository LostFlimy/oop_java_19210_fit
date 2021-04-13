package com.MyExceptions;

public class MyException extends RuntimeException {
    protected String message;
    @Override
    public String getMessage() {
        return message;
    }
}
