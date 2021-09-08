package Classes;

import Annotations.Init;
import Annotations.Service;

@Service(name = "Lazy", lazyLoad = true)
public class LazyService {
    @Init
    public void lazyInit() throws Exception
    {
        System.out.println("lazy initialize!");
    }
}
