public class Main {
    public static void main(String[] args) {
        Function func = new Function(-3, 3, -1, 0.1);
        RootFinder finder = new RootFinder(func, 0.1, 2);
        double[] roots = finder.getRoots();
        int[] countRoot = finder.getCountRoot();
        for(int i = 0; i < finder.getCountOfRoots(); ++i) {
            System.out.println(roots[i] + " кратности " + countRoot[i]);
        }
    }
}
