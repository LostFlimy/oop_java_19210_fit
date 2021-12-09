import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bStream.write(String.valueOf("Hello").getBytes());
        System.out.println(Arrays.toString(bStream.toByteArray()));
        bStream.reset();
        bStream.write(String.valueOf("World").getBytes());
        System.out.println(Arrays.toString(bStream.toByteArray()));

        //if(args.length < 3) {
        //    System.out.println("You should use (-send <hostname:port> <filename>) or (-store <port> <storage-path>)");
        //    System.exit(1);
        //}
        //switch(args[0]) {
        //    case "-send" :
        //        Client.clientAction(args);
        //        return;
        //    case "-store" :
        //        Server server = new Server(args);
        //        server.serverAction();
        //        return;
        //    default:
        //        System.out.println("You should use (-send <hostname:port> <filename>) or (-store <port> <storage-path>)");
        //        System.exit(1);
        //}
    }
}
