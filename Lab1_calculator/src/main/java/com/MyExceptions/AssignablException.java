package com.MyExceptions;

public class AssignablException extends MyException{
    public AssignablException(String name) {
        message = "Error: " + name + " is not Operation";
    }
}
