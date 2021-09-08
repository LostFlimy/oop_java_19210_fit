package com.myexceptions;

public class MoreThanNeedException extends OperationException{
    public MoreThanNeedException(String name) {
        message = "Error: Count of arguments is more than operation(" + name + ") need!\n";
    }
}
