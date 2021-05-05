package factory.workers;

import factory.Factory;
import factory.components.Accesory;
import factory.store.Store;

import java.util.concurrent.TimeUnit;

public class AccesorySupplier extends Supplier<Accesory>{

    public AccesorySupplier(Store<Accesory> curStore) {
        super(curStore);
    }

    @Override
    public Accesory create() {
        try {
            TimeUnit.SECONDS.sleep(Factory.timeSet.getTimeForAccesory());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Accesory((int)(Math.random() * 100));
    }
}
