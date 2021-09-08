package com.myexceptions;

public class ControllerBadFileException extends MyException{
    public ControllerBadFileException(){
        message = "Error: Controller can not work with file";
    }
}
