package com.calculator;

import com.myexceptions.AssignablException;
import com.myexceptions.CommandNotFoundException;
import com.myexceptions.FabricException;
import com.myexceptions.HaveNotCommandException;
import com.operations.Operation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;


public class Fabric {
    private final Map<String, Class<?>> stringToOper;

    public Fabric(String COMMANDS_PROP) {
        this(Fabric.class.getResourceAsStream(COMMANDS_PROP));
    }

    public Fabric(InputStream stream) {
        stringToOper = new HashMap<String, Class<?>>();

        try (InputStreamReader inputStreamReader =
                     new InputStreamReader(stream)
        ) {
            PropertyResourceBundle bundle = new PropertyResourceBundle(inputStreamReader);

            var propKeys = bundle.getKeys();

            while (propKeys.hasMoreElements()) {
                String key = propKeys.nextElement();
                String className = bundle.getString(key);
                Class<?> cl = null;
                try {
                    cl = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new CommandNotFoundException(className);
                }

                if (!Operation.class.isAssignableFrom(cl)) {
                    throw new AssignablException(className);
                }

                stringToOper.put(key.toLowerCase(), cl);
            }
        } catch (IOException | NullPointerException ex) {
            throw new FabricException();
        }
    }

    public Operation getNewOperation(String name) {
        if(!(stringToOper.containsKey(name))) {
            throw new HaveNotCommandException(name);
        }
        try {
            Operation result;
            result = (Operation) stringToOper.get(name.toLowerCase()).getDeclaredConstructor().newInstance();
            if (result == null) {
                throw new NullPointerException();
            }
            return result;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NullPointerException ex) {
            throw new HaveNotCommandException(name);
        }
    }
}
