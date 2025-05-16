package trig;

import org.example.func.trig.Sin;
import org.example.util.Equation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.random.RandomGenerator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;
    private static RandomGenerator random;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
        random = RandomGenerator.getDefault();
    }

    @ParameterizedTest
    @DisplayName("Sin pure table")
    @CsvFileSource(resources = "table/TableSin.csv", delimiter = ';', useHeadersInDisplayName = true)
    public void testSinTableValues(int deg, double radian, double expected) {
            assertEquals(expected, Sin.sin(radian), eps_test);
    }

    @Test
    @DisplayName("Sin check periodicity")
    public void testSinPeriodicity() {
        double num = random.nextDouble(-1000, 1000);;
        double sinVal = Math.sin(num);
        double period = 2 * Math.PI;

        for (int i = 1; i < 1000; i++) {
            assertEquals(Math.sin(num + period * i), Sin.sin(num + period * i), eps_test);
            assertEquals(sinVal, Sin.sin(num + period * i), eps_calc);
        }
    }

    @Test
    @DisplayName("Sin check random big")
    public void testSinRandomBig() {
        for (int i = 1; i < 10000; i++) {
            double val = random.nextDouble(-1000, 1000);
            assertEquals(Math.sin(val), Sin.sin(val), eps_calc);
        }
    }
}