package trig;

import org.example.func.trig.Cos;
import org.example.func.trig.Cot;
import org.example.func.trig.Sin;
import org.example.func.trig.Tan;
import org.example.util.Equation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.MockedStatic;

import java.util.random.RandomGenerator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CotTest {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;
    private static RandomGenerator random;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
        random = RandomGenerator.getDefault();
    }

    @ParameterizedTest
    @DisplayName("Cot table values mocked Sin")
    @CsvFileSource(resources = "table/TableCot.csv", delimiter = ',', useHeadersInDisplayName = true)
    void testCotMockedSinTableValues(
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

            assertEquals(expected, Cot.cot(radian), eps_test);
            mockedSin.verify(() -> Sin.sin(radian));
            mockedSin.verify(() -> Sin.sin(shifted_sin));
        }
    }

    @ParameterizedTest
    @DisplayName("Cot table values mocked Cos")
    @CsvFileSource(resources = "table/TableCot.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testCotMockedCosTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected,
            double cos_expected,
            double shifted_sin,
            double shifted_sin_expected) {

        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class)) {
            mockedCos.when(() -> Cos.cos(radian)).thenReturn(cos_expected);

            assertEquals(expected, Cot.cot(radian), eps_test);
            mockedCos.verify(() -> Cos.cos(radian));
        }
    }

    @ParameterizedTest
    @DisplayName("Cot table values mocked Sin and Cos")
    @CsvFileSource(resources = "table/TableCot.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testCotMockedSinAndCosTableValues(
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

            assertEquals(expected, Cot.cot(radian), eps_test);
            mockedSin.verify(() -> Sin.sin(radian));
            mockedCos.verify(() -> Cos.cos(radian));
        }
    }

    @ParameterizedTest
    @DisplayName("Cot pure table")
    @CsvFileSource(resources = "table/TableCot.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testCotPureTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected,
            double cos_expected,
            double shifted_sin,
            double shifted_sin_expected) {

        assertEquals(expected, Cot.cot(radian), eps_test);
    }

    @Test
    @DisplayName("Cot test random value")
    public void testCotRandomVal() {
        double randomValue = 4321;
        double expected = 0.26676587651;

        assertEquals(expected, Cot.cot(randomValue), eps_test);
    }

    @Test
    @DisplayName("Cot test undefined value")
    public void testCotUndefinedValues() {
        try (MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
            double val = Math.PI; // tan(PI) = 0 => cot(PI) is undefined

            mockedSin.when(() -> Sin.sin(val)).thenReturn(0.0);

            assertThrows(ArithmeticException.class, () -> Cot.cot(val));
            mockedSin.verify(() -> Sin.sin(val));
        }
    }
}