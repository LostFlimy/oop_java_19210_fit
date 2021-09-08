package com.myexceptions;

public class HaveNotCommandException extends MyException{
    public HaveNotCommandException(String name) {
        message = "Error: There is not " + name + " Operation";
    }
}
