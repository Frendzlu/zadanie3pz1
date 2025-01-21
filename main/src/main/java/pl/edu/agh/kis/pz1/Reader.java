/**
 * Represents a Reader thread that interacts with a Library. A Reader can request to read, 
 * perform the reading operation, and idle for a certain amount of time.
 * The reading and idle durations can either be set to default values or specified during instantiation.
 */
package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.NumUtils;

/**
 * The Reader class models a thread that simulates a reader's behavior in a library.
 */
public class Reader extends Thread {
    private final Library library;
    private final int MIN_ACTIVE_TIME; // Minimum reading time in milliseconds.
    private final int MAX_ACTIVE_TIME; // Maximum reading time in milliseconds.
    private final int MIN_IDLE_TIME;   // Minimum idle time in milliseconds.
    private final int MAX_IDLE_TIME;   // Maximum idle time in milliseconds.

    /**
     * Retrieves the minimum active (reading) time.
     *
     * @return the minimum active time in milliseconds.
     */
    public int getMIN_ACTIVE_TIME() {
        return MIN_ACTIVE_TIME;
    }

    /**
     * Retrieves the maximum active (reading) time.
     *
     * @return the maximum active time in milliseconds.
     */
    public int getMAX_ACTIVE_TIME() {
        return MAX_ACTIVE_TIME;
    }

    /**
     * Retrieves the minimum idle time.
     *
     * @return the minimum idle time in milliseconds.
     */
    public int getMIN_IDLE_TIME() {
        return MIN_IDLE_TIME;
    }

    /**
     * Retrieves the maximum idle time.
     *
     * @return the maximum idle time in milliseconds.
     */
    public int getMAX_IDLE_TIME() {
        return MAX_IDLE_TIME;
    }

    /**
     * Constructs a Reader with default active and idle times.
     *
     * @param library the Library instance the Reader interacts with.
     * @param name    the name of the Reader thread.
     */
    public Reader(Library library, String name) {
        this.library = library;
        this.setName(name);
        MIN_ACTIVE_TIME = 1000; // ms
        MAX_ACTIVE_TIME = 3000;
        MIN_IDLE_TIME = 3000;
        MAX_IDLE_TIME = 10000;
    }

    /**
     * Constructs a Reader with specified active and idle times.
     *
     * @param library       the Library instance the Reader interacts with.
     * @param name          the name of the Reader thread.
     * @param minWriteTime  the minimum active (reading) time in milliseconds.
     * @param maxWriteTime  the maximum active (reading) time in milliseconds.
     * @param minIdleTime   the minimum idle time in milliseconds.
     * @param maxIdleTime   the maximum idle time in milliseconds.
     */
    public Reader(Library library, String name, int minWriteTime, int maxWriteTime, int minIdleTime, int maxIdleTime) {
        this.library = library;
        this.setName(name);
        MIN_ACTIVE_TIME = minWriteTime;
        MAX_ACTIVE_TIME = maxWriteTime;
        MIN_IDLE_TIME = minIdleTime;
        MAX_IDLE_TIME = maxIdleTime;
    }

    /**
     * Executes the Reader's behavior in a loop. The Reader alternates between reading and idling.
     * Requests to read and starts reading, then idles for a random time within the specified range.
     * Interrupts the thread gracefully if an InterruptedException is encountered.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                library.requestReading(this);
                library.startReading(this);
                Thread.sleep(NumUtils.randIntBetween(MIN_ACTIVE_TIME, MAX_ACTIVE_TIME));
                library.stopReading(this);
                Thread.sleep(NumUtils.randIntBetween(MIN_IDLE_TIME, MAX_IDLE_TIME));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
