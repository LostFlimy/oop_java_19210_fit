import java.math.*;


//f(x) = x^3 + ax^2 + bx + c
//f'(x) = 3x^2 + 2ax + b
public class Function {
    private double a;
    private double b;
    private double c;
    private double minX;
    private double maxX;
    private double D;
    private double epsilonD;
    private boolean moreOneExtr;

    public Function(double a, double b, double c, double epsilonD) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.epsilonD = epsilonD;

        D = 4 * a * a - 12 * b;
        if(D < epsilonD) {
            moreOneExtr = false;
        }
        else {
            moreOneExtr = true;
        }

        if(moreOneExtr) {
            minX = (-2 * a + Math.sqrt(D)) / 6;
            maxX = (-2 * a - Math.sqrt(D)) / 6;
        }
        else {
            minX = 0;
            maxX = 0;
        }
    }

    public boolean getMoreOneExtr() {
        return moreOneExtr;
    }

    public double getMinX(){
        return minX;
    }

    public double getMaxX(){
        return maxX;
    }

    public double getResultForX(double x) {
        return (Math.pow(x,3) + a * Math.pow(x,2) + b * x + c);
    }
}
