package pl.edu.agh.kis.pz1.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumUtilsTest {

    // Test for randIntBetween() with a valid range
    @Test
    void testRandIntBetween_validRange() {
        int a = 5;
        int b = 10;

        // Call the method
        int result = NumUtils.randIntBetween(a, b);

        // Assert that the result is between a and b, inclusive
        assertTrue(result >= a && result <= b, "Result should be between " + a + " and " + b);
    }

    // Test for randIntBetween() when a equals b (edge case)
    @Test
    void testRandIntBetween_equalBounds() {
        int a = 7;
        int b = 7;

        // Call the method
        int result = NumUtils.randIntBetween(a, b);

        // Assert that the result is exactly a (or b, since they are the same)
        assertEquals(a, result, "When a equals b, the result should be " + a);
    }

    // Test for multiple calls to ensure randomness
    @Test
    void testRandIntBetween_randomness() {
        int a = 1;
        int b = 100;

        // Call the method multiple times
        int result1 = NumUtils.randIntBetween(a, b);
        int result2 = NumUtils.randIntBetween(a, b);

        // Assert that the results are not equal to each other in most cases
        assertNotEquals(result1, result2, "Random results should not be the same in most cases");
    }

    // Test for edge case: range of size 1 (b == a + 1)
    @Test
    void testRandIntBetween_singleValueRange() {
        int a = 0;
        int b = 1;

        // Call the method multiple times
        int result1 = NumUtils.randIntBetween(a, b);
        int result2 = NumUtils.randIntBetween(a, b);

        // Assert that the result is either 0 or 1
        assertTrue(result1 == 0 || result1 == 1, "Result should be either 0 or 1");
        assertTrue(result2 == 0 || result2 == 1, "Result should be either 0 or 1");
    }
}
