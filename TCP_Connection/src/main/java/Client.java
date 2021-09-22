import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Client {
    private static File file;
    private static Socket client;
    private static FileInputStream fStreamIn;
    private static DataOutputStream out;
    private static DataInputStream in;

    public static void clientAction(String[] args) {
        if(args.length < 3) {
            System.out.println("ERROR : Few arguments, try again please");
        }
        String filename = args[2];
        int pos = args[1].indexOf(':');
        String address = args[1].substring(0, pos);
        Integer port = Integer.parseInt(args[1].substring(pos + 1));
        try {
            System.out.println("Try to connect to server with address = " + address + " and port = " + port + " ...");
            Socket client = new Socket(address, port);
            System.out.println("Connection was successful!");

            file = new File(filename);

            fStreamIn = new FileInputStream(file);

            out = new DataOutputStream(client.getOutputStream());

            in = new DataInputStream(client.getInputStream());

            //создали буфер куда будем читать из файла и писать в сокет
            byte[] buf = new byte[256];


            System.out.println("Try to upload filesize...");
            //Отправляем размер файла
            out.writeLong(file.length());
            int response = in.read();
            if(response < 0) {
                System.out.println("ERROR : Server can not upload this file");
                out.close();
                in.close();
                fStreamIn.close();
                client.close();
            }
            System.out.println("Filesize uploaded successful");
            byte[] filenameInBytes = file.getName().getBytes("UTF-8");
            System.out.println("Try to upload filename size...");
            out.writeInt(filenameInBytes.length);

            System.out.println("Try to upload filename...");
            //Начинаем писать в сокет на сервер имя файла
            out.write(filenameInBytes);

            System.out.println("Filename uploaded successful");

            //переменная хранящая количество переданных из файла байт
            int count;

            //пишем сам файл
            System.out.println("Start to upload a file...");
            while((count = fStreamIn.read(buf)) > 0) {
                //Это значит, что мы дошли до конца файла
                if(count < 256) {
                    buf = Arrays.copyOf(buf, count);
                    out.write(buf);
                    break;
                }
                out.write(buf);
            }

            response = in.read();
            if(response > 0) {
                System.out.println("Upload ended successful");
                client.close();
                return;
            } else {
                System.out.println("Error : can not upload file");
            }
        } catch (IOException e) {
            e.printStackTrace();
            try{
                in.close();
            } catch (IOException ex) {
            }
            try{
                out.close();
            } catch (IOException ex) {
            }
            try{
                client.close();
            } catch (IOException ex) {
            }
            try{
                fStreamIn.close();
            } catch (IOException ex) {
            }
        }
    }
}

