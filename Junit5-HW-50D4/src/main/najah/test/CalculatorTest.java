package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("calculator tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // For ordered test execution
public class CalculatorTest {

	private Calculator calc;

	@BeforeAll
	static void initializeTestEnvironment() {
		System.out.println("initiating calculator test");
	}

	@AfterAll
	static void tearDownTestEnvironment() {
		System.out.println("calculator tests finished");
	}

	@BeforeEach
	void setUp() {
		calc = new Calculator();
		System.out.println("calculator instance created");
	}

	@AfterEach
	void tearDown() {
		calc = null;
		System.out.println("calculator instance cleared");
	}

	@Test
	@Order(1)
	@DisplayName("add functionality with positives")
	void testAddPositiveNumbers() {
		assertEquals(12, calc.add(7, 5), "answer should be 12");
	}

	@Test
	@Order(2)
	@DisplayName("add functionality with negatives")
	void testAddNegativeNumbers() {
		assertEquals(-8, calc.add(-5, -3), "answer should be -8");
	}

	@Test
	@Order(3)
	@DisplayName("testing zero addition")
	void testAddWithZero() {
		assertAll(
				() -> assertEquals(7, calc.add(7, 0), "should equal 7"),
				() -> assertEquals(-8, calc.add(-8, 0), "should equal -8"),
				() -> assertEquals(0, calc.add(0, 0), "0 + 0 should equal 0")
		);
	}

	@ParameterizedTest
	@Order(4)
	@DisplayName("parameterized test")
	@CsvSource({
			"1, 1, 2",
			"3, 1, 4",
			"-1, 8, 7",
			"5, -3, 2",
			"12, 15, 27"
	})
	void testAddWithMultipleInputs(int a, int b, int expected) {
		assertEquals(expected, calc.add(a, b));
	}

	@Test
	@Order(5)
	@DisplayName("division of positive numbers")
	void testDividePositiveNumbers() {
		assertEquals(2, calc.divide(10, 5), "10 / 5 should equal 2");
	}

	@Test
	@Order(6)
	@DisplayName("division with negative numbers")
	void testDivideNegativeNumbers() {
		assertEquals(-2, calc.divide(-10, 5), "-10 / 5 should equal -2");
	}

	@Test
	@Order(7)
	@DisplayName("division by zero it should throw an exception")
	void testDivideByZero() {
		Exception exception = assertThrows(ArithmeticException.class,
				() -> calc.divide(10, 0),
				"division by zero should is an exception");

		assertEquals("error you can't divied by 0", exception.getMessage());
	}

	@Test
	@Order(8)
	@DisplayName("factorial")
	void testFactorialPositive() {
		assertAll(
				() -> assertEquals(1, calc.factorial(0), "0! should equal 1"),
				() -> assertEquals(1, calc.factorial(1), "1! should equal 1"),
				() -> assertEquals(120, calc.factorial(5), "5! should equal 120")
		);
	}

	@Test
	@Order(9)
	@DisplayName("factorial of negative number is an exception")
	void testFactorialNegative() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> calc.factorial(-1),
				"factorial of negative number should throw exception");

		assertEquals("negative input", exception.getMessage());
	}

	@Test
	@Order(10)
	@Timeout(1)
	@DisplayName("calculator performance test")
	void testCalculatorPerformance() {
		// This should complete within the timeout
		calc.factorial(11);
	}

	@Test
	@Disabled("test is disabled because factorial needs fixing for large numbers")
	@DisplayName("large factorial !")
	void testLargeFactorial() {
		assertEquals(2432902008176640000L, calc.factorial(20), "20! should match expected value");
	}
}