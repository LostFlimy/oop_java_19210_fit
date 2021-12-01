import java.io.IOException;
import java.net.*;

public class Reciever implements Runnable{
    private InetAddress group;
    private MulticastSocket socket;
    //варианты сообщений (стартовое, текущее и конечное)
    private final String finalMessage = "end";
    private final String startMessage = "start";
    private final String message = "hello";
    //адрес групповой рассылки, взятый из группы групповых адресов, которые направлены на отправление
    //сообщений в локальной сети
    private final String groupAddr = "224.0.0.1";
    @Override
    public void run() {
        try {
            //Задаем адрес группы, создаем сокет мультикаст рассылки и джоинемся в группу
            InetAddress group = InetAddress.getByName(groupAddr);
            MulticastSocket socket = new MulticastSocket(44444);
            socket.joinGroup(group);

            //пока не прерваны работаем
            while(!(Thread.interrupted())) {
                //здесь мы инициализируем датаграм пакет
                byte[] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                //пытаемся получить пакет
                socket.receive(packet);
                //преобразовали в строку
                String sendedMsg = new String(buffer);
                //Эти 3 условия проверяют, подходит ли сообщение по сигнатуре приложения
                // и, если подходит, то делаем соответствующие этому сообщению действия
                //start : добавляем запись в таблицу и распечатываем таблицу
                //hello : обновляем запись в таблице
                //final : устанавливаем в таблице запись как мертвую
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
                //выводим сообщение с указанием адреса отправителя
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
