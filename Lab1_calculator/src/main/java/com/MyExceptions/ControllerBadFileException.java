package com.MyExceptions;

public class ControllerBadFileException extends MyException{
    public ControllerBadFileException(){
        message = "Error: Controller can not work with file";
    }
}
