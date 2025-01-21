package pl.edu.agh.kis.pz1.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TextUtilsTest {

    // Test for generateUUID() method
    @Test
    void testGenerateUUID() {
        // Generate a UUID using the method
        String uuid = TextUtils.generateUUID();

        // Assert that the generated string is not null or empty
        assertNotNull(uuid);
        assertFalse(uuid.isEmpty());

        // Assert that the generated string is a valid UUID format
        try {
            UUID parsedUUID = UUID.fromString(uuid);
            assertNotNull(parsedUUID);
        } catch (IllegalArgumentException e) {
            fail("Generated UUID is not in a valid format: " + uuid);
        }
    }

    // Test for listToStringWithAdditionalElement() method
    @Test
    void testListToStringWithAdditionalElement() {
        // Create a list of threads
        ArrayList<Thread> list = new ArrayList<>();
        list.add(new Thread("thread1"));
        list.add(new Thread("thread2"));
        list.add(new Thread("thread3"));

        // Create an additional thread
        Thread additionalThread = new Thread("additionalThread");

        // Call the method
        String result = TextUtils.listToStringWithAdditionalElement(list, additionalThread);

        // Assert that the result contains all thread names
        assertTrue(result.contains("thread1"));
        assertTrue(result.contains("thread2"));
        assertTrue(result.contains("thread3"));
        assertTrue(result.contains("additionalThread"));

        // Assert that the result has the correct format
        String[] parts = result.split(", ");
        assertEquals(4, parts.length); // We should have 4 parts (thread1, thread2, thread3, additionalThread)

        // Assert that the last element in the result is the additional thread name
        assertEquals("additionalThread", parts[3]);
    }
}
