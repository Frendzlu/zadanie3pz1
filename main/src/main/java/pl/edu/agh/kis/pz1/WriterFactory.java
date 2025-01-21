package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.TextUtils;

import java.util.ArrayList;

/**
 * Factory class for creating and managing {@link Writer} objects.
 * <p>
 * This class allows creating {@link Writer} instances with predefined or custom active and idle times.
 * The created writers are stored in an internal list.
 * </p>
 */
public class WriterFactory {

    /** List of created writers */
    public ArrayList<Writer> writers;

    /** Minimum active time for a writer in milliseconds */
    private final int MIN_ACTIVE_TIME;

    /** Maximum active time for a writer in milliseconds */
    private final int MAX_ACTIVE_TIME;

    /** Minimum idle time for a writer in milliseconds */
    private final int MIN_IDLE_TIME;

    /** Maximum idle time for a writer in milliseconds */
    private final int MAX_IDLE_TIME;

    /**
     * Default constructor initializing the factory with default time intervals.
     * Default active time range: 1000ms to 3000ms
     * Default idle time range: 3000ms to 10000ms
     */
    public WriterFactory() {
        writers = new ArrayList<>();
        MIN_ACTIVE_TIME = 1000;
        MAX_ACTIVE_TIME = 3000;
        MIN_IDLE_TIME = 3000;
        MAX_IDLE_TIME = 10000;
    }

    /**
     * Constructor that allows customizing the time intervals for writers.
     *
     * @param minActiveTime Minimum active time for writers in milliseconds
     * @param maxActiveTime Maximum active time for writers in milliseconds
     * @param minIdleTime Minimum idle time for writers in milliseconds
     * @param maxIdleTime Maximum idle time for writers in milliseconds
     */
    public WriterFactory(int minActiveTime, int maxActiveTime, int minIdleTime, int maxIdleTime) {
        writers = new ArrayList<>();
        MIN_ACTIVE_TIME = minActiveTime;
        MAX_ACTIVE_TIME = maxActiveTime;
        MIN_IDLE_TIME = minIdleTime;
        MAX_IDLE_TIME = maxIdleTime;
    }

    /**
     * Adds a new {@link Writer} to the factory with the provided library and default time intervals.
     * The new writer is also added to the internal list of writers.
     *
     * @param library The {@link Library} instance that the writer will interact with
     * @return The newly created {@link Writer} object
     */
    public Writer addWriter(Library library) {
        Writer writer = new Writer(library, TextUtils.generateUUID(), MIN_ACTIVE_TIME, MAX_ACTIVE_TIME, MIN_IDLE_TIME, MAX_IDLE_TIME);
        writers.add(writer);
        return writer;
    }
}
