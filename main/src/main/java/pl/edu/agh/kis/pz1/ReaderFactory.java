package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.TextUtils;

import java.util.ArrayList;

/**
 * Factory class for creating and managing {@link Reader} objects.
 * <p>
 * This class allows creating {@link Reader} instances with predefined or custom active and idle times.
 * The created readers are stored in an internal list.
 * </p>
 */
public class ReaderFactory {

    /** List of created readers */
    public ArrayList<Reader> readers;

    /** Minimum active time for a reader in milliseconds */
    private final int MIN_ACTIVE_TIME;

    /** Maximum active time for a reader in milliseconds */
    private final int MAX_ACTIVE_TIME;

    /** Minimum idle time for a reader in milliseconds */
    private final int MIN_IDLE_TIME;

    /** Maximum idle time for a reader in milliseconds */
    private final int MAX_IDLE_TIME;

    /**
     * Default constructor initializing the factory with default time intervals.
     * Default active time range: 1000ms to 3000ms
     * Default idle time range: 1000ms to 5000ms
     */
    public ReaderFactory() {
        readers = new ArrayList<>();
        MIN_ACTIVE_TIME = 1000;
        MAX_ACTIVE_TIME = 3000;
        MIN_IDLE_TIME = 1000;
        MAX_IDLE_TIME = 5000;
    }

    /**
     * Constructor that allows customizing the time intervals for readers.
     *
     * @param minActiveTime Minimum active time for readers in milliseconds
     * @param maxActiveTime Maximum active time for readers in milliseconds
     * @param minIdleTime Minimum idle time for readers in milliseconds
     * @param maxIdleTime Maximum idle time for readers in milliseconds
     */
    public ReaderFactory(int minActiveTime, int maxActiveTime, int minIdleTime, int maxIdleTime) {
        readers = new ArrayList<>();
        MIN_ACTIVE_TIME = minActiveTime;
        MAX_ACTIVE_TIME = maxActiveTime;
        MIN_IDLE_TIME = minIdleTime;
        MAX_IDLE_TIME = maxIdleTime;
    }

    /**
     * Adds a new {@link Reader} to the factory with the provided library and default time intervals.
     * The new reader is also added to the internal list of readers.
     *
     * @param library The {@link Library} instance that the reader will interact with
     * @return The newly created {@link Reader} object
     */
    public Reader addReader(Library library) {
        Reader reader = new Reader(library, TextUtils.generateUUID(), MIN_ACTIVE_TIME, MAX_ACTIVE_TIME, MIN_IDLE_TIME, MAX_IDLE_TIME);
        readers.add(reader);
        return reader;
    }
}
