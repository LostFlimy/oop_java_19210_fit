package factory;

import factory.components.Accesory;
import factory.components.Body;
import factory.components.Car;
import factory.components.Motor;
import factory.store.Store;
import factory.workers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Factory {
    public static final TimeSettingsForWorkers timeSet = new TimeSettingsForWorkers();
    private final Store<Motor> motorStore;
    private final Store<Body> bodyStore;
    private final Store<Accesory> accesoryStore;
    private final Store<Car> carStore;
    private final ThreadPool threadPool;
    private final Queue<Request> requestQueue;

    public static Logger logger = LoggerFactory.getLogger(Factory.class);

    public Factory() {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream("src/main/resources/configure.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        motorStore = new Store<Motor>(Integer.parseInt(property.get("StorageMotorSize").toString()));
        bodyStore = new Store<Body>(Integer.parseInt(property.get("StorageBodySize").toString()));
        accesoryStore = new Store<Accesory>(Integer.parseInt(property.get("StorageAccessorySize").toString()));
        carStore = new Store<Car>(Integer.parseInt(property.get("StorageAutoSize").toString()));

        logger.info("MotorStore size {}, BodyStore size {}, AccesoryStore size {}, CarStore size {}",
                    motorStore.getMaxSize(), bodyStore.getMaxSize(),
                    accesoryStore.getMaxSize(), carStore.getMaxSize());

        requestQueue = new ConcurrentLinkedQueue<Request>();

        int countAccesorySupp = Integer.parseInt(property.get("AccessorySuppliers").toString());
        int countMotorSupp = Integer.parseInt(property.get("MotorSuppliers").toString());
        int countBodySupp = Integer.parseInt(property.get("BodySuppliers").toString());
        int countCarCreat = Integer.parseInt(property.get("Workers").toString());
        int countDealCreat = Integer.parseInt(property.get("Dealers").toString());

        ViewControl.start(new View(bodyStore, accesoryStore, motorStore, carStore));

        threadPool = new ThreadPool(countAccesorySupp + countBodySupp + countCarCreat +
                                                            countDealCreat + countMotorSupp + 1);
        for(int i = 0; i < countAccesorySupp; ++i) {
            threadPool.execute(new AccesorySupplier(accesoryStore));
        }
        for(int i = 0; i < countBodySupp; ++i) {
            threadPool.execute(new BodySupplier(bodyStore));
        }
        for(int i = 0; i < countMotorSupp; ++i) {
            threadPool.execute(new MotorSupplier(motorStore));
        }
        for(int i = 0; i < countCarCreat; ++i) {
            threadPool.execute(new CarCreator(motorStore, bodyStore, accesoryStore, carStore));
        }
        threadPool.execute(new WorkerControl(requestQueue, carStore));
        for(int i = 0; i < countDealCreat; ++i) {
            threadPool.execute(new Dealer(requestQueue, carStore));
        }
    }
}
