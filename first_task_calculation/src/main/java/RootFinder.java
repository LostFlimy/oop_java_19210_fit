public class RootFinder {
    private final Function func;
    private double[] roots;
    private int countOfRoots;
    private final double epsilon;

    public RootFinder(Function curfunc, double eps, double delta) {
        epsilon = eps;
        func = curfunc;

        if(!(func.getMoreOneExtr())) {
            System.out.println("This function have one root!");
            roots = new double[1];
            countOfRoots = 1;
            if(Math.abs(func.getResultForX(0)) < epsilon) {
                roots[0] = 0;
            }
            if(func.getResultForX(0) < -epsilon) {
                roots[0] = findToInf(0, delta);
            }
            if(func.getResultForX(0) > epsilon) {
                roots[0] = findFromInf(0, delta);
            }
        } else {
            if(func.getResultForX(func.getMaxX()) < -epsilon) {
                System.out.println("This function have one root!");
                countOfRoots = 1;
                roots = new double[1];
                roots[0] = findToInf(func.getMinX(), delta);
            }
            if(func.getResultForX(func.getMinX()) > epsilon) {
                System.out.println("This function have one root!");
                countOfRoots = 1;
                roots = new double[1];
                roots[0] = findFromInf(func.getMaxX(), delta);
            }
            if(Math.abs(func.getResultForX(func.getMinX())) < epsilon) {
                if(Math.abs(func.getResultForX(func.getMaxX())) < epsilon) {
                    System.out.println("This function have one root!");
                    countOfRoots = 1;
                    roots = new double[1];
                    roots[0] = (func.getMaxX() + func.getMinX()) / 2;
                }else{
                    System.out.println("This function have two roots!");
                    countOfRoots = 2;
                    roots = new double[2];
                    roots[0] = func.getMinX();
                    roots[1] = findFromInf(func.getMaxX(), delta);
                }
            }
            if(Math.abs(func.getResultForX(func.getMaxX())) < epsilon) {
                if(func.getResultForX(func.getMinX()) < -epsilon) {
                    System.out.println("This function have two roots!");
                    countOfRoots = 2;
                    roots = new double[2];
                    roots[0] = func.getMaxX();
                    roots[1] = findToInf(func.getMinX(), delta);
                }
            }
            if((func.getResultForX(func.getMaxX()) > epsilon) && (func.getResultForX(func.getMinX()) < -epsilon)) {
                System.out.println("This function have three roots!");
                countOfRoots = 3;
                roots = new double[3];
                roots[0] = findToInf(func.getMinX(), delta);
                System.out.println("First root is " + roots[0]);
                roots[1] = findOnInterval(func.getMaxX(), func.getMinX());
                System.out.println("Second root is " + roots[1]);
                roots[2] = findFromInf(func.getMaxX(), delta);
                System.out.println("Third root is " + roots[2]);
            }
        }
    }

    public double[] getRoots() {
        return roots;
    }

    public int getCountOfRoots() {
        return countOfRoots;
    }

    private double findFromInf(double _a, double delta) {
        double curRight = _a;
        double curLeft = _a - delta;

        while(func.getResultForX(curLeft) > epsilon) {
            curRight = curLeft;
            curLeft -= delta;
        }

        if(Math.abs(func.getResultForX(curLeft)) < epsilon) {
            return curLeft;
        }

        return findOnInterval(curLeft, curRight);
    }

    private double findToInf(double _a, double delta) {
        double curLeft = _a;
        double curRight = _a + delta;

        while(func.getResultForX(curRight) < -epsilon) {
            curLeft = curRight;
            curRight += delta;
        }

        if(Math.abs(func.getResultForX(curRight)) < epsilon) {
            return curRight;
        }

        return findOnInterval(curLeft, curRight);
    }

    private double findOnInterval(double _a, double _b) {
        double a = _a;
        double b = _b;

        double curMedium = func.getResultForX((a + b) / 2);

        if(func.getResultForX(a) < -epsilon) {
            while (Math.abs(curMedium) >= epsilon) {
                if (curMedium > epsilon) {
                    b = (a + b) / 2;
                } else {
                    if (curMedium < epsilon) {
                        a = (a + b) / 2;
                    }
                }
                curMedium = func.getResultForX((a + b) / 2);
            }
        }

        if(func.getResultForX(a) > epsilon) {
            while (Math.abs(curMedium) >= epsilon) {
                if (curMedium > epsilon) {
                    a = (a + b) / 2;
                } else {
                    if (curMedium < epsilon) {
                        b = (a + b) / 2;
                    }
                }
                curMedium = func.getResultForX((a + b) / 2);
            }
        }
        return ((a + b) / 2);
    }
}
