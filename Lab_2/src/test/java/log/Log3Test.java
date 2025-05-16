package log;

import org.example.func.log.Ln;
import org.example.func.log.Log2;
import org.example.func.log.Log3;
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

public class Log3Test {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
    }

    @DisplayName("Log3 not determined (negative)")
    @Test
    public void testLog3NegativeArg() {
        assertThrows(IllegalArgumentException.class, () -> Log3.log3(-1));
    }

    @DisplayName("Log3 not determined (zero)")
    @Test
    public void testLog3ZeroArg() {
        assertThrows(IllegalArgumentException.class, () -> Log3.log3(0));
    }

    @DisplayName("Log3(1) mock")
    @Test
    public void testLog3OneArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(3.0)).thenReturn(1.0986122886681098);
            mockedLn.when(() -> Ln.ln(1.0)).thenReturn(0.0);

            double expected = 0;
            double result = Log3.log3(1);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(3.0));
            mockedLn.verify(() -> Ln.ln(1.0));
        }
    }

    @DisplayName("Log3 fraction mock")
    @Test
    public void testLog3NegativeVal() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(3.0)).thenReturn(1.0986122886681098);
            mockedLn.when(() -> Ln.ln(1.0/3.0)).thenReturn(-1.0986122886681098);

            double expected = -1;
            double result = Log3.log3(1.0/3.0);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(3.0));
            mockedLn.verify(() -> Ln.ln(1.0/3.0));
        }
    }

    @DisplayName("Log3 random mock")
    @Test
    public void testLog3RandArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            double x = 77777;

            mockedLn.when(() -> Ln.ln(3.0)).thenReturn(1.0986122886681098);
            mockedLn.when(() -> Ln.ln(x)).thenReturn(Math.log(x));

            double expected = 10.2507510182;
            double result = Log3.log3(x);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(3.0));
            mockedLn.verify(() -> Ln.ln(x));
        }
    }

    @Test
    @DisplayName("property log3(a * b) = log3(a) + log3(b)")
    void testLogarithmProperty() {
        double a = 23;
        double b = 45;

        double expected = 6.3190233517;
        double res_mult = Log3.log3(a * b);
        double res_add = Log3.log3(a) + Log3.log3(b);

        assertEquals(expected, res_mult, eps_test);
        assertEquals(res_mult, res_add, eps_test);
    }

    @DisplayName("Log3 table cases")
    @ParameterizedTest
    @CsvFileSource(resources = "PerfectLog3.csv", delimiter = ';', useHeadersInDisplayName = true)
    public void testLnTableCases(double val, double expected_ln) {
        assertEquals(expected_ln, Log3.log3(val), eps_test);
    }
}
