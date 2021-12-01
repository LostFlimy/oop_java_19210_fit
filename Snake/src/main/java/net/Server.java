package net;

import model.AppConfig;

import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

public class Server implements Runnable {
    private MulticastSocket sendAnnouncmentSocket;
    private final String group = AppConfig.GROUP_ADDRESS;
    private final int groupPort = AppConfig.port;
    public static boolean run = true;

    @Override
    public void run() {
        try {
            sendAnnouncmentSocket = new MulticastSocket(groupPort);
            sendAnnouncmentSocket.joinGroup(InetAddress.getByName(group));

            while (run) {

            }

        } catch (Exception ex) {

        }
    }
}
