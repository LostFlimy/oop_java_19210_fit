import java.io.IOException;
import java.net.*;

public class Reciever implements Runnable{
    @Override
    public void run() {
        try {
            InetAddress group = InetAddress.getByName("226.10.10.10");
            MulticastSocket socket = new MulticastSocket(3456);
            socket.joinGroup(group);

            while(!Thread.interrupted()) {
                byte[] buffer = new byte[200];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println(new String(buffer) + " send message and it is alive!");
            }
            System.out.println("Try to close socket");
            socket.close();
            System.out.println("socket was closed");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
