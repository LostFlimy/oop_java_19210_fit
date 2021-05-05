package factory;

import factory.components.Accesory;
import factory.components.Body;
import factory.components.Car;
import factory.components.Motor;
import factory.store.Store;
import factory.workers.TimeSettingsForWorkers;

import java.util.logging.Logger;

public class Factory {
    public static final TimeSettingsForWorkers timeSet = new TimeSettingsForWorkers();
    private Store<Motor> motorStore;
    private Store<Body> bodyStore;
    private Store<Accesory> accesoryStore;
    private Store<Car> carStore;

    public Factory() {

    }
}
