package com.MyExceptions;

public class DivisionByZeroException extends OperationException{
    public DivisionByZeroException() {
        message = "Error: division by zero!";
    }
}
