package factory.workers;

import factory.Factory;
import factory.components.Motor;
import factory.store.Store;

import java.util.concurrent.TimeUnit;

public class MotorSupplier extends Supplier<Motor>{

    public MotorSupplier(Store<Motor> curStore) {
        super(curStore);
    }

    @Override
    public Motor create() {
        try {
            TimeUnit.SECONDS.sleep(Factory.timeSet.getTimeForMotor());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Motor((int)(Math.random() * 100));
    }
}
