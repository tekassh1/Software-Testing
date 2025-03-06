package org.example.Part1;

import java.math.BigInteger;

public class MyCos {
    public static double cos(double x) {
        x = x % (2 * Math.PI);
        if (x > Math.PI) x -= 2 * Math.PI;
        if (x < -Math.PI) x += 2 * Math.PI;

        double res = 1;
        int sign = -1;
        for (int i = 2; i <= 36; i += 2) {
            res += sign * Math.pow(x, i)/factorial(i).doubleValue();
            sign *= -1;
        }
        return res;
    }

    private static BigInteger factorial(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            res = res.multiply(BigInteger.valueOf(i));
        return res;
    }
}
