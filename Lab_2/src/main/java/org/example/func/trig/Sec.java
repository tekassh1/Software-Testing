package org.example.func.trig;

import static org.example.func.trig.Cos.cos;

public class Sec {
    public static double sec(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("Invalid argument: x cannot be NaN or Infinity.");
        }
        double res = cos(x);
        if (res == 0) {
            throw new ArithmeticException("Secant is undefined for x = " + x);
        }
        return 1 / res;
    }
}
