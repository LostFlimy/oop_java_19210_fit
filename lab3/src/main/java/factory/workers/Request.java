package factory.workers;

public class Request {
    private final int id;
    public Request(int ID){
        id = ID;
    }
    public int GetID() {
        return id;
    }
}
