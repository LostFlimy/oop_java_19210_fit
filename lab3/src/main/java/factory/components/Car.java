package factory.components;

public class Car {
    private Body body;
    private Accesory accesory;
    private Motor motor;
    private int id;

    public Car(Body _body, Accesory _accesory, Motor _motor, int _id) {
        body = _body;
        accesory = _accesory;
        motor = _motor;
        id = _id;
    }

    public int getID() {
        return id;
    }
}
