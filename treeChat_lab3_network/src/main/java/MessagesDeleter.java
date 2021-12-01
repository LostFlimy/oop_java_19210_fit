
public class MessagesDeleter implements Runnable{
    @Override
    public void run() {
        while (TreeNode.running) {
            TreeNode.accountingContainer.deleteMessages();
        }
    }
}
