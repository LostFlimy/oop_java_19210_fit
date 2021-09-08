public class Client {
    private int id;
    private String fullName;

    public Client(int id, String fullName){
        this.id = id;
        this.fullName = fullName;
    }

    public void setId(int curid){
        id = curid;
    }

    public void setFullName(String name) {
        fullName = name;
    }

    public String getFullName(){
        return fullName;
    }

    public int getId() {
        return id;
    }
}
