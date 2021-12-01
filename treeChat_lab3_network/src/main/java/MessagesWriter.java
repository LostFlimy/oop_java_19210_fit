import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessagesWriter implements Runnable {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public void run() {
        while (TreeNode.running) {
            try {
                String message = reader.readLine();
                System.out.println("You write new message : " + message);
                Long GUID = Double.valueOf(Math.random() * Long.MAX_VALUE).longValue();
                TreeNode.accountingContainer.createMessage(GUID, GUID + ":" + message, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
