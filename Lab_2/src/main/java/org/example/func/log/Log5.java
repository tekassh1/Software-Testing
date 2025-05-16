package org.example.func.log;

import static org.example.func.log.Ln.ln;

public class Log5 {

    public static double log5(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be greater than 0 to compute log5.");
        }
        return ln(x) / ln(5.0);
    }
}