
import com.sun.source.tree.Tree;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.stream.Stream;

public class ClientHandler implements Runnable{
    private final Socket socket;
    private final int loses;
    private String neighbor;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final LinkedHashMap<Long, Long> messagesGUIDs = new LinkedHashMap<>();
    private boolean broken = false;
    private final int DELAY = 8000;

    public ClientHandler(Socket socket, int loses) throws IOException {
        System.out.println(socket.getPort() + "\n" + socket.getLocalPort());
        this.socket = socket;
        this.loses = loses;
        TreeNode.countOfNeigbor++;
        in =  new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("Connected " + socket.getInetAddress());
        byte[] buffer = new byte[250];
        try{
            System.out.println("Swap with neighbors...");
            if (TreeNode.neighbor == null) {
                System.out.println("I have not neighbors yet...");
                out.writeUTF(socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
            }
            else {
                System.out.println("I have neighbor" + TreeNode.neighbor);
                out.writeUTF(TreeNode.neighbor);
            }
            System.out.println("Try to read neighbor");
            neighbor = this.in.readUTF();
            TreeNode.neighbor = neighbor;
            System.out.println("Neighbor of " + socket.getInetAddress().toString() + " is " + neighbor);
            new Thread(this::read).start();
            while (TreeNode.running && !broken) {
                Map<Long, String> messages = TreeNode.accountingContainer.getMessages();

                for (Long id : messages.keySet()) {
                    out.writeUTF(messages.get(id));
                }

                messagesGUIDs.keySet().forEach(k -> {
                            if (messagesGUIDs.get(k) < System.currentTimeMillis() - DELAY) {
                                TreeNode.brokensNeighbor = socket.getInetAddress().getHostAddress();
                                TreeNode.reservNeighbor = neighbor;
                                broken = true;
                            }
                        });
            }
        } catch (IOException ex) {
            System.out.println("Input/output exception...");
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }

    public void read() {
        while (TreeNode.running && !broken) {
            try {
                String message = in.readUTF();
                String sender = socket.getInetAddress().toString();
                int pos = message.indexOf(":");
                Long GUID = Long.parseLong(message.substring(0, pos));
                if (GUID >= 0) {
                    if (!(messagesGUIDs.containsKey(GUID)) && (Math.random() * 100 > loses)) {
                        TreeNode.accountingContainer.createMessage(GUID, message, sender);
                        out.writeUTF(Long.toString(-GUID) + ":" + " Accepted");
                        System.out.println(message);
                        messagesGUIDs.put(GUID, System.currentTimeMillis());
                        if (messagesGUIDs.size() > 100) {
                            messagesGUIDs.keySet().stream()
                                    .filter(k -> messagesGUIDs.get(k) > System.currentTimeMillis() + 10000)
                                    .forEach(messagesGUIDs::remove);
                        }
                    }
                } else {
                    TreeNode.accountingContainer.acceptMsg(-GUID);
                }
            } catch(IOException ex) {
                broken = true;
                System.out.println("Input/output exception...");
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
