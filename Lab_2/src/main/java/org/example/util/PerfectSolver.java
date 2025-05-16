package org.example.util;

public class PerfectSolver {
    private static Double eps;

    public static double getEps() {
        return eps;
    }

    public static void setEps(double eps) {
        PerfectSolver.eps = eps;
    }

    public static boolean isFirstFuncDefined(double x) {
        if (x > 0) return false;

        double sinX = Math.sin(x);
        double cosX = Math.cos(x);

        double eps = Equation.getEps();
        if (Math.abs(sinX) < eps || Math.abs(cosX) < eps) {
            return false;
        }

        return true;
    }

    public static double solveFirstEq(double x) {
        if (isFirstFuncDefined(x)) {
            double sinX = Math.sin(x);
            double cosX = Math.cos(x);
            double tanX = Math.tan(x);
            double cotX = 1.0 / tanX;
            double secX = 1.0 / cosX;
            double cscX = 1.0 / sinX;

            return (Math.pow((Math.pow((Math.pow((Math.pow(((((secX / secX) - cotX) * sinX) * (cotX - (cosX - tanX))), 2) * Math.pow(cosX, 2)), 3) - tanX), 3) + ((Math.pow(cotX, 2) + cotX) * ((cscX + (tanX - cscX)) + cscX))), 2) - sinX);
        }
        return Double.NaN;
    }

    public static boolean isSecondFuncDefined(double x) {
        if (x <= 0) return false;

        double eps = Equation.getEps();

        if (Math.abs(x - 1.0) < eps) {
            return false;
        }

        double log10X = Math.log10(x);
        if (Math.abs(log10X) < eps) {
            return false;
        }

        return true;
    }

    public static double solveSecondEq(double x) {
        if (isSecondFuncDefined(x)) {
            double lnX = Math.log(x);
            double log2X    =  lnX / Math.log(2);
            double log5X    =  lnX / Math.log(5);
            double log10X   =  lnX / Math.log(10);

            return (Math.pow(((Math.pow(log2X, 2) / (log5X - lnX)) * (log5X / log10X)), 2) - (lnX / log2X));
        }
        return Double.NaN;
    }

    public static double solve(double x) {
        if (x <= 0)
            return solveFirstEq(x);

        return solveSecondEq(x);
    }
}
