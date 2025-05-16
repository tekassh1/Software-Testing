package log;

import org.example.func.log.Ln;
import org.example.func.log.Log2;
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

public class Log5Test {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
    }

    @DisplayName("Log5 not determined (negative)")
    @Test
    public void testLog5NegativeArg() {
        assertThrows(IllegalArgumentException.class, () -> Log5.log5(-1));
    }

    @DisplayName("Log5 not determined (zero)")
    @Test
    public void testLog5ZeroArg() {
        assertThrows(IllegalArgumentException.class, () -> Log5.log5(0));
    }

    @DisplayName("Log5(1) mock")
    @Test
    public void testLog5OneArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(5.0)).thenReturn(1.6094379124341003);
            mockedLn.when(() -> Ln.ln(1.0)).thenReturn(0.0);

            double expected = 0;
            double result = Log5.log5(1);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(5.0));
            mockedLn.verify(() -> Ln.ln(1.0));
        }
    }

    @DisplayName("Log5 negative mock")
    @Test
    public void testLog5NegativeVal() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            mockedLn.when(() -> Ln.ln(5.0)).thenReturn(1.6094379124341003);
            mockedLn.when(() -> Ln.ln(1.0/5.0)).thenReturn(-1.6094379124341003);

            double expected = -1;
            double result = Log5.log5(1.0/5.0);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(5.0));
            mockedLn.verify(() -> Ln.ln(1.0/5.0));
        }
    }

    @DisplayName("Log5 random mock")
    @Test
    public void testLog5RandArg() {
        try (MockedStatic<Ln> mockedLn = mockStatic(Ln.class)) {
            double x = 3214;

            mockedLn.when(() -> Ln.ln(5.0)).thenReturn(1.6094379124341003);
            mockedLn.when(() -> Ln.ln(x)).thenReturn(Math.log(x));

            double expected = 5.01744831777;
            double result = Log5.log5(x);

            assertEquals(expected, result, eps_test);
            mockedLn.verify(() -> Ln.ln(5.0));
            mockedLn.verify(() -> Ln.ln(x));
        }
    }

    @Test
    @DisplayName("property log5(a * b) = log5(a) + log5(b)")
    void testLogarithmProperty() {
        double a = 23;
        double b = 45;

        double expected = 4.3134044827;
        double res_mult = Log5.log5(a * b);
        double res_add = Log5.log5(a) + Log5.log5(b);

        assertEquals(expected, res_mult, eps_test);
        assertEquals(res_mult, res_add, eps_test);
    }

    @DisplayName("Log5 table cases")
    @ParameterizedTest
    @CsvFileSource(resources = "PerfectLog5.csv", delimiter = ';', useHeadersInDisplayName = true)
    public void testLnTableCases(double val, double expected_ln) {
        assertEquals(expected_ln, Log5.log5(val), eps_test);
    }
}