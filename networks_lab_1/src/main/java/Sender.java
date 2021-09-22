import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Sender implements Runnable{
    private InetAddress group;
    private MulticastSocket socket;
    private final String finalMessage = "end";
    private final String startMessage = "start";
    private final String message = "hello";
    private final String groupAddr = "224.0.0.1";
    @Override
    public void run() {
        try {
            group = InetAddress.getByName(groupAddr);
            socket = new MulticastSocket();

            DatagramPacket packet = new DatagramPacket(startMessage.getBytes(), startMessage.length(), group, 44444);
            System.out.println("sender try to send start-packet to " + group.getHostAddress() + " on the port : " + packet.getPort());
            socket.send(packet);
            System.out.println("sender sended start-message");

            while(!Thread.interrupted()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("sender was interupted");
                }
                packet = new DatagramPacket(message.getBytes(), message.length(), group, 44444);
                socket.send(packet);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DatagramPacket packet = new DatagramPacket(finalMessage.getBytes(), finalMessage.length(), group, 44444);
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
