package com.calculator;

import com.operations.Operation;

public class ReturnType {
    private Operation command;
    private String[] args;

    ReturnType(Operation newOperation, String[] newArgs) {
        command = newOperation;
        args = newArgs;
    }

    public Operation getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
