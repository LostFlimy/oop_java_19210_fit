package factory.workers;

import factory.Factory;
import factory.components.Car;
import factory.store.Store;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Dealer implements Runnable{
    private final Store<Car> carStore;
    private final Object syncObj = WorkerControl.dealerSyncObj;

    public Dealer(Store<Car> carStore) {
        this.carStore = carStore;
    }

    @Override
    public void run() {
        //Сделаем подпоток который будет доставать машины, а мы будем просто проверять состояние очереди запросов
        //по необходимости ожидая освобождения места под запросы
        while (true) {// Будем раз в несколько секунд добавлять запросы, пока это будет возможно
            try {
                TimeUnit.SECONDS.sleep(Factory.timeSet.getTimeForSaleCar());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Car car = carStore.getDetail();
            synchronized (syncObj) {
                syncObj.notifyAll();
            }
            Factory.logger.info("Получение: Dealer {}: Auto {} (Body: {} Motor: {} Accessory: {}) ",
                        Thread.currentThread().getId(), car.getID(), car.body.getID(),
                        car.motor.getID(), car.accesory.getID());

        }
    }
}
