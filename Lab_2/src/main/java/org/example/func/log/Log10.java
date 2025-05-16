package org.example.func.log;

import static org.example.func.log.Ln.ln;

public class Log10 {
    public static double log10(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be greater than 0 to compute log10.");
        }
        return ln(x) / ln(10.0);
    }
}