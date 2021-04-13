package com.Operations;

import com.Calculator.Context;

public class Add extends Operation {
    @Override
    public void doOperation(Context context, Object[] args) {
        Double a = context.getValueFromStack();
        Double b = context.getValueFromStack();
        Double res = a + b;
        context.setValueToStack(res);
    }
}
