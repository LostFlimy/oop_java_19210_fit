package com.Calculator;

public class CommandsParser {
    private String nameOfOperation;
    private String[] args;

    public CommandsParser() {
    }

    public void split(String str) {
        String[] subStr;
        String delim = " ";
        subStr = str.split(delim);
        nameOfOperation = subStr[0].toLowerCase();

        int len = subStr.length - 1;
        if(len == 0) {
            args = null;
        } else {
            args = new String[len];
        }

        if(subStr.length > 1) {
            for(int i = 0; i < len; ++i) {
                args[i] = subStr[i + 1];
            }
        }
    }

    public String getClassName() {
        return nameOfOperation;
    }

    public String[] getArgs() {
        return args;
    }
}
