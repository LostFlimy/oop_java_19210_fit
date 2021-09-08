package com.myexceptions;

public class BadReadException extends MyException{
    public BadReadException() {
        message = "Error: Reader can not read new string!\n";
    }
}
