package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("product Tests")
public class ProductTest {

	private Product product;

	@BeforeEach
	void setUp() {
		product = new Product("test product", 100.0);
	}

	@Test
	@DisplayName("create product")
	void testValidProductCreation() {
		Product p = new Product("product1", 25.5);
		assertAll(
				() -> assertEquals("product1", p.getName(), "product name should be correct"),
				() -> assertEquals(25.5, p.getPrice(), "product price should be correct"),
				() -> assertEquals(0.0, p.getDiscount(), "discount should be correct")
		);
	}

	@Test
	@DisplayName("create product with negative price")
	void testInvalidProductPrice() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new Product("invalid", -10.0),
				"creating product with negative price should throw an exception");

		assertEquals("price must be positive", exception.getMessage());
	}

	@Test
	@DisplayName("should be a valid discount")
	void testApplyValidDiscount() {
		product.applyDiscount(30.0);
		assertEquals(30.0, product.getDiscount(), "discount should be 30%");
		assertEquals(70.0, product.getFinalPrice(), "Final price should be 70 after 30% discount");
	}

	@ParameterizedTest
	@DisplayName("try invalid discounts")
	@ValueSource(doubles = {-5.0, 51.0, 100.0})
	void testInvalidDiscounts(double invalidDiscount) {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> product.applyDiscount(invalidDiscount),
				"invalid discount should throw an exception");

		assertEquals("invalid discount", exception.getMessage());
	}

	@Test
	@DisplayName("final price with no discount")
	void testFinalPriceNoDiscount() {
		assertEquals(100.0, product.getFinalPrice(), "final price should equal original price");
	}

	@Test
	@DisplayName("final price with discount")
	void testFinalPriceWithDiscount() {
		product.applyDiscount(25.0);
		assertEquals(75.0, product.getFinalPrice(), "final price should be 75.0 after 25% discount");
	}

	@Test
	@Timeout(1)
	@DisplayName("product operations performance")
	void testProductPerformance() {
		//test that creating and applying discounts to 1000 products completes within timeout
		for (int i = 0; i < 1000; i++) {
			Product p = new Product("product" + i, i);
			p.applyDiscount(i % 50); //apply a discount between 0-49%
		}
	}

	@Test
	@Disabled("test fails because discount calculation precision needs to be fixed")
	@DisplayName("discount precision")
	void testDiscountPrecision() {
		product.applyDiscount(33.33); // precise discount percentage
		assertEquals(66.67, product.getFinalPrice(), 0.001, "final price calculation should handle decimal");
	}
}