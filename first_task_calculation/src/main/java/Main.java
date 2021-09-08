public class Main {
    public static void main(String[] args) {
        Function func = new Function(3, 3, 2, 0.0001);
        RootFinder finder = new RootFinder(func, 0.1, 2);
        double[] roots = finder.getRoots();
        for(int i = 0; i < finder.getCountOfRoots(); ++i) {
            System.out.println(roots[i]);
        }
    }
}
