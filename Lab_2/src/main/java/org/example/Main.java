package org.example;

import org.example.util.Equation;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -jar example-jar.jar <epsilon> <x>");
            return;
        }
        Equation.setEps(Double.parseDouble(args[0]));
        for (double x = -10; x <= 10; x += Equation.getEps()) {
            System.out.println("x: " + x + ", y: " + Equation.solve(x));
        }
//        double x = -1.571;
//        System.out.println("x: " + x + ", y: " + Equation.solve(x));
    }
}