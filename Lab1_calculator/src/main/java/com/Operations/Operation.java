package com.Operations;

import com.Calculator.Context;
import com.MyExceptions.IncorrectArgumentException;
import com.MyExceptions.NotFoundArgumentsException;

abstract public class Operation {
    abstract public void doOperation(Context context, Object[] args) throws NotFoundArgumentsException, IncorrectArgumentException;
}
