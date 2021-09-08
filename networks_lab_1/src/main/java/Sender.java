import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Sender implements Runnable{
    private InetAddress group;
    private MulticastSocket socket;
    private String finalMessage;
    @Override
    public void run() {
        try {
            group = InetAddress.getByName("226.10.10.10");
            socket = new MulticastSocket();
            String message = "start work";
            finalMessage = "end work";

            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), group, 3456);
            socket.send(packet);

            while(!Thread.interrupted()) {
                Thread.sleep(1000);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            DatagramPacket packet = new DatagramPacket(finalMessage.getBytes(), finalMessage.length(), group, 3456);
            try {
                socket.send(packet);
            } catch (IOException e) {
                socket.close();
                e.printStackTrace();
            }
            socket.close();
        }
    }
}
