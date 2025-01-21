package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link ReaderFactory} class.
 * <p>
 * This class contains tests for the methods of the {@link ReaderFactory} to ensure that readers are created correctly
 * and that the factory works as expected with both default and custom time intervals.
 * </p>
 */
class ReaderFactoryTest {

    /** Mocked instance of the {@link LibraryTest} class */
    private Library library;

    /** Instance of {@link ReaderFactory} to be tested */
    private ReaderFactory readerFactory;

    /**
     * Sets up the test environment by creating a new mock library and a new instance of ReaderFactory.
     */
    @BeforeEach
    void setUp() {
        library = mock(Library.class);  // Mocking the Library class
        readerFactory = new ReaderFactory();
    }

    /**
     * Test to ensure that a new {@link Reader} is correctly created and added to the factory.
     * <p>
     * Verifies that the reader is not null, that its name is properly generated,
     * and that it is added to the list of readers in the factory.
     * </p>
     */
    @Test
    void testAddReader() throws InterruptedException {
        // Add a new reader to the factory
        Reader reader = readerFactory.addReader(library);

        // Verify that a new Reader object is returned
        assertNotNull(reader, "Reader should not be null");

        // Verify that the Library's requestReading method is not yet called (it will be called by the reader when it starts)
        verify(library, never()).requestReading(any(Reader.class));

        // Ensure the reader's name is a non-null UUID
        assertNotNull(reader.getName(), "Reader should have a valid name (UUID)");

        // Verify that the reader is added to the factory's readers list
        assertEquals(1, readerFactory.readers.size(), "Reader should be added to the list of readers");
    }

    /**
     * Test to ensure that custom time intervals are correctly applied to the readers created by the factory.
     * <p>
     * Verifies that the time intervals for the reader match the ones provided during the creation of the factory.
     * </p>
     */
    @Test
    void testCustomTimeIntervals() {
        // Create a ReaderFactory with custom time intervals
        ReaderFactory customFactory = new ReaderFactory(500, 1500, 1000, 3000);

        // Add a reader
        Reader reader = customFactory.addReader(library);

        // Ensure the reader's times are set correctly
        assertEquals(500, reader.getMIN_ACTIVE_TIME(), "The min active time should be 500");
        assertEquals(1500, reader.getMAX_ACTIVE_TIME(), "The max active time should be 1500");
        assertEquals(1000, reader.getMIN_IDLE_TIME(), "The min idle time should be 1000");
        assertEquals(3000, reader.getMAX_IDLE_TIME(), "The max idle time should be 3000");
    }
}
