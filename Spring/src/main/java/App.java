import org.springframework.*;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public void logEvent(String msg){
        String message = msg.replaceAll(((Integer)client.getId()).toString(), client.getFullName());
        eventLogger.logEvent(message);
    }

    public App(Client client, EventLogger logger){
        this.client = client;
        this.eventLogger = logger;
    }

    public static void main(String[] args) {

    }
}
