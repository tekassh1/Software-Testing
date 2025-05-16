package trig;

import org.example.func.trig.Csc;
import org.example.func.trig.Sin;
import org.example.util.Equation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.MockedStatic;

import java.util.random.RandomGenerator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CscTest {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;
    private static RandomGenerator random;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
        random = RandomGenerator.getDefault();
    }

    @ParameterizedTest
    @DisplayName("Csc table values mocked Sin")
    @CsvFileSource(resources = "table/TableCsc.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testCscMockedSinTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected) {

        try (MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
            mockedSin.when(() -> Sin.sin(radian)).thenReturn(sin_expected);

            assertEquals(expected, Csc.csc(radian), eps_test);
            mockedSin.verify(() -> Sin.sin(radian));
        }
    }

    @ParameterizedTest
    @DisplayName("Csc pure table")
    @CsvFileSource(resources = "table/TableCsc.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testCscPureTableValues(
            int deg,
            double radian,
            double expected,
            double sin_expected) {

        assertEquals(expected, Csc.csc(radian), eps_test);
    }

    @Test
    @DisplayName("Csc test random value")
    public void testCscRandomVal() {
        double randomValue = 67756;
        double expected = -1.04660805431;

        assertEquals(expected, Csc.csc(randomValue), eps_test);
    }

    @Test
    @DisplayName("Csc test undefined value")
    public void testCscUndefinedValues() {
        try (MockedStatic<Sin> mockedSin = mockStatic(Sin.class)) {
            double val = 4321;

            mockedSin.when(() -> Sin.sin(val)).thenReturn(0.0);

            assertThrows(ArithmeticException.class, () -> Csc.csc(val));
            mockedSin.verify(() -> Sin.sin(val));
        }
    }
}