package factory.components;

abstract public class Detail {
    private final int id;

    public Detail(int curid) {
        id = curid;
    }

    public int getID() {
        return id;
    }
}
