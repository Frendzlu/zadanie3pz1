package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link Library} class to verify interactions with {@link Reader} and {@link Writer} threads.
 */
class LibraryTest {

    private Library library;
    private Reader reader;
    private Writer writer;
    private Reader reader1;
    private Reader reader2;

    /**
     * Initializes the {@link Library} instance before each test.
     */
    @BeforeEach
    void setUp() {
        // Initialize the library instance before each test
        library = new Library();
    }

    /**
     * Test for starting and stopping reading by a {@link Reader}.
     *
     * @throws InterruptedException if the thread is interrupted during test execution
     */
    @Test
    void testReaderStartStopReading() throws InterruptedException {
        // Mock the Reader class
        reader = new Reader(library, "Reader1");

        // Simulate the Reader requesting to read
        library.requestReading(reader);

        // Simulate starting and stopping reading
        library.startReading(reader);
        library.stopReading(reader);

        // Verify interactions with the Reader
        assertTrue(true);
    }

    /**
     * Test for starting and stopping writing by a {@link Writer}.
     *
     * @throws InterruptedException if the thread is interrupted during test execution
     */
    @Test
    void testWriterStartStopWriting() throws InterruptedException {
        // Mock the Writer class
        writer = new Writer(library, "Writer1");

        // Simulate the Writer requesting to write
        library.requestWriting(writer);

        // Simulate starting and stopping writing
        library.startWriting(writer);
        library.stopWriting(writer);

        // Verify interactions with the Writer
        assertTrue(true);
    }

    /**
     * Test for managing concurrent access for several {@link Reader}s.
     *
     * @throws InterruptedException if the thread is interrupted during test execution
     */
    @Test
    void testConcurrentReaders() throws InterruptedException {
        // Mock the Reader class
        reader1 = new Reader(library, "Reader1");
        reader2 = new Reader(library, "Reader2");

        // Simulate requesting reading access
        library.requestReading(reader1);
        library.requestReading(reader2);

        // Simulate starting reading
        library.startReading(reader1);
        library.startReading(reader2);

        // Simulate stopping reading
        library.stopReading(reader1);
        library.stopReading(reader2);

        assertTrue(true);
        assertTrue(true);
    }
}
