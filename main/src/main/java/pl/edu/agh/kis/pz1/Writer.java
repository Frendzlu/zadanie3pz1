/**
 * Represents a Writer thread that interacts with a Library. A Writer can request to write,
 * perform the writing operation, and idle for a certain amount of time.
 * The writing and idle durations can either be set to default values or specified during instantiation.
 */
package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.NumUtils;

/**
 * The Writer class models a thread that simulates a writer's behavior in a library.
 */
public class Writer extends Thread {
    private final Library library;
    private final int MIN_ACTIVE_TIME; // Minimum writing time in milliseconds.
    private final int MAX_ACTIVE_TIME; // Maximum writing time in milliseconds.
    private final int MIN_IDLE_TIME;   // Minimum idle time in milliseconds.
    private final int MAX_IDLE_TIME;   // Maximum idle time in milliseconds.

    /**
     * Retrieves the minimum active (writing) time.
     *
     * @return the minimum active time in milliseconds.
     */
    public int getMIN_ACTIVE_TIME() {
        return MIN_ACTIVE_TIME;
    }

    /**
     * Retrieves the maximum active (writing) time.
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
     * Constructs a Writer with default active and idle times.
     *
     * @param library the Library instance the Writer interacts with.
     * @param name    the name of the Writer thread.
     */
    public Writer(Library library, String name) {
        this.setName(name);
        this.library = library;
        MIN_ACTIVE_TIME = 1000; // ms
        MAX_ACTIVE_TIME = 3000;
        MIN_IDLE_TIME = 3000;
        MAX_IDLE_TIME = 10000;
    }

    /**
     * Constructs a Writer with specified active and idle times.
     *
     * @param library       the Library instance the Writer interacts with.
     * @param name          the name of the Writer thread.
     * @param minWriteTime  the minimum active (writing) time in milliseconds.
     * @param maxWriteTime  the maximum active (writing) time in milliseconds.
     * @param minIdleTime   the minimum idle time in milliseconds.
     * @param maxIdleTime   the maximum idle time in milliseconds.
     */
    public Writer(Library library, String name, int minWriteTime, int maxWriteTime, int minIdleTime, int maxIdleTime) {
        this.library = library;
        this.setName(name);
        MIN_ACTIVE_TIME = minWriteTime;
        MAX_ACTIVE_TIME = maxWriteTime;
        MIN_IDLE_TIME = minIdleTime;
        MAX_IDLE_TIME = maxIdleTime;
    }

    /**
     * Executes the Writer's behavior in a loop. The Writer alternates between writing and idling.
     * Requests to write and starts writing, then idles for a random time within the specified range.
     * Interrupts the thread gracefully if an InterruptedException is encountered.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                library.requestWriting(this);
                library.startWriting(this);
                Thread.sleep(NumUtils.randIntBetween(MIN_ACTIVE_TIME, MAX_ACTIVE_TIME));
                library.stopWriting(this);
                Thread.sleep(NumUtils.randIntBetween(MIN_IDLE_TIME, MAX_IDLE_TIME));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
