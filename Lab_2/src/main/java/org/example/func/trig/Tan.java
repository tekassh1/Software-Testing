package org.example.func.trig;

import static org.example.func.trig.Cos.cos;
import static org.example.func.trig.Sin.sin;

public class Tan {
    public static double tan(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("Invalid argument: x cannot be NaN or Infinity.");
        }
        double res = cos(x);
        if (res == 0) {
            throw new ArithmeticException("Tan is undefined for x = " + x);
        }
        return sin(x) / res;
    }
}