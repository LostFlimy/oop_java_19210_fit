import java.io.IOException;
import java.net.*;

public class Reciever implements Runnable{
    private InetAddress group;
    private MulticastSocket socket;
    private final String finalMessage = "end";
    private final String startMessage = "start";
    private final String message = "hello";
    private final String groupAddr = "224.0.0.1";
    @Override
    public void run() {
        try {
            InetAddress group = InetAddress.getByName(groupAddr);
            MulticastSocket socket = new MulticastSocket(44444);
            socket.joinGroup(group);

            while(!(Thread.interrupted())) {
                byte[] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String sendedMsg = new String(buffer);
                if(sendedMsg.trim().toLowerCase().equals(startMessage)) {
                    TableOfAddresses.updateMark(packet.getAddress().getHostAddress(), Long.valueOf(System.currentTimeMillis()));
                    TableOfAddresses.printTable();
                }
                if(sendedMsg.trim().equals(finalMessage)) {
                    TableOfAddresses.updateMark(packet.getAddress().getHostAddress(), Long.valueOf(-1));
                }
                if(sendedMsg.trim().equals(message)) {
                    TableOfAddresses.updateMark(packet.getAddress().getHostAddress(), System.currentTimeMillis());
                }
                System.out.println(packet.getAddress().getHostAddress() + " send message : '" + sendedMsg + "'");
            }
            try {
                System.out.println("try to close socket");
                socket.close();
            } catch (Exception ex) {
                System.out.println("can not close socket");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                System.out.println("try to close socket");
                socket.close();
            } catch (Exception ex) {
                System.out.println("can not close socket");
            }
        }

    }
}
