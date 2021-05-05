package factory.workers;

import factory.Factory;
import factory.components.Body;
import factory.store.Store;

import java.util.concurrent.TimeUnit;

public class BodySupplier extends Supplier<Body>{

    public BodySupplier(Store<Body> curStore) {
        super(curStore);
    }

    @Override
    public Body create() {
        try {
            TimeUnit.SECONDS.sleep(Factory.timeSet.getTimeForBody());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Body((int)(Math.random() * 100));
    }
}
