
public class App {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("You should use (<node name> <Losses in %> <port> <hostname:port(optional)>)");
            System.exit(1);
        }
        TreeNode node = new TreeNode(args);
        node.action();
    }
}
