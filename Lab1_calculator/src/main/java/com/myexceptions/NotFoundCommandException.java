package com.myexceptions;

public class NotFoundCommandException extends MyException{
    public NotFoundCommandException(String className) {
        message = ("Error:Command (" + className + ") not found\n");
    }
}
