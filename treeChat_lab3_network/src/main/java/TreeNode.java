import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.exit;

public class TreeNode {
    private ExecutorService threadPool;
    private Thread serverStarter;
    private ServerSocket server;
    private int loses;
    private String name;
    public static volatile boolean running;
    public static String neighbor = null;
    public static int port;
    public static int countOfNeigbor = 0;
    public static AccountingContainer accountingContainer = new AccountingContainer(0L);
    public static String brokensNeighbor = null;
    public static String reservNeighbor = null;


    public TreeNode(String[] args) {
        running = true;
        if(args.length > 4) {
            System.out.println("Inccorrect format of args!!!\n" +
                    "You should use : (<node name> <Losses in %> <port> <hostname:port(optional)>");
            exit(1);
        }
        name = args[0];
        loses = Integer.parseInt(args[1]);
        port = Integer.parseInt(args[2]);
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("ERROR : Can not open server, try again, please");
            System.exit(1);
        }

        threadPool = Executors.newFixedThreadPool(10);

        if(args.length == 4) {
            int pos = args[3].indexOf(':');
            String address = args[3].substring(0, pos);
            port = Integer.parseInt(args[3].substring(pos + 1));
            try {
                Socket connectSocket = new Socket(address, port);
                neighbor = address + ":" + Integer.valueOf(port).toString();
                countOfNeigbor++;
                threadPool.submit(new ClientHandler(connectSocket, loses));
            } catch (IOException e) {
                System.out.println("Can not connect to neighbor");
            }
        }

        threadPool.submit(new MessagesDeleter());
        threadPool.submit(new MessagesWriter());
    }

    public void action() {
        serverStarter = new Thread(this::run);
        serverStarter.start();
    }

    private void run() {
        threadPool.submit(new ConflictsSolution(this));
        System.out.println("Application running!");
        while (running) {
            try {
                threadPool.submit(new ClientHandler(server.accept(), loses));
            } catch (IOException ex) {
                if (server.isClosed()) {
                    System.out.println("Server was closed");
                    running = false;
                }
            }
        }
    }

    public String getInetAdress() {
        return server.getLocalSocketAddress().toString();
    }

    public void submit(String address, int port) {
        try {
            Socket connectSocket = new Socket(address, port);
            threadPool.submit(new ClientHandler(connectSocket, loses));
        } catch (IOException ex) {
            System.out.println("Can not connect to reserv neighbor");
        }
    }
}
