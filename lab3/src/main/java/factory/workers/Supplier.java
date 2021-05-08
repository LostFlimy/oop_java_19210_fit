package factory.workers;

import factory.Factory;
import factory.components.Detail;
import factory.store.Store;

public abstract class Supplier<T extends Detail> implements Runnable{
    private final Store<T> store;
    public abstract T create();
    public Supplier(Store<T> curStore) {
        store = curStore;
    }

    @Override
    public void run() {
        while(true) {
            store.setDetail(create());
        }
    }
}

