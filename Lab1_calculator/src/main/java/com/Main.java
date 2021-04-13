package com;

import com.Calculator.Calculator;
import com.MyExceptions.MyException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        InputStream stream = null;
        if(args.length < 1) {
            stream = System.in;
        } else {
            try {
                stream = new FileInputStream(args[0]);
            } catch (FileNotFoundException e) {
                System.out.println("Error: can not open file (" + args[0] + ")\n");
                return;
            }
        }
        Calculator calc = new Calculator(stream);
        try {
            calc.run();
        } catch(MyException ex) {
            System.out.println(ex.getMessage());
            return;
        }
    }
}
