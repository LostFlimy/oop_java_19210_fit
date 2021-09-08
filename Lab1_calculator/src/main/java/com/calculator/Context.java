package com.calculator;

import com.myexceptions.StackIsEmptyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    private Stack<Double> stack;
    private Map<String, Double> listOfSymbols;

    public Context() {
        stack = new Stack<Double>();
        listOfSymbols = new HashMap<String, Double>();
    }

    public void setValueToStack(Double x) {
        stack.push(x);
    }

    public void define(String key, Double value) {
        listOfSymbols.put(key, value);
    }

    public int getStackSize() {
        return stack.size();
    }

    public Double getValueFromMap(String key){
        return listOfSymbols.get(key);
    }

    public Double getValueFromStack() throws StackIsEmptyException {
        if (stack.empty()) {
            throw new StackIsEmptyException();
        }
        return stack.pop();
    }

    public boolean haveValueFor(String key) {
        if(listOfSymbols.containsKey(key)) {
            return true;
        }
        return false;
    }
}
