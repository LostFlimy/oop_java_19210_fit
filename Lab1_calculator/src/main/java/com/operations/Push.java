package com.operations;

import com.calculator.Context;

public class Push extends Operation{
    @Override
    public void doOperation(Context context, Object[] args) {
        Double newElem = 0.0;
        newElem = Double.parseDouble((String) args[0]);
        context.setValueToStack(newElem);
    }
}
