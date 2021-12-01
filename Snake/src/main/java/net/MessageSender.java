package net;

import app.App;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class MessageSender<Message> implements Runnable {
    private Set<Message> messages = new HashSet<>();
    private Socket socket;
    private ObjectOutputStream dataOutputStream;

    public MessageSender(Socket socket) {
        this.socket = socket;
        try {
            dataOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            App.running = false;
            System.out.println("Can not send messages exit...");
        }
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    @Override
    public void run() {
        while (App.running) {
            for (Message msg : messages) {
                try {
                    dataOutputStream.writeObject(msg);
                } catch (IOException e) {
                    System.out.println("Can not send message exit...");
                    App.running = false;
                    break;
                }
            }
        }
    }
}
