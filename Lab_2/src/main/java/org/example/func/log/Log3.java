package org.example.func.log;

import static org.example.func.log.Ln.ln;

public class Log3 {

    public static double log3(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be greater than 0 to compute log3.");
        }
        return ln(x) / ln(3.0);
    }
}