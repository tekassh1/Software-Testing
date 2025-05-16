package log;

import org.example.func.log.Ln;
import org.example.util.Equation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LnTest {
    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-6;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
    }

    @DisplayName("Ln not determined (negative)")
    @Test
    public void testLnNegativeArg() {
        assertThrows(IllegalArgumentException.class, () -> Ln.ln(-1));
    }

    @DisplayName("Ln not determined (zero)")
    @Test
    public void testLnZeroArg() {
        assertThrows(IllegalArgumentException.class, () -> Ln.ln(0));
    }

    @DisplayName("Ln(1)")
    @Test
    public void testLnOneArg() {
        assertEquals(0, Ln.ln(1), eps_test);
    }

    @Test
    public void testLnNegativeVal() {
        assertEquals(Math.log(0.5), Ln.ln(0.5), eps_test);
    }

    @Test
    @DisplayName("property ln(a * b) = ln(a) + ln(b)")
    void testLogarithmProperty() {
        double a = 23;
        double b = 45;

        double expected = 6.9421567057;
        double res_mult = Ln.ln(a * b);
        double res_add = Ln.ln(a) + Ln.ln(b);

        assertEquals(expected, res_mult, eps_test);
        assertEquals(res_mult, res_add, eps_test);
    }

    @DisplayName("Ln table cases")
    @ParameterizedTest
    @CsvFileSource(resources = "PerfectLn.csv", delimiter = ';', useHeadersInDisplayName = true)
    public void testLnTableCases(double val, double expected_ln) {
            assertEquals(expected_ln, Ln.ln(val), eps_test);
    }
}