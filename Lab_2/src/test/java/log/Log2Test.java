package log;

import org.example.func.log.Ln;
import org.example.func.log.Log2;
import org.example.util.Equation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.MockedStatic;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class Log2Test {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
    }

    @DisplayName("Log2 not determined (negative)")
    @Test
    public void testLog2NegativeArg() {
        assertThrows(IllegalArgumentException.class, () -> Log2.log2(-1));
    }

    @DisplayName("Log2 not determined (zero)")
    @Test
    public void testLog2ZeroArg() {
        assertThrows(IllegalArgumentException.class, () -> Log2.log2(0));
    }

    @DisplayName("Log2(1) mock")
    @Test
    public void testLog2OneArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(2.0)).thenReturn(0.6931471805599453);
            mockedLn.when(() -> Ln.ln(1.0)).thenReturn(0.0);

            double expected = 0;
            double result = Log2.log2(1);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(2.0));
            mockedLn.verify(() -> Ln.ln(1.0));
        }
    }

    @DisplayName("Log2 negative mock")
    @Test
    public void testLog2NegativeVal() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(2.0)).thenReturn(0.6931471805599453);
            mockedLn.when(() -> Ln.ln(0.5)).thenReturn(-0.69314718056);

            double expected = -1;
            double result = Log2.log2(0.5);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(2.0));
            mockedLn.verify(() -> Ln.ln(0.5));
        }
    }

    @DisplayName("Log2 random mock")
    @Test
    public void testLog2RandArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            double x = 1026;

            mockedLn.when(() -> Ln.ln(2.0)).thenReturn(0.69314718056);
            mockedLn.when(() -> Ln.ln(x)).thenReturn(Math.log(x));

            double expected = 10.0028150156;
            double result = Log2.log2(x);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(2.0));
            mockedLn.verify(() -> Ln.ln(x));
        }
    }

    @Test
    @DisplayName("property log2(a * b) = log2(a) + log2(b)")
    void testLogarithmProperty() {
        double a = 23;
        double b = 45;

        double expected = 10.0154150524;
        double res_mult = Log2.log2(a * b);
        double res_add = Log2.log2(a) + Log2.log2(b);

        assertEquals(expected, res_mult, eps_test);
        assertEquals(res_mult, res_add, eps_test);
    }

    @DisplayName("Log2 table cases")
    @ParameterizedTest
    @CsvFileSource(resources = "PerfectLog2.csv", delimiter = ';', useHeadersInDisplayName = true)
    public void testLnTableCases(double val, double expected_ln) {
        assertEquals(expected_ln, Log2.log2(val), eps_test);
    }
}