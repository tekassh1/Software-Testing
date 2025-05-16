package trig;

import org.example.func.trig.Cos;
import org.example.func.trig.Sec;
import org.example.util.Equation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;

import java.util.random.RandomGenerator;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecTest {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;
    private static RandomGenerator random;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
        random = RandomGenerator.getDefault();
    }

    @ParameterizedTest
    @DisplayName("Sec table values mocked Cos")
    @CsvFileSource(resources = "table/TableSec.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testSecMockedCosTableValues(
            int deg,
            double radian,
            double expected,
            double cos_expected) {

        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class)) {
            mockedCos.when(() -> Cos.cos(radian)).thenReturn(cos_expected);

            assertEquals(expected, Sec.sec(radian), eps_test);
            mockedCos.verify(() -> Cos.cos(radian));
        }
    }

    @ParameterizedTest
    @DisplayName("Sec pure table")
    @CsvFileSource(resources = "table/TableSec.csv", delimiter = ',', useHeadersInDisplayName = true)
    public void testSecPureTableValues(
            int deg,
            double radian,
            double expected,
            double cos_expected) {

        assertEquals(expected, Sec.sec(radian), eps_test);
    }

    @Test
    @DisplayName("Sec test random value")
    public void testSecRandomVal() {
        double randomValue = 67756;
        double expected = -3.38872437432;

        assertEquals(expected, Sec.sec(randomValue), eps_test);
    }

    @Test
    @DisplayName("Sec test undefined value")
    public void testSecUndefinedValues() {
        try (MockedStatic<Cos> mockedCos = mockStatic(Cos.class)) {
            double val = 4321;

            mockedCos.when(() -> Cos.cos(val)).thenReturn(0.0);

            assertThrows(ArithmeticException.class, () -> Sec.sec(val));
            mockedCos.verify(() -> Cos.cos(val));
        }
    }
}