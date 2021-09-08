package com.myexceptions;

public class StackIsEmptyException extends OperationException{
    public StackIsEmptyException(){
        message = "Error: Elements in stack is not enough\n";
    }
}
