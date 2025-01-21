package pl.edu.agh.kis.pz1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link WriterFactory} class.
 * <p>
 * This class contains tests for the methods of the {@link WriterFactory} to ensure that writers are created correctly
 * and that the factory works as expected with both default and custom time intervals.
 * </p>
 */
class WriterFactoryTest {

    /** Mocked instance of the {@link LibraryTest} class */
    private Library library;

    /** Instance of {@link WriterFactory} to be tested */
    private WriterFactory writerFactory;

    /**
     * Sets up the test environment by creating a new mock library and a new instance of WriterFactory.
     */
    @BeforeEach
    void setUp() {
        library = mock(Library.class);  // Mocking the Library class
        writerFactory = new WriterFactory();
    }

    /**
     * Test to ensure that a new {@link Writer} is correctly created and added to the factory.
     * <p>
     * Verifies that the writer is not null, that its name is properly generated,
     * and that it is added to the list of writers in the factory.
     * </p>
     */
    @Test
    void testAddWriter() throws InterruptedException {
        // Add a new writer to the factory
        Writer writer = writerFactory.addWriter(library);

        // Verify that a new Writer object is returned
        assertNotNull(writer, "Writer should not be null");

        // Verify that the Library's requestWriting method is not yet called (it will be called by the writer when it starts)
        verify(library, never()).requestWriting(any(Writer.class));

        // Ensure the writer's name is a non-null UUID
        assertNotNull(writer.getName(), "Writer should have a valid name (UUID)");

        // Verify that the writer is added to the factory's writers list
        assertEquals(1, writerFactory.writers.size(), "Writer should be added to the list of writers");
    }

    /**
     * Test to ensure that custom time intervals are correctly applied to the writers created by the factory.
     * <p>
     * Verifies that the time intervals for the writer match the ones provided during the creation of the factory.
     * </p>
     */
    @Test
    void testCustomTimeIntervals() {
        // Create a WriterFactory with custom time intervals
        WriterFactory customFactory = new WriterFactory(500, 1500, 1000, 3000);

        // Add a writer
        Writer writer = customFactory.addWriter(library);

        // Ensure the writer's times are set correctly
        assertEquals(500, writer.getMIN_ACTIVE_TIME(), "The min active time should be 500");
        assertEquals(1500, writer.getMAX_ACTIVE_TIME(), "The max active time should be 1500");
        assertEquals(1000, writer.getMIN_IDLE_TIME(), "The min idle time should be 1000");
        assertEquals(3000, writer.getMAX_IDLE_TIME(), "The max idle time should be 3000");
    }
}
