import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CalculatorTestsHamcrest {
    Calculator sut;

    @BeforeAll
    public static void startedAll() {
        System.out.println("Все тесты стартовали!");
    }

    @BeforeEach
    public void started() {
        System.out.println("Данный тест стартовал");
        sut = new Calculator();
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Все тесты закончены!");
    }

    @AfterEach
    public void finished() {
        System.out.println("Данный тест закончен");
    }

    @Test
    public void deductionTest() {
        double a = 80, b = 20, expected = 60;

        double result = sut.deduction(a, b);
        assertThat(result, equalTo(expected));
        //assertEquals(expected, result);
    }

    @Test
    public void additionTest() {
        double a = 80, b = 20, expected = 100;

        double result = sut.addition(a, b);
        assertThat(result, equalTo(expected));
        //assertEquals(expected, result);
    }

    @Test
    public void multiplicationTest() {
        double a = 80, b = 20, expected = 1600;

        double result = sut.multiplication(a, b);

        //assertEquals(expected, result);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void divisionTest() {
        int a = 80, b = 20, expected = 4;

        int result = sut.division(a, b);


       // assertEquals(expected, result);
        assertThat(result, equalTo(expected));
    }

    @Test
    public void divisionByZeroTEst() {
        int a = 80, b = 0;
        var expected = ArithmeticException.class;

        Executable action = () -> sut.division(a, b);

        assertThrows(expected, action);
    }

    @ParameterizedTest
    @MethodSource("source")
    public void linearEquationTest(int x, int expected) {

        int result = sut.linearEquation(x);

        //assertEquals(expected, result);
        assertThat(result, equalTo(expected));
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(7, 55), Arguments.of(12, 100));
    }

    @Test
    public void polishNotationTestHamcrest() {
        String mathEq = "8 / 2 * 3 - 5 + 8";

        assertThat(mathEq, anyOf(containsString("2 * 3"), endsWith("5 + 8")));
    }

    @Test
    public void linearEquationTestHamcrest() {
        Integer intVal = 4;

        assertThat(intVal, allOf(greaterThan(1), not(equalTo(0))));
    }
}