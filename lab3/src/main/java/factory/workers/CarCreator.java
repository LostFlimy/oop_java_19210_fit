package factory.workers;

import factory.Factory;
import factory.components.Accesory;
import factory.components.Body;
import factory.components.Car;
import factory.components.Motor;
import factory.store.Store;

import java.util.concurrent.TimeUnit;

public class CarCreator implements Runnable{
    private final Store<Motor> motorStore;
    private final Store<Body> bodyStore;
    private final Store<Accesory> accesoryStore;
    private final Store<Car> carStore;
    private static final Object syncObject = new Object();

    public CarCreator(Store<Motor> motorStore, Store<Body> bodyStore, Store<Accesory> accesoryStore,
                      Store<Car> carStore) {
        this.accesoryStore = accesoryStore;
        this.bodyStore = bodyStore;
        this.carStore = carStore;
        this.motorStore = motorStore;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (syncObject) {
                try {
                    syncObject.wait(); //ждем пока кто-нибудь не сделает запрос на получение новой детали
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            Body body = bodyStore.getDetail();
            Motor motor = motorStore.getDetail();
            Accesory accesory = accesoryStore.getDetail();

            try {
                TimeUnit.SECONDS.sleep(Factory.timeSet.getTimeForMachine());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Car car = new Car(body, accesory, motor, (int)(Math.random() * 100));

            //add logger

            carStore.setDetail(car);
        }
    }
}
