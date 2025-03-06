import org.example.Part1.MyCos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestPart1 {
    @Test
    @DisplayName("Table cases")
    void testTableCases() {
        double[] test_vals = {
                0,
                Math.PI / 6,
                Math.PI / 4,
                Math.PI / 3,
                Math.PI / 2,
                Math.PI,
                3*Math.PI/2,
                2*Math.PI
        };
        for (double x: test_vals) {
            assertTrue(Math.abs(MyCos.cos(x) - Math.cos(x)) < 0.1);
        }
    }

    @Test
    @DisplayName("Random values")
    void testRandomValues() {
        int rangeMin = -100;
        int rangeMax = 100;
        ArrayList<Double> test_vals = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Random r = new Random();
            double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            test_vals.add(randomValue);
        }

        for (double x: test_vals) {
            assertTrue(Math.abs(MyCos.cos(x) - Math.cos(x)) < 0.1);
        }
    }

    @Test
    @DisplayName("Corner cases")
    void testCornerCases() {
        double hugeNumber = 1e6;
        double littleNumber = 1e-6;

        assertTrue(Math.abs(MyCos.cos(hugeNumber) - Math.cos(hugeNumber)) < 0.1);
        assertTrue(Math.abs(MyCos.cos(littleNumber) - Math.cos(littleNumber)) < 0.1);

    }

    @Test
    @DisplayName("Overflow case")
    void testOverflowCase() {
        double hugeNumber = Double.MAX_VALUE;
        assertFalse(Math.abs(MyCos.cos(hugeNumber) - Math.cos(hugeNumber)) < 0.1);
    }
}
