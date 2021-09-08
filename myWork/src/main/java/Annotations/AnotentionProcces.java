package Annotations;

import java.lang.reflect.Method;

public class AnotentionProcces {
    public static void inspectService(Class<?> service)
    {
        if(service.isAnnotationPresent(Service.class)){
            Service ann = service.getAnnotation(Service.class);
            System.out.println(ann.name());
            Method[] methods = service.getMethods();

            System.out.println(service.getSimpleName() + " have " + methods.length + " methods");
            boolean mark = false;
            for(Method x : methods) {
                System.out.print(x.getName());
                if(x.isAnnotationPresent(Init.class)) {
                    System.out.print(" is Init method!");
                    mark = true;
                }
                System.out.println("");
            }
            if(mark == true) {
                System.out.println(service.getSimpleName() + " have Init method!");
            }
            else {
                System.out.println(service.getSimpleName() + " haven't Init method!");
            }
        }
        else {
            System.out.println("Isn't service");
        }
    }
}
