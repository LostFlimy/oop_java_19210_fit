package factory.workers;


import factory.Factory;
import factory.components.Car;
import factory.store.Store;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class WorkerControl implements Runnable{
    private final Queue<Request> requestQueue;
    private final Store<Car> carStore;
    public static final Object syncObject = new Object();

    public WorkerControl(Queue<Request> requestQueue, Store<Car> carStore) {
        this.requestQueue = requestQueue;
        this.carStore = carStore;
    }

    @Override
    public void run() {
        while(true) {//Хотим, чтобы машин всегда хватало, поэтому стараемся, чтобы их было столько же, сколько
                     //и запросов на создание машин, поэтому запросов в очереди не более, чем размер склада
            /* Если машин на складе меньше, чем количество запросов на получение машин, то
               Создаем новые машины*/
            synchronized (requestQueue) {
                //Если же запросов больше нету, то засыпаем
                while (requestQueue.isEmpty()) {
                    try {
                        Factory.logger.info("WorkerControl {} уснул", Thread.currentThread().getId());
                        requestQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (carStore.getSize() < requestQueue.size()) {
                    synchronized (syncObject) {
                        syncObject.notify();
                    }
                }
                requestQueue.notifyAll(); // Если есть необработанные запросы, то после их выполнения пусть все просыпаются
            }
        }
    }
}
