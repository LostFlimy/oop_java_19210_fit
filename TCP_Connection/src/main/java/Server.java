import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Server {

    private ExecutorService threadPool;
    private String filepath;
    private ServerSocket server;
    private Set<String> filenames = new HashSet<>();
    private Thread serverStarter;
    public static volatile boolean running;

    public Server(String[] args) {
        running = true;
        if(args.length != 3) {
            System.out.println("ERROR : Incorrect form of arguments, launch again please");
        }
        Integer port = Integer.parseInt(args[1]);
        filepath = args[2];
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("ERROR : Can not open server, try again, please");
            System.exit(1);
        }
        threadPool = Executors.newFixedThreadPool(10);
    }

    public void serverAction() {
        serverStarter = new Thread(this::run);
        serverStarter.start();
    }

    private void run() {
        System.out.println("Server started");
        while(running) {
            try {
                threadPool.submit(new ClientHandler(server.accept(), filepath));
            } catch (IOException ex) {
                if(server.isClosed()) {
                    System.out.println("Server was closed or can not upload file");
                }
            }
        }
    }

    public void putNewName(String newName) {
        filenames.add(newName);
    }

    public void removeName(String name) {
        filenames.remove(name);
    }

    public boolean containsName(String name) {
        return filenames.contains(name);
    }
}
