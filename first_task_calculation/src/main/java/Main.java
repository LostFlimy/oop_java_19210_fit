public class Main {
    public static void main(String[] args) {
        Function func = new Function(-2.98, -9.08, -5.1, 0.1);
        RootFinder finder = new RootFinder(func, 0.01, 2);
        double[] roots = finder.getRoots();
        int[] countRoot = finder.getCountRoot();
        for(int i = 0; i < finder.getCountOfRoots(); ++i) {
            System.out.println(roots[i] + " кратности " + countRoot[i]);
        }
    }
}
