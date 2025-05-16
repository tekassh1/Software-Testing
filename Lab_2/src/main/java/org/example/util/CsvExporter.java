package org.example.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;

public class CsvExporter {
    public static void main(String[] args) {
        System.out.println("Calculating 1st interval");
        double Int1start = -5.57;
        double Int1end = -5.17;
        double Int1step = 0.001;

        System.out.println("Calculating 2nd interval");
        double Int2start = -2.651722;
        double Int2end = -2.637828;
        double Int2step = 0.00001;

        System.out.println("Calculating 3rd interval");
        double Int3start = -2.578981;
        double Int3end = -2.075771;
        double Int3step = 0.001;

        System.out.println("Calculating 4th interval");
        double Int4start = 0.51212;
        double Int4end = 1.95266;
        double Int4step = 0.001;

        ArrayList<double[]> int1res = getPoints(Int1start, Int1end, Int1step);
        ArrayList<double[]> int2res = getPoints(Int2start, Int2end, Int2step);
        ArrayList<double[]> int3res = getPoints(Int3start, Int3end, Int3step);
        ArrayList<double[]> int4res = getPoints(Int4start, Int4end, Int4step);

        ArrayList<double[]> allPoints = new ArrayList<>();
        allPoints.addAll(int1res);
        allPoints.addAll(int2res);
        allPoints.addAll(int3res);
        allPoints.addAll(int4res);

        try (PrintWriter writer = new PrintWriter(new FileWriter("points.csv"))) {
            writer.println("x,y");

            for (double[] point : allPoints) {
                double x = point[0];
                double y = point[1];
                writer.printf(Locale.US, "%.8f,%.8f%n", x, y);
            }
            System.out.println("Data successfully written to points.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static ArrayList<double[]> getPoints(double start, double end, double step) {
        final double eps_calc = 1e-3;
        Equation.setEps(eps_calc);

        ArrayList<double[]> points = new ArrayList<>();

        for (double x = start; x <= end; x += step) {
            double y = Equation.solve(x);

            points.add(new double[]{x, y});
        }

        return points;
    }
}
