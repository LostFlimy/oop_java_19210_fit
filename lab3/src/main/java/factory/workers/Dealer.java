package factory.workers;

import factory.Factory;
import factory.components.Car;
import factory.store.Store;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Dealer implements Runnable{
    private final Queue<Request> requestQueue;//Сюда помещаем запросы, если хватает места на запрос
    private final Store<Car> carStore;
    private final Queue<Request> requestPool = new ConcurrentLinkedQueue<Request>();// Сюда помещаем запросы, когда места в очереди нету, чтобы ждать

    public Dealer(Queue<Request> requestQueue, Store<Car> carStore) {
        this.requestQueue = requestQueue;
        this.carStore = carStore;
    }

    @Override
    public void run() {
            //Сделаем подпоток который будет доставать машины, а мы будем просто проверять состояние очереди запросов
            //по необходимости ожидая освобождения места под запросы

            Runnable carGetter = new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        if(!requestPool.isEmpty()) {// будем доставать машины, пока запросы будут
                            Car car = carStore.getDetail();
                            Factory.logger.info("Получение: Dealer {}: Auto {} (Body: {} Motor: {} Accessory: {}) ",
                                                Thread.currentThread().getId(), car.getID(), car.body.getID(),
                                                car.motor.getID(), car.accesory.getID());
                            synchronized (requestQueue) {
                                requestQueue.remove();
                                requestQueue.notify();
                            }
                            requestPool.remove(); // удаляем из очереди очередной запрос
                        }
                    }
                }
            };
            new Thread(carGetter).start();
            while(true) {// Будем раз в несколько секунд добавлять запросы, пока это будет возможно
                try {
                    TimeUnit.SECONDS.sleep(Factory.timeSet.getTimeForSaleCar());
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
                synchronized (requestQueue) {
                    while(requestQueue.size() >= (carStore.getMaxSize() * 95 / 100)) {
                        try {
                            requestQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Request newrequest = new Request((int)(Math.random() * 100));
                    Factory.logger.info("Dealler {} отправил запрос {} на новую машину", Thread.currentThread().getId(), newrequest.GetID());
                    requestQueue.offer(newrequest);
                    requestPool.offer(newrequest);
                    requestQueue.notify();
                }

            }
        }
}
