package com.myexceptions;

public class NotFoundArgumentsException extends OperationException {
    public NotFoundArgumentsException(String operationName) {
        message = ("Error: Few arguments for Operation(" + operationName + ")\n");
    }
}
