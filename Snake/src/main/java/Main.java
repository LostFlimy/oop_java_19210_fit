import app.App;
import model.message.Message;
import model.message.PingMsg;

import java.io.*;
import java.net.DatagramPacket;

public class Main {
    public static void main(String[] args) throws IOException {
        new App().go();
    }
}
