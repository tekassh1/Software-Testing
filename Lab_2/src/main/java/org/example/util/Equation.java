package org.example.util;

import static org.example.func.log.Ln.ln;
import static org.example.func.log.Log10.log10;
import static org.example.func.log.Log2.log2;
import static org.example.func.log.Log5.log5;
import static org.example.func.trig.Cos.cos;
import static org.example.func.trig.Cot.cot;
import static org.example.func.trig.Csc.csc;
import static org.example.func.trig.Sec.sec;
import static org.example.func.trig.Sin.sin;
import static org.example.func.trig.Tan.tan;

public class Equation {
    private static Double eps;

    public static double getEps() {
        return eps;
    }

    public static void setEps(double eps) {
        Equation.eps = eps;
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
        if (isFirstFuncDefined(x))
            return (Math.pow((Math.pow((Math.pow((Math.pow(((((sec(x) / sec(x)) - cot(x)) * sin(x)) * (cot(x) - (cos(x) - tan(x)))), 2) * Math.pow(cos(x), 2)), 3) - tan(x)), 3) + ((Math.pow(cot(x), 2) + cot(x)) * ((csc(x) + (tan(x) - csc(x))) + csc(x)))), 2) - sin(x));
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
        if (isSecondFuncDefined(x))
            return (Math.pow(((Math.pow(log2(x), 2) / (log5(x) - ln(x))) * (log5(x) / log10(x))), 2) - (ln(x) / log2(x)));
        return Double.NaN;
    }

    public static double solve(double x) {
        if (x <= 0)
            return solveFirstEq(x);

        return solveSecondEq(x);
    }
}
