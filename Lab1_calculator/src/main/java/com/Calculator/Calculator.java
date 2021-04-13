package com.Calculator;

import com.MyExceptions.CommandNotFoundException;
import com.MyExceptions.OperationException;

import java.io.InputStream;

public class Calculator {
    private final CommandReader reader;
    private final Context context;
    private Fabric factory;
    private final ArgumentsController controller;

    public Calculator(InputStream stream) {
        context = new Context();
        reader = new CommandReader(stream);
        factory = new Fabric("/classesForFabric");
        controller = new ArgumentsController("/typesCountsForOperations.properties");
    }

    public void run() {
        ReturnType commandWithArgs = null;
        while ((commandWithArgs = reader.readCommand()) != null) {
            try {
                if(commandWithArgs.getCommand() == null) {
                    throw new CommandNotFoundException(commandWithArgs.getArgs()[0]);
                }
                controller.doOperationWithControl(commandWithArgs.getCommand(), commandWithArgs.getArgs(), context);
            } catch(OperationException | CommandNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
