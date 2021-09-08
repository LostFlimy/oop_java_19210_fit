package com.operations;

import com.calculator.Context;
import com.myexceptions.NotFoundArgumentsException;

public class Pop extends Operation {
    @Override
    public void doOperation(Context context, Object[] args) throws NotFoundArgumentsException {
        Double x = context.getValueFromStack();
    }
}
