// java Main.java {a} {b} {h}

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        System.out.println(Math.exp(2.5) * (Math.cos(2.5) + Math.sin(2.5)) / 2);
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double h = Double.parseDouble(args[2]);
        int k = Integer.parseInt(args[3]);
        if(k != 4) {
            int j = 0;
            double prevY = 0.0;
            double prevX = 0.0;
            while (j * h < a) {
                if(j != 0) {
                    prevY = formResult(j * h, prevY, new double[]{prevX}, h, k);
                    prevX = j * h;
                }
                j++;
            }
            while (j * h <= b) {
                if (j > 0) {
                    prevY = formResult(j * h, prevY, new double[]{prevX}, h, k);
                    prevX = j * h;
                }
                System.out.printf(
                        "%d  %f  %f  %f  %f%n",
                        j,
                        h*j,
                        prevY,
                        y(j*h),
                        Math.abs(y(j*h) - prevY)
                );
                j++;
            }
        }

        if (k == 4) {
            int j = 0;
            LinkedList<Double> y = new LinkedList<>();
            y.addLast(0.0);
            y.addLast(h * (g(h) + g(0)) / 2);

            while (j * h < a) {
                if (j > 1) {
                    form4Result(y, j, h);
                }
                j++;
            }

            while (j * h <= b) {
                if (j > 1) {
                    form4Result(y, j, h);
                }
                System.out.printf(
                        "%d  %f  %f  %f  %23.16f%n",
                        j,
                        j * h,
                        y.get(j),
                        y(j*h),
                        Math.abs(y.get(j) - y(j * h))
                );
                j++;
            }
        }
    }


    public static double g(double x) {
        return Math.cos(x) * Math.exp(x);
    }

    public static  double y(double x) {
        return Math.exp(x) * (Math.cos(x) + Math.sin(x)) / 2 - 0.5;
    }

    public static double formResult(double x, double prevY, double[] prevX, double h, int k) {
        switch (k) {
            case 1 : {
                return (h * g(prevX[0]) + prevY);
            }

            case 2 : {
                return (h * (g(x) + g(prevX[0])) / 2 + prevY);
            }
        }
        throw new RuntimeException("Invalid k");
    }

    public static void form4Result(LinkedList<Double> prevY, int j, double h) {
        prevY.addLast(prevY.get(j - 2) + (g(j*h) + 4 * g((j-1)*h) + g((j-2) * h)) * h / 3);
    }
}
