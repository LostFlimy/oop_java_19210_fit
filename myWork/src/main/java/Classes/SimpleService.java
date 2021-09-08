package Classes;

import Annotations.Init;
import Annotations.Service;

@Service(name = "Simple")
public class SimpleService {
    @Init
    public void initService()
    {
        System.out.println("initialize simple!");
    }
}
