package trig;

import org.example.func.trig.Cos;
import org.example.func.trig.Sin;
import org.example.util.Equation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import java.util.random.RandomGenerator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

public class CosTest {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;
    private static RandomGenerator random;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
        random = RandomGenerator.getDefault();
    }

    @ParameterizedTest
    @DisplayName("Cos check table values")
    @CsvFileSource(resources = "table/TableCos.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testCosTableValuesMock(
            int deg,
            double radian,
            double expected,
            double shifted_sin,
            double shifted_sin_expected) {

        try (MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
            double sinMockedArg = shifted_sin;
            double sinMockedVal = shifted_sin_expected;

            mockedSin.when(() -> Sin.sin(sinMockedArg)).thenReturn(sinMockedVal);

            assertEquals(expected, Cos.cos(radian), eps_test);
            mockedSin.verify(() -> Sin.sin(sinMockedArg));
        }
    }

    @ParameterizedTest
    @DisplayName("Cos pure table")
    @CsvFileSource(resources = "table/TableCos.csv", delimiter = ';', useHeadersInDisplayName = true)
    public void testSinTableValues(int deg, double radian, double expected) {
        assertEquals(expected, Cos.cos(radian), eps_test);
    }

    @Test
    @DisplayName("Cos random val mock")
    void testCosRandomValMock() {
        try (MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
            double val = 957;

            double sinMockedArg = val + Math.PI / 2;
            double sinMockedVal = -0.37559341145;

            mockedSin.when(() -> Sin.sin(sinMockedArg)).thenReturn(sinMockedVal);

            assertEquals(Math.cos(val), Cos.cos(val), eps_test);
            mockedSin.verify(() -> Sin.sin(sinMockedArg));
        }
    }

    @Test
    @DisplayName("Cos check periodicity")
    public void testSinPeriodicity() {
        double num = random.nextDouble(-1000, 1000);
        double cosVal = Math.cos(num);
        double period = 2 * Math.PI;

        for (int i = 1; i < 1000; i++) {
            assertEquals(Math.cos(num + period * i), Cos.cos(num + period * i), eps_test);
            assertEquals(cosVal, Cos.cos(num + period * i), eps_calc);
        }
    }
}