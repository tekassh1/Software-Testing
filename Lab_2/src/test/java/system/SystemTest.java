package system;

import org.example.func.log.*;
import org.example.func.trig.*;
import org.example.util.Equation;
import org.example.util.PerfectSolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SystemTest {

    private static final double eps_calc = 1e-10;
    private static final double eps_test = 1e-5;
    private static RandomGenerator random;

    @BeforeAll
    public static void setUp() {
        Equation.setEps(eps_calc);
        PerfectSolver.setEps(eps_calc);

        random = RandomGenerator.getDefault();
    }

    // First equation

    @ParameterizedTest
    @CsvFileSource(resources = "values/FirstEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testFirstEquationWithAllMocked(
            double x,
            double sin,
            double cos,
            double tan, 
            double cot, 
            double sec, 
            double csc,
            double expected
    ) {
        try (MockedStatic<Sin> sinMock = mockStatic(Sin.class);
             MockedStatic<Cos> cosMock = mockStatic(Cos.class);
             MockedStatic<Tan> tanMock = mockStatic(Tan.class);
             MockedStatic<Cot> cotMock = mockStatic(Cot.class);
             MockedStatic<Sec> secMock = mockStatic(Sec.class);
             MockedStatic<Csc> cscMock = mockStatic(Csc.class))
        {
            sinMock.when(() -> Sin.sin(x)).thenReturn(sin);
            cosMock.when(() -> Cos.cos(x)).thenReturn(cos);
            tanMock.when(() -> Tan.tan(x)).thenReturn(tan);
            cotMock.when(() -> Cot.cot(x)).thenReturn(cot);
            secMock.when(() -> Sec.sec(x)).thenReturn(sec);
            cscMock.when(() -> Csc.csc(x)).thenReturn(csc);

            double result = Equation.solveFirstEq(x);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);
            assertTrue(relativeError < eps_test);

            sinMock.verify(() -> Sin.sin(x), atLeastOnce());
            cosMock.verify(() -> Cos.cos(x), atLeastOnce());
            cscMock.verify(() -> Csc.csc(x), atLeastOnce());
            secMock.verify(() -> Sec.sec(x), atLeastOnce());
            tanMock.verify(() -> Tan.tan(x), atLeastOnce());
            cotMock.verify(() -> Cot.cot(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/FirstEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testFirstEquationWithCsc(
            double x,
            double sin,
            double cos,
            double tan,
            double cot,
            double sec,
            double csc,
            double expected
    ) {
        try (MockedStatic<Sin> sinMock = mockStatic(Sin.class);
             MockedStatic<Cos> cosMock = mockStatic(Cos.class);
             MockedStatic<Tan> tanMock = mockStatic(Tan.class);
             MockedStatic<Cot> cotMock = mockStatic(Cot.class);
             MockedStatic<Sec> secMock = mockStatic(Sec.class))
        {
            sinMock.when(() -> Sin.sin(x)).thenReturn(sin);
            cosMock.when(() -> Cos.cos(x)).thenReturn(cos);
            tanMock.when(() -> Tan.tan(x)).thenReturn(tan);
            cotMock.when(() -> Cot.cot(x)).thenReturn(cot);
            secMock.when(() -> Sec.sec(x)).thenReturn(sec);

            double result = Equation.solveFirstEq(x);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);
            assertTrue(relativeError < eps_test);

            sinMock.verify(() -> Sin.sin(x), atLeastOnce());
            cosMock.verify(() -> Cos.cos(x), atLeastOnce());
            secMock.verify(() -> Sec.sec(x), atLeastOnce());
            tanMock.verify(() -> Tan.tan(x), atLeastOnce());
            cotMock.verify(() -> Cot.cot(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/FirstEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testFirstEquationWithSec(
            double x,
            double sin,
            double cos,
            double tan,
            double cot,
            double sec,
            double csc,
            double expected
    ) {
        try (MockedStatic<Sin> sinMock = mockStatic(Sin.class);
             MockedStatic<Cos> cosMock = mockStatic(Cos.class);
             MockedStatic<Tan> tanMock = mockStatic(Tan.class);
             MockedStatic<Cot> cotMock = mockStatic(Cot.class))
        {
            sinMock.when(() -> Sin.sin(x)).thenReturn(sin);
            cosMock.when(() -> Cos.cos(x)).thenReturn(cos);
            tanMock.when(() -> Tan.tan(x)).thenReturn(tan);
            cotMock.when(() -> Cot.cot(x)).thenReturn(cot);

            double result = Equation.solveFirstEq(x);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);
            assertTrue(relativeError < eps_test);

            sinMock.verify(() -> Sin.sin(x), atLeastOnce());
            cosMock.verify(() -> Cos.cos(x), atLeastOnce());
            tanMock.verify(() -> Tan.tan(x), atLeastOnce());
            cotMock.verify(() -> Cot.cot(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/FirstEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testFirstEquationWithCot(
            double x,
            double sin,
            double cos,
            double tan,
            double cot,
            double sec,
            double csc,
            double expected
    ) {
        try (MockedStatic<Sin> sinMock = mockStatic(Sin.class);
             MockedStatic<Cos> cosMock = mockStatic(Cos.class);
             MockedStatic<Tan> tanMock = mockStatic(Tan.class))
        {
            sinMock.when(() -> Sin.sin(x)).thenReturn(sin);
            cosMock.when(() -> Cos.cos(x)).thenReturn(cos);
            tanMock.when(() -> Tan.tan(x)).thenReturn(tan);

            double result = Equation.solveFirstEq(x);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);
            assertTrue(relativeError < eps_test);

            sinMock.verify(() -> Sin.sin(x), atLeastOnce());
            cosMock.verify(() -> Cos.cos(x), atLeastOnce());
            tanMock.verify(() -> Tan.tan(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/FirstEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testFirstEquationWithTan(
            double x,
            double sin,
            double cos,
            double tan,
            double cot,
            double sec,
            double csc,
            double expected
    ) {
        try (MockedStatic<Sin> sinMock = mockStatic(Sin.class);
             MockedStatic<Cos> cosMock = mockStatic(Cos.class))
        {
            sinMock.when(() -> Sin.sin(x)).thenReturn(sin);
            cosMock.when(() -> Cos.cos(x)).thenReturn(cos);

            double result = Equation.solveFirstEq(x);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);
            assertTrue(relativeError < eps_test);

            sinMock.verify(() -> Sin.sin(x), atLeastOnce());
            cosMock.verify(() -> Cos.cos(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/FirstEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testFirstEquationWithAllStubs(
            double x,
            double sin,
            double cos,
            double tan,
            double cot,
            double sec,
            double csc,
            double expected
    ) {
        try (MockedStatic<Sin> sinMock = mockStatic(Sin.class))
        {
            sinMock.when(() -> Sin.sin(x)).thenReturn(sin);
            sinMock.when(() -> Sin.sin(x + Math.PI / 2)).thenReturn(Math.sin(x + Math.PI / 2));

            double result = Equation.solveFirstEq(x);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);
            assertTrue(relativeError < eps_test);

            sinMock.verify(() -> Sin.sin(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/FirstEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testFirstEquationWithAllFunc(
            double x,
            double sin,
            double cos,
            double tan,
            double cot,
            double sec,
            double csc,
            double expected
    ) {
        double result = Equation.solveFirstEq(x);
        double relativeError = Math.abs(expected - result) / Math.abs(expected);
        assertTrue(relativeError < eps_test);
    }

    @Test
    @DisplayName("Test first equation periodicity")
    public void testFirstEquationPeriodicity() {
        double num = random.nextDouble(-100, -eps_test);
        double expected = Equation.solveFirstEq(num);

        for (int i = 0; i <= 1000; i++) {
            double result = Equation.solveFirstEq(num - Math.PI * 2 * i);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);

            assertTrue(relativeError < eps_test);
        }
    }

    // Second equation

    @ParameterizedTest
    @CsvFileSource(resources = "values/SecondEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testSecondEquationWithAllStubs(
            double x,
            double ln,
            double log2,
            double log3,
            double log5,
            double log10,
            double expected)
    {
        try (MockedStatic<Ln>       lnMock      =  mockStatic(Ln.class);
             MockedStatic<Log2>     log2Mock    =  mockStatic(Log2.class);
             MockedStatic<Log3>     log3Mock    =  mockStatic(Log3.class);
             MockedStatic<Log5>     log5Mock    =  mockStatic(Log5.class);
             MockedStatic<Log10>    log10Mock   = mockStatic(Log10.class))
        {
            lnMock.when(()      ->  Ln.ln(x)).thenReturn(ln);
            log2Mock.when(()    ->  Log2.log2(x)).thenReturn(log2);
            log3Mock.when(()    ->  Log3.log3(x)).thenReturn(log3);
            log5Mock.when(()    ->  Log5.log5(x)).thenReturn(log5);
            log10Mock.when(()   ->  Log10.log10(x)).thenReturn(log10);

            double result = Equation.solveSecondEq(x);
            assertEquals(expected, result, eps_test);

            lnMock.verify(()    -> Ln.ln(x),        atLeastOnce());
            log2Mock.verify(()  -> Log2.log2(x),    atLeastOnce());
            log5Mock.verify(()  -> Log5.log5(x),    atLeastOnce());
            log10Mock.verify(() -> Log10.log10(x),  atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/SecondEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testSecondEquationWithLog10(
            double x,
            double ln,
            double log2,
            double log3,
            double log5,
            double log10,
            double expected)
    {
        try (MockedStatic<Ln>   lnMock   = mockStatic(Ln.class);
             MockedStatic<Log2> log2Mock = mockStatic(Log2.class);
             MockedStatic<Log3> log3Mock = mockStatic(Log3.class);
             MockedStatic<Log5> log5Mock = mockStatic(Log5.class))
        {
            lnMock.when(()      -> Ln.ln(x)).thenReturn(ln);
            lnMock.when(()      -> Ln.ln(10)).thenReturn(2.30258509299);

            log2Mock.when(()    -> Log2.log2(x)).thenReturn(log2);
            log3Mock.when(()    -> Log3.log3(x)).thenReturn(log3);
            log5Mock.when(()    -> Log5.log5(x)).thenReturn(log5);

            double result = Equation.solveSecondEq(x);
            assertEquals(expected, result, eps_test);

            lnMock.verify(() -> Ln.ln(x), atLeastOnce());
            log2Mock.verify(() -> Log2.log2(x), atLeastOnce());
            log5Mock.verify(() -> Log5.log5(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/SecondEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testSecondEquationWithLog5(
            double x,
            double ln,
            double log2,
            double log3,
            double log5,
            double log10,
            double expected)
    {
        try (MockedStatic<Ln>   lnMock   = mockStatic(Ln.class);
             MockedStatic<Log2> log2Mock = mockStatic(Log2.class);
             MockedStatic<Log3> log3Mock = mockStatic(Log3.class))
        {
            lnMock.when(()      -> Ln.ln(x)).thenReturn(ln);
            lnMock.when(()      -> Ln.ln(5)).thenReturn(1.60943791243);
            lnMock.when(()      -> Ln.ln(10)).thenReturn(2.30258509299);

            log2Mock.when(()    -> Log2.log2(x)).thenReturn(log2);
            log3Mock.when(()    -> Log3.log3(x)).thenReturn(log3);

            double result = Equation.solveSecondEq(x);
            assertEquals(expected, result, eps_test);

            lnMock.verify(() -> Ln.ln(x), atLeastOnce());
            log2Mock.verify(() -> Log2.log2(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/SecondEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testSecondEquationWithLog3(
            double x,
            double ln,
            double log2,
            double log3,
            double log5,
            double log10,
            double expected)
    {
        try (MockedStatic<Ln>   lnMock   = mockStatic(Ln.class);
             MockedStatic<Log2> log2Mock = mockStatic(Log2.class))
        {
            lnMock.when(()      -> Ln.ln(x)).thenReturn(ln);
            lnMock.when(()      -> Ln.ln(3)).thenReturn(1.09861228867);
            lnMock.when(()      -> Ln.ln(5)).thenReturn(1.60943791243);
            lnMock.when(()      -> Ln.ln(10)).thenReturn(2.30258509299);

            log2Mock.when(()    -> Log2.log2(x)).thenReturn(log2);

            double result = Equation.solveSecondEq(x);
            assertEquals(expected, result, eps_test);

            lnMock.verify(() -> Ln.ln(x), atLeastOnce());
            log2Mock.verify(() -> Log2.log2(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/SecondEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testSecondEquationWithLog2(
            double x,
            double ln,
            double log2,
            double log3,
            double log5,
            double log10,
            double expected)
    {
        try (MockedStatic<Ln>   lnMock   = mockStatic(Ln.class))
        {
            lnMock.when(()      -> Ln.ln(x)).thenReturn(ln);
            lnMock.when(()      -> Ln.ln(2)).thenReturn(0.69314718056);
            lnMock.when(()      -> Ln.ln(3)).thenReturn(1.09861228867);
            lnMock.when(()      -> Ln.ln(5)).thenReturn(1.60943791243);
            lnMock.when(()      -> Ln.ln(10)).thenReturn(2.30258509299);

            double result = Equation.solveSecondEq(x);
            assertEquals(expected, result, eps_test);

            lnMock.verify(() -> Ln.ln(x), atLeastOnce());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "values/SecondEquationMock.csv", delimiter = ';', useHeadersInDisplayName = true)
    void testSecondEquationWithPureVals(
            double x,
            double ln,
            double log2,
            double log3,
            double log5,
            double log10,
            double expected)
    {
        assertEquals(expected, Equation.solveSecondEq(x), eps_test);
    }

    @Test
    @DisplayName("Test second undef")
    void testSecondUndef() {
        double x = 1;
        assertEquals(NaN, Equation.solveSecondEq(x), eps_test);
    }

    // Complete system test

    @Test
    @DisplayName("Test system random large")
    void testRandomLarge() {
        for (int i = 0; i < 1000; i++) {
            try {
                double num = random.nextDouble(-100, 100);
                double expected = PerfectSolver.solve(num);

                double result = Equation.solve(num);
                double relativeError = Math.abs(expected - result) / Math.abs(expected);

                assertTrue(relativeError < eps_test);
            }
            catch (Exception e) {
                continue;
            }
        }
    }

//  x <= 0: x != -pi/2 + pi*n, x != pi*n
//  x > 0:  x != 1

    @Test
    @DisplayName("Test system undefined: sec(x) == 0")
    void testSystemUndefinedCos() {
        try (MockedStatic<Sec> secMock = mockStatic(Sec.class);
             MockedStatic<Cos> cosMock = mockStatic(Cos.class))
        {
            secMock.when(() -> Sec.sec(anyDouble())).thenReturn(0.0);
            secMock.when(() -> Cos.cos(anyDouble())).thenReturn(0.0);

            assertThrows(Exception.class, () -> Equation.solve(random.nextDouble(-100, -eps_test)));

            secMock.verify(() -> Sec.sec(anyDouble()), atLeastOnce());
        }
    }

    @Test
    @DisplayName("Test system throws sec/cos == 0")
    void testSystemUndefinedSecCosZero() {
        try (MockedStatic<Sec> secMock = mockStatic(Sec.class);
             MockedStatic<Cos> cosMock = mockStatic(Cos.class))
        {
            secMock.when(() -> Sec.sec(anyDouble())).thenReturn(0.0);
            secMock.when(() -> Cos.cos(anyDouble())).thenReturn(0.0);

            assertThrows(Exception.class, () -> Equation.solve(random.nextDouble(-100, -eps_test)));

            secMock.verify(() -> Sec.sec(anyDouble()), atLeastOnce());
        }
    }

    @Test
    @DisplayName("Test system undefined x == 1")
    void testSystemUndefinedOne() {
            assertEquals(Equation.solve(1), NaN);
    }

    @Test
    @DisplayName("Inflection point 1")
    void testSystemInflectionPoint1() {
        double point = -2.31963;
        double expected = Equation.solve(point);

        for (int i = 0; i < 4; i++) {
            double result = Equation.solve(point - Math.PI * 2 * i);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 2")
    void testSystemInflectionPoint2() {
        double point = -2.64727;
        double expected = Equation.solve(point);

        for (int i = 0; i < 4; i++) {
            double result = Equation.solve(point - Math.PI * 2 * i);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 3")
    void testSystemInflectionPoint3() {
        double point = -5.31356;
        double expected = Equation.solve(point);

        for (int i = 0; i < 4; i++) {
            double result = Equation.solve(point - Math.PI * 2 * i);
            double relativeError = Math.abs(expected - result) / Math.abs(expected);

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 1 equivalence right")
    void testSystemInflectionPoint1EquivalenceRight() {
        double intervalStart = -2.31963;
        double intervalEnd = intervalStart + 0.2;

        ArrayList<Double> origVals = new ArrayList<>();
        for (double i = intervalStart; i <= intervalEnd; i += 0.001) {
            origVals.add(Equation.solve(i));
        }

        ArrayList<Double> eqVals = new ArrayList<>();
        double equivalentStart = intervalStart - 2 * Math.PI;
        double equivalentEnd = equivalentStart + 0.2;
        for (double i = equivalentStart; i <= equivalentEnd; i += 0.001) {
            eqVals.add(Equation.solve(i));
        }

        for (int i = 0; i < eqVals.size(); i++) {
            double relativeError = Math.abs(origVals.get(i) - eqVals.get(i)) / Math.abs(origVals.get(i));

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 1 equivalence left")
    void testSystemInflectionPoint1EquivalenceLeft() {
        double intervalStart = -2.31963 - 0.2;
        double intervalEnd = -2.31963;

        ArrayList<Double> origVals = new ArrayList<>();
        for (double i = intervalStart; i <= intervalEnd; i += 0.001) {
            origVals.add(Equation.solve(i));
        }

        ArrayList<Double> eqVals = new ArrayList<>();
        double equivalentStart = intervalStart - 2 * Math.PI;
        double equivalentEnd = equivalentStart + 0.2;
        for (double i = equivalentStart; i <= equivalentEnd; i += 0.001) {
            eqVals.add(Equation.solve(i));
        }

        for (int i = 0; i < eqVals.size(); i++) {
            double relativeError = Math.abs(origVals.get(i) - eqVals.get(i)) / Math.abs(origVals.get(i));

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 2 equivalence right")
    void testSystemInflectionPoint2EquivalenceRight() {
        double intervalStart = -2.64727;
        double intervalEnd = intervalStart + 0.01;

        ArrayList<Double> origVals = new ArrayList<>();
        for (double i = intervalStart; i <= intervalEnd; i += 0.001) {
            origVals.add(Equation.solve(i));
        }

        ArrayList<Double> eqVals = new ArrayList<>();
        double equivalentStart = intervalStart - 2 * Math.PI;
        double equivalentEnd = equivalentStart + 0.01;
        for (double i = equivalentStart; i <= equivalentEnd; i += 0.001) {
            eqVals.add(Equation.solve(i));
        }

        for (int i = 0; i < eqVals.size(); i++) {
            double relativeError = Math.abs(origVals.get(i) - eqVals.get(i)) / Math.abs(origVals.get(i));

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 2 equivalence left")
    void testSystemInflectionPoint2EquivalenceLeft() {
        double intervalStart = -2.64727 - 0.01;
        double intervalEnd = -2.64727;

        ArrayList<Double> origVals = new ArrayList<>();
        for (double i = intervalStart; i <= intervalEnd; i += 0.001) {
            origVals.add(Equation.solve(i));
        }

        ArrayList<Double> eqVals = new ArrayList<>();
        double equivalentStart = intervalStart - 2 * Math.PI;
        double equivalentEnd = equivalentStart + 0.01;
        for (double i = equivalentStart; i <= equivalentEnd; i += 0.001) {
            eqVals.add(Equation.solve(i));
        }

        for (int i = 0; i < eqVals.size(); i++) {
            double relativeError = Math.abs(origVals.get(i) - eqVals.get(i)) / Math.abs(origVals.get(i));

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 3 equivalence right")
    void testSystemInflectionPoint3EquivalenceRight() {
        double intervalStart = -5.31356;

        ArrayList<Double> origVals = new ArrayList<>();
        int steps = 100;
        for (double i = 0; i < steps; i++) {
            origVals.add(Equation.solve(intervalStart + i * 0.01));
        }

        ArrayList<Double> eqVals = new ArrayList<>();
        double equivalentStart = intervalStart - 2 * Math.PI;
        for (double i = 0; i < steps; i++) {
            origVals.add(Equation.solve(equivalentStart + i * 0.01));
        }

        for (int i = 0; i < eqVals.size(); i++) {
            double relativeError = Math.abs(origVals.get(i) - eqVals.get(i)) / Math.abs(origVals.get(i));

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point 3 equivalence left")
    void testSystemInflectionPoint3EquivalenceLeft() {
        double intervalStart = -5.31356;

        ArrayList<Double> origVals = new ArrayList<>();
        int steps = 100;
        for (double i = 0; i < steps; i++) {
            origVals.add(Equation.solve(intervalStart - i * 0.01));
        }

        ArrayList<Double> eqVals = new ArrayList<>();
        double equivalentStart = intervalStart - 2 * Math.PI;
        for (double i = 0; i < steps; i++) {
            origVals.add(Equation.solve(equivalentStart - i * 0.01));
        }

        for (int i = 0; i < eqVals.size(); i++) {
            double relativeError = Math.abs(origVals.get(i) - eqVals.get(i)) / Math.abs(origVals.get(i));

            assertTrue(relativeError < eps_test);
        }
    }

    @Test
    @DisplayName("Inflection point x==1 equivalence left")
    void testSystemInflectionPointSecondEquivalenceLeft() {
        double intervalEnd = 1;
        double intervalStart = intervalEnd - 0.5;

        double last = intervalStart;

        for (double i = intervalStart + 0.01; i <= intervalEnd; i += 0.001) {
            assertTrue(Equation.solve(i) < Equation.solve(last));
            last = i;
        }
    }

    @Test
    @DisplayName("Inflection point x==1 equivalence right")
    void testSystemInflectionPointSecondEquivalenceRight() {
        double intervalStart = 1;
        double intervalEnd = intervalStart + 0.5;

        double last = intervalStart+0.0001;

        for (double i = intervalStart + 0.01; i <= intervalEnd; i += 0.001) {
            assertTrue(Equation.solve(i) > Equation.solve(last));
            last = i;
        }
    }
}