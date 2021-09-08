package com.operations;

import com.calculator.Context;

public class Define extends Operation{
        @Override
        public void doOperation(Context context, Object[] args) {
                String key = (String)args[0];
                Double value = Double.parseDouble((String)args[1]);
                context.define(key, value);
        }
}
