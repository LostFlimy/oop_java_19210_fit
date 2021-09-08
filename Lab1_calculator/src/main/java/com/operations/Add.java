package com.operations;

import com.calculator.Context;

public class Add extends Operation {
    @Override
    public void doOperation(Context context, Object[] args) {
        Double a = context.getValueFromStack();
        Double b = context.getValueFromStack();
        Double res = a + b;
        context.setValueToStack(res);
    }
}
