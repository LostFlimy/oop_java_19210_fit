package factory.workers;


import factory.Factory;
import factory.components.Car;
import factory.store.Store;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class WorkerControl implements Runnable{
    public static final Object dealerSyncObj = new Object();
    private final Store<Car> carStore;
    public static final Object syncObject = new Object();

    public WorkerControl(Store<Car> carStore) {
        this.carStore = carStore;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (dealerSyncObj) {
                //Если же запросов больше нету, то засыпаем
                while (carStore.getSize() >= carStore.getMaxSize() / 2) {
                    try {
                        Factory.logger.info("WorkerControl {} уснул", Thread.currentThread().getId());
                       dealerSyncObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (carStore.getSize() < carStore.getMaxSize() / 2) {
                    synchronized (syncObject) {
                        syncObject.notify();
                    }
                }
            }
        }
    }
}
