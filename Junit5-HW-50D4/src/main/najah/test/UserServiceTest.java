package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("User Service Tests")
@Execution(ExecutionMode.CONCURRENT) //enable parallel execution
public class UserServiceTest {

	private UserService userService;

	@BeforeAll
	static void initTestEnvironment() {
		System.out.println("UserService test environment initialized");
	}

	@AfterAll
	static void tearDownTestEnvironment() {
		System.out.println("UserService test environment cleaned up");
	}

	@BeforeEach
	void setUp() {
		userService = new UserService();
		System.out.println("UserService instance created");
	}

	@AfterEach
	void cleanUp() {
		userService = null;
		System.out.println("UserService instance cleared");
	}

	@Test
	@DisplayName("valid email validation")
	void testValidEmail() {
		assertTrue(userService.isValidEmail("user@email.com"), "should validate proper email format");
	}

	@ParameterizedTest
	@DisplayName("invalid email validation")
	@NullAndEmptySource
	@ValueSource(strings = {"invalid", "no-at-sign.com", "no-dot@domain", "@.com"})
	void testInvalidEmail(String invalidEmail) {
		assertFalse(userService.isValidEmail(invalidEmail), "should reject invalid email formats");
	}

	@Test
	@DisplayName("successful authentication")
	void testSuccessfulAuthentication() {
		assertTrue(userService.authenticate("admin", "1234"), "should authenticate with correct credentials");
	}

	@ParameterizedTest
	@DisplayName("failed authentication")
	@CsvSource({
			"admin, wrongpass",
			"wronguser, 1234",
			"wronguser, wrongpass"
	})
	void testFailedAuthentication(String username, String password) {
		assertFalse(userService.authenticate(username, password), "should reject incorrect credentials");
	}

	@Test
	@Timeout(1)
	@DisplayName("authentication performance test")
	void testAuthenticationPerformance() {
		// Test performance by authenticating multiple times within timeout
		for (int i = 0; i < 10000; i++) {
			userService.authenticate("admin", "1234");
		}
	}

	@Test
	@Disabled("this test is disabled because case insensitivity is not yet implemented")
	@DisplayName("case insensitive username authentication")
	void testCaseInsensitiveUsername() {
		assertTrue(userService.authenticate("ADMIN", "1234"), "should authenticate with uppercase username");
	}
}