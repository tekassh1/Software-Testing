package log;

import org.example.func.log.Ln;
import org.example.func.log.Log10;
import org.example.func.log.Log5;
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

public class Log10Test {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
    }

    @DisplayName("Log10 not determined (negative)")
    @Test
    public void testLog10NegativeArg() {
        assertThrows(IllegalArgumentException.class, () -> Log10.log10(-1));
    }

    @DisplayName("Log10 not determined (zero)")
    @Test
    public void testLog10ZeroArg() {
        assertThrows(IllegalArgumentException.class, () -> Log10.log10(0));
    }

    @DisplayName("Log10(1) mock")
    @Test
    public void testLog10OneArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(10.0)).thenReturn(2.302585092994046);
            mockedLn.when(() -> Ln.ln(1.0)).thenReturn(0.0);

            double expected = 0;
            double result = Log10.log10(1);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(10.0));
            mockedLn.verify(() -> Ln.ln(1.0));
        }
    }

    @DisplayName("Log10 negative mock")
    @Test
    public void testLog10NegativeVal() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(10.0)).thenReturn(2.302585092994046);
            mockedLn.when(() -> Ln.ln(0.1)).thenReturn(-2.302585092994046);

            double expected = -1;
            double result = Log10.log10(0.1);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(10.0));
            mockedLn.verify(() -> Ln.ln(0.1));
        }
    }

    @DisplayName("Log10 random mock")
    @Test
    public void testLog10RandArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            double x = 9987;

            mockedLn.when(() -> Ln.ln(10.0)).thenReturn(2.302585092994046);
            mockedLn.when(() -> Ln.ln(x)).thenReturn(Math.log(x));

            double expected = 3.99943504988;
            double result = Log10.log10(x);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(10.0));
            mockedLn.verify(() -> Ln.ln(x));
        }
    }

    @Test
    @DisplayName("property log10(a * b) = log10(a) + log10(b)")
    void testLogarithmProperty() {
        double a = 23;
        double b = 45;

        double expected = 3.01494;
        double res_mult = Log10.log10(a * b);
        double res_add = Log10.log10(a) + Log10.log10(b);

        assertEquals(expected, res_mult, eps_test);
        assertEquals(res_mult, res_add, eps_test);
    }

    @DisplayName("Log10 table cases")
    @ParameterizedTest
    @CsvFileSource(resources = "PerfectLog10.csv", delimiter = ';', useHeadersInDisplayName = true)
    public void testLnTableCases(double val, double expected_ln) {
        assertEquals(expected_ln, Log10.log10(val), eps_test);
    }
}