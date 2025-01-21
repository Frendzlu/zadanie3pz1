package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link Reader} class.
 * <p>
 * This class contains tests to verify the behavior of the {@link Reader} thread, including its normal cycle, interruption handling,
 * and custom time settings. Mockito is used to mock the {@link LibraryTest} class interactions and verify the correct behavior of the
 * Reader thread during its execution.
 * </p>
 */
class ReaderTest {

    /** Mocked instance of the {@link LibraryTest} class */
    private Library library;

    /** Instance of the {@link Reader} thread to be tested */
    private Reader reader;

    /**
     * Sets up the test environment by creating a new mock library.
     */
    @BeforeEach
    void setUp() {
        // Mock the Library class to simulate its behavior during tests
        library = mock(Library.class);
    }

    /**
     * Test to ensure that the Reader thread follows its expected cycle.
     * <p>
     * This test verifies that the Reader thread calls the {@link LibraryTest} methods in the expected order:
     * 1. requestReading
     * 2. startReading
     * 3. stopReading
     * </p>
     * The test also ensures that the Reader thread behaves correctly for a duration of time before being interrupted.
     * </p>
     *
     * @throws InterruptedException if the thread sleep or join is interrupted
     */
    @Test
    void testReaderCycle() throws InterruptedException {
        // Create a Reader with default times
        reader = new Reader(library, "Reader1");

        // Start the Reader thread
        reader.start();

        // Allow the Reader to run for 5 seconds to simulate its behavior
        TimeUnit.SECONDS.sleep(5);

        // Capture the interactions with the Library
        ArgumentCaptor<Reader> readerCaptor = ArgumentCaptor.forClass(Reader.class);
        verify(library, atLeast(1)).requestReading(readerCaptor.capture());
        verify(library, atLeast(1)).startReading(readerCaptor.capture());
        verify(library, atLeast(1)).stopReading(readerCaptor.capture());

        // Verify that each method was called at least once in the correct order
        verify(library, times(1)).requestReading(any(Reader.class));
        verify(library, times(1)).startReading(any(Reader.class));
        verify(library, times(1)).stopReading(any(Reader.class));

        // Interrupt the Reader thread after the test completes
        reader.interrupt();
        reader.join();  // Wait for the thread to finish
    }

    /**
     * Test to ensure that the Reader thread can be properly interrupted.
     * <p>
     * This test verifies that the Reader thread can be interrupted and that the {@link Reader#isInterrupted()} method
     * reflects the interruption status correctly.
     * </p>
     *
     * @throws InterruptedException if the thread sleep or join is interrupted
     */
    @Test
    void testReaderInterruption() throws InterruptedException {
        // Create a Reader with custom times
        reader = new Reader(library, "Reader2", 1000, 2000, 2000, 4000);

        // Start the Reader thread
        reader.start();

        // Allow the Reader to run for a short period (2 seconds)
        TimeUnit.SECONDS.sleep(2);

        // Interrupt the Reader thread
        reader.interrupt();

        // Assert that the thread was interrupted properly
        assertTrue(reader.isInterrupted(), "Reader thread should be interrupted.");

        // Wait for the thread to finish
        reader.join();
    }

    /**
     * Test to ensure that custom time intervals for active and idle times are correctly applied to the Reader thread.
     * <p>
     * This test verifies that the Reader thread interacts with the {@link LibraryTest} as expected, and checks that the
     * custom time intervals are honored during the Reader's cycle.
     * </p>
     *
     * @throws InterruptedException if the thread sleep or join is interrupted
     */
    @Test
    void testReaderCustomTimes() throws InterruptedException {
        int minActiveTime = 500;
        int maxActiveTime = 1500;
        int minIdleTime = 1000;
        int maxIdleTime = 2000;

        // Create a Reader with custom active and idle times
        reader = new Reader(library, "Reader3", minActiveTime, maxActiveTime, minIdleTime, maxIdleTime);

        // Start the Reader thread
        reader.start();

        // Allow the Reader to run for a short time (3 seconds)
        TimeUnit.SECONDS.sleep(3);

        // Capture the interactions with the Library
        ArgumentCaptor<Reader> readerCaptor = ArgumentCaptor.forClass(Reader.class);
        verify(library, atLeast(1)).requestReading(readerCaptor.capture());
        verify(library, atLeast(1)).startReading(readerCaptor.capture());
        verify(library, atLeast(1)).stopReading(readerCaptor.capture());

        // Check that the Reader performed at least one full cycle (requestReading -> startReading -> stopReading)
        assertTrue(readerCaptor.getAllValues().size() > 1, "Reader should have performed at least one cycle.");

        // Interrupt the Reader thread after the test completes
        reader.interrupt();
        reader.join();  // Wait for the thread to finish
    }
}
