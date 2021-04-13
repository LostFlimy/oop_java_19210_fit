package com.Operations;

import com.Calculator.Context;

public class Sqrt extends Operation{
    @Override
    public void doOperation(Context context, Object[] args) {
        Double x = context.getValueFromStack();
        x = (Double)Math.sqrt(x.doubleValue());
        context.setValueToStack(x);
    }
}
