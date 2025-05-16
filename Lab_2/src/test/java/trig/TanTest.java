package trig;

import org.example.func.trig.Cos;
import org.example.func.trig.Sin;
import org.example.func.trig.Tan;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class TanTest {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;
    private static RandomGenerator random;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
        random = RandomGenerator.getDefault();
    }

    @ParameterizedTest
    @DisplayName("Tan table values mocked Sin")
    @CsvFileSource(resources = "table/TableTan.csv", delimiter = ',', useHeadersInDisplayName = true)
        void testTanMockedSinTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected,
            double cos_expected,
            double shifted_sin,
            double shifted_sin_expected) {

                try (MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
                    mockedSin.when(() -> Sin.sin(radian)).thenReturn(sin_expected);
                    mockedSin.when(() -> Sin.sin(shifted_sin)).thenReturn(shifted_sin_expected);

                    assertEquals(expected, Tan.tan(radian), eps_test);
                    mockedSin.verify(() -> Sin.sin(radian));
                    mockedSin.verify(() -> Sin.sin(shifted_sin));
                }
            }

    @ParameterizedTest
    @DisplayName("Tan table values mocked Cos")
    @CsvFileSource(resources = "table/TableTan.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testTanMockedCosTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected,
            double cos_expected,
            double shifted_sin,
            double shifted_sin_expected) {

        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class)) {
            mockedCos.when(() -> Cos.cos(radian)).thenReturn(cos_expected);

            assertEquals(Math.tan(radian), Tan.tan(radian), eps_test);
            mockedCos.verify(() -> Cos.cos(radian));
        }
    }

    @ParameterizedTest
    @DisplayName("Tan table values mocked Sin and Cos")
    @CsvFileSource(resources = "table/TableTan.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testTanMockedSinAndCosTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected,
            double cos_expected,
            double shifted_sin,
            double shifted_sin_expected) {

        try (MockedStatic<Sin> mockedSin = mockStatic(Sin.class); MockedStatic<Cos> mockedCos = mockStatic(Cos.class)) {
            mockedSin.when(() -> Sin.sin(radian)).thenReturn(sin_expected);
            mockedCos.when(() -> Cos.cos(radian)).thenReturn(cos_expected);

            assertEquals(Math.tan(radian), Tan.tan(radian), eps_test);
            mockedSin.verify(() -> Sin.sin(radian));
            mockedCos.verify(() -> Cos.cos(radian));
        }
    }

    @ParameterizedTest
    @DisplayName("Tan pure table")
    @CsvFileSource(resources = "table/TableTan.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testTanPureTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected,
            double cos_expected,
            double shifted_sin,
            double shifted_sin_expected) {

        assertEquals(expected, Tan.tan(radian), eps_test);
    }

    @Test
    @DisplayName("Tan test random value")
    public void testTanRandomVal() {
        double randomValue = 43521;
        double expected = 0.56854523243;

        assertEquals(expected, Tan.tan(randomValue), eps_test);
    }

    @Test
    @DisplayName("Tan test undefined value")
    public void testTanUndefinedValues() {
        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class)) {
            double val = 4321.0;

            mockedCos.when(() -> Cos.cos(val)).thenReturn(0.0);

            assertThrows(ArithmeticException.class, () -> Tan.tan(val));
            mockedCos.verify(() -> Cos.cos(val));
        }
    }
}
