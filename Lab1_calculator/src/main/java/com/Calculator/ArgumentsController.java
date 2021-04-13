package com.Calculator;

import com.MyExceptions.*;
import com.Operations.Operation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

public class ArgumentsController {
    private final Map<String, Integer> countArgsToOper;
    private final Map<String, Integer> countStackElementsToOper;
    private final Map<String, String[]> typeNamesToOper;


    public ArgumentsController(InputStream stream) {
        countArgsToOper = new HashMap<String, Integer>();
        countStackElementsToOper = new HashMap<String, Integer>();
        typeNamesToOper = new HashMap<String, String[]>();

        try (InputStreamReader inputStreamReader =
                     new InputStreamReader(stream)
        ) {
            PropertyResourceBundle bundle = new PropertyResourceBundle(inputStreamReader);

            var propKeys = bundle.getKeys();

            while (propKeys.hasMoreElements()) {
                String key = propKeys.nextElement();
                String str = bundle.getString(key);
                String[] params = str.split(" ");
                countArgsToOper.put(key.toLowerCase(), Integer.parseInt(params[0]));
                countStackElementsToOper.put(key.toLowerCase(), Integer.parseInt(params[1]));
                String[] typeNames = new String[params.length - 2];
                for(int i = 0; i < params.length - 2; ++i) {
                    typeNames[i] = params[i + 2];
                }
                if(params.length - 2 > 0) {
                    typeNamesToOper.put(key.toLowerCase(), typeNames);
                } else {
                    typeNamesToOper.put(key.toLowerCase(), null);
                }
            }
        } catch (IOException ex) {
            throw new ControllerBadFileException();
        }
    }

    public ArgumentsController(String filename) {
        this(ArgumentsController.class.getResourceAsStream(filename));
    }

    public void doOperationWithControl(Operation command, Object[] args, Context context){
        String commandNameClass = command.getClass().getName().toLowerCase();
        Integer countArgs = countArgsToOper.get(commandNameClass);
        if(countArgs == 0 && args != null) {
            throw new MoreThanNeedException(command.toString());
        }
        if(countArgs != 0 && args.length > countArgs) {
            throw new MoreThanNeedException(command.toString());
        }
        if(countArgs != 0 && args.length < countArgs) {
            throw new NotFoundArgumentsException(command.toString());
        }
        if(countArgs != 0 && args.length == countArgs) {
            for(int i = 0; i < args.length; ++i) {
                if (typeNamesToOper.get(commandNameClass)[i].equals("Double")) {
                    Double x = null;
                    try{
                        x = Double.parseDouble((String)args[i]);
                    } catch(NumberFormatException ex) {
                        if(context.haveValueFor((String)args[i])) {
                            x = context.getValueFromMap((String)args[i]);
                        }
                        else {
                            throw new IncorrectArgumentException((String)args[i], commandNameClass);
                        }
                    }
                    args[i] = x.toString();
                }
            }
        }
        if(countStackElementsToOper.get(commandNameClass) > context.getStackSize()) {
            throw new StackIsEmptyException();
        }
        command.doOperation(context, args);
    }

}
