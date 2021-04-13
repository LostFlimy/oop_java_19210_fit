package com.MyExceptions;

public class IncorrectArgumentException extends OperationException{
    public IncorrectArgumentException(String arg, String command) {
        message = ("Error:Incorrect argument (" + arg + ") for Operation (" + command + ")\n");
    }
}
