package com.myexceptions;

public class CommandNotFoundException extends MyException{
    public CommandNotFoundException(String name) {
        message = "Error: Command with name " + name + " not founded in property file";
    }
}
