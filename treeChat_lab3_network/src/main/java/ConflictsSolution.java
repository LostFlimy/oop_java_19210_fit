
public class ConflictsSolution implements Runnable{
    private final TreeNode treeNode;

    public ConflictsSolution(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public void run() {
        System.out.println("Conflict Solution started...");
        while (TreeNode.running) {
            if (TreeNode.brokensNeighbor != null) {
                System.out.println("Start to solute conflict...");
                String reserv = TreeNode.reservNeighbor;
                int pos = reserv.indexOf(":");
                Integer port = Integer.parseInt(reserv.substring(pos + 1));
                String address = reserv.substring(0, pos);
                if(!(treeNode.getInetAdress().equals(address))) {
                    treeNode.submit(address, port);
                    if (TreeNode.brokensNeighbor.equals(TreeNode.neighbor))
                        TreeNode.neighbor = reserv;
                }
            }
        }
    }
}
