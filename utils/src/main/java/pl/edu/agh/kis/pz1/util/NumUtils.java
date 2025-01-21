/**
 * Utility class providing number-related helper methods.
 */
package pl.edu.agh.kis.pz1.util;

import java.util.Random;

/**
 * The NumUtils class contains utility methods for generating random numbers.
 */
public class NumUtils {

    /**
     * Generates a random integer between the specified bounds (inclusive).
     *
     * @param a the lower bound of the range (inclusive).
     * @param b the upper bound of the range (inclusive).
     * @return a random integer between {@code a} and {@code b} (inclusive).
     * @throws IllegalArgumentException if {@code a} is greater than {@code b}.
     */
    public static int randIntBetween(int a, int b) {
        if (a > b) {
            throw new IllegalArgumentException("Lower bound must not be greater than upper bound.");
        }
        Random r = new Random();
        return r.nextInt(b - a + 1) + a;
    }
}
