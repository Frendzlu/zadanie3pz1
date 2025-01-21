/**
 * Utility class providing text-related helper methods.
 */
package pl.edu.agh.kis.pz1.util;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The TextUtils class contains utility methods for text manipulation and generation.
 */
public class TextUtils {

    /**
     * Generates a random universally unique identifier (UUID) as a string.
     *
     * @return a randomly generated UUID in string format.
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Converts a list of threads to a single string with an additional thread's name appended.
     *
     * @param list the list of threads whose names will be concatenated.
     * @param additionalElement an additional thread whose name will be appended to the result.
     * @return a string containing the names of all threads in the list and the additional thread, separated by ", ".
     * @throws NullPointerException if {@code list} or {@code additionalElement} is null.
     */
    public static String listToStringWithAdditionalElement(ArrayList<Thread> list, Thread additionalElement) {
        StringBuilder result = new StringBuilder();
        for (Thread thread : list) {
            result.append(thread.getName()).append(", ");
        }
        result.append(additionalElement.getName());
        return result.toString();
    }

}
