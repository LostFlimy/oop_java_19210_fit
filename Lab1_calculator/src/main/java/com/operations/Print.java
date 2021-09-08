package com.operations;

import com.calculator.Context;

public class Print extends Operation{
    @Override
    public void doOperation(Context context, Object[] args) {
        Double x = context.getValueFromStack();
        System.out.println(x);
        context.setValueToStack(x);
    }
}
