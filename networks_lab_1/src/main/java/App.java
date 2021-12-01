import java.util.Map;

public class App {
    public static void main(String[] args) {
        Thread sender = new Thread(new Sender());
        Thread receiver = new Thread(new Reciever());
        Thread deleter = new Thread(new Deleter());
        //Поток отвечающий за удаление записей из таблицы и их распечатку не должен продолжать работу
        //Если мы закончили слушать/отправлять рассылку (очевидно), поэтому он демон
        deleter.setDaemon(true);
        sender.start();
        receiver.start();
        deleter.start();
    }
}
