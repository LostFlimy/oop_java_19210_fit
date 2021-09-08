import java.util.Map;

public class App {
    public static void main(String[] args) {
        RequestParser parser = new RequestParser();
        parser.parse("start work");
        System.out.println("addr : " + parser.getAddr());
        System.out.println("operation : " + parser.getOperation());
    }
}
