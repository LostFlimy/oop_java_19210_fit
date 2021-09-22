import java.util.Map;

public class App {
    public static void main(String[] args) {
        Thread sender = new Thread(new Sender());
        Thread receiver = new Thread(new Reciever());
        Thread deleter = new Thread(new Deleter());
        deleter.setDaemon(true);
        sender.start();
        receiver.start();
        deleter.start();
    }
}
