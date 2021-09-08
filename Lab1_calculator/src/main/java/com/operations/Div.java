package com.operations;

import com.calculator.Context;
import com.myexceptions.DivisionByZeroException;

public class Div extends Operation {
    @Override
    public void doOperation(Context context, Object[] args) {
        Double a = context.getValueFromStack();
        Double b = context.getValueFromStack();
        if(b.equals(0.0)) {
            context.setValueToStack(b);
            context.setValueToStack(a);
            throw new DivisionByZeroException();
        }
        Double res = a / b;
        context.setValueToStack(res);
    }
}
