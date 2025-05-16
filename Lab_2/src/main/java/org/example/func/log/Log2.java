package org.example.func.log;

import static org.example.func.log.Ln.ln;

public class Log2 {

    public static double log2(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be greater than 0 to compute log2.");
        }
        return ln(x) / ln(2.0);
    }
}