package com.Operations;

import com.Calculator.Context;
import com.MyExceptions.NotFoundArgumentsException;

public class Pop extends Operation {
    @Override
    public void doOperation(Context context, Object[] args) throws NotFoundArgumentsException {
        Double x = context.getValueFromStack();
    }
}
