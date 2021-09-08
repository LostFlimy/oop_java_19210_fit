package com.calculator;

import com.myexceptions.BadReadException;
import com.myexceptions.CommandNotFoundException;
import com.myexceptions.HaveNotCommandException;
import com.operations.Operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandReader {
    private final CommandsParser parser;
    private final Fabric fabric;
    private BufferedReader reader;
    private Operation operation;

    public CommandReader(InputStream stream) {
        InputStreamReader streamReader = new InputStreamReader(stream);
        reader = new BufferedReader(streamReader);
        parser = new CommandsParser();
        fabric = new Fabric("/classesForFabric");
    }

    public ReturnType readCommand() {
        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException e) {
            throw new BadReadException();
        }
        if(str == null || str.isEmpty()) {
            return null;
        }
        parser.split(str);
        String commandName = parser.getClassName();
        String[] args = parser.getArgs();
        try {
            operation = fabric.getNewOperation(commandName);
        } catch(CommandNotFoundException | HaveNotCommandException ex) {
            String[] exReturn = new String[1];
            exReturn[0] = commandName;
            return (new ReturnType(null, exReturn));
        }
        return (new ReturnType(operation, args));
    }

    public Operation getCommand() {
        return operation;
    }
}
