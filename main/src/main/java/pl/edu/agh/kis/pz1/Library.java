package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.TextUtils;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * The {@code Library} class simulates a library system where {@link Reader} and {@link Writer} threads can concurrently
 * access the library. It ensures that up to a maximum of 5 readers can read at the same time, but only one writer can
 * write at a time. Readers and writers request access to the library, and their actions are managed using semaphores.
 * <p>
 * The class uses semaphores to synchronize the access to shared resources. It handles the scheduling of readers and
 * writers, ensuring that they follow the correct access protocol.
 * </p>
 */
public class Library {
    private final int MAX_READERS = 5;  // Maximum number of readers allowed at the same time
    private ArrayList<Thread> awaiting;  // List of threads awaiting access (readers and writers)
    private ArrayList<Thread> readers;  // List of active readers
    public final Semaphore libraryAccessPermits = new Semaphore(MAX_READERS, true);  // Semaphore for controlling access to the library
    public final Semaphore queueAccessPermits = new Semaphore(1, true);  // Semaphore for controlling access to the queue

    /**
     * Constructor that initializes the awaiting and readers lists.
     */
    public Library() {
        awaiting = new ArrayList<>();
        readers = new ArrayList<>();
    }

    /**
     * Starts reading by a {@link Reader} thread. Acquires the necessary semaphores and updates the readers list.
     *
     * @param reader the {@link Reader} thread requesting to start reading
     * @throws InterruptedException if the thread is interrupted while acquiring semaphores
     */
    public void startReading(Reader reader) throws InterruptedException {
        queueAccessPermits.acquire(1);
        System.out.printf("Reader %s started reading...\n", reader.getName());
        awaiting.remove(reader);
        String currentlyReading = TextUtils.listToStringWithAdditionalElement(readers, reader);
        System.out.printf("Readers currently reading: %s\n", currentlyReading);
        readers.add(reader);
        queueAccessPermits.release(1);
    }

    /**
     * Stops reading by a {@link Reader} thread. Updates the readers list and releases the library access permit.
     *
     * @param reader the {@link Reader} thread requesting to stop reading
     * @throws InterruptedException if the thread is interrupted while acquiring semaphores
     */
    public void stopReading(Reader reader) throws InterruptedException {
        queueAccessPermits.acquire(1);
        System.out.printf("Reader %s stopped reading.\n", reader.getName());
        readers.remove(reader);
        queueAccessPermits.release(1);
        libraryAccessPermits.release(1);
    }

    /**
     * Starts writing by a {@link Writer} thread. Updates the awaiting list.
     *
     * @param writer the {@link Writer} thread requesting to start writing
     * @throws InterruptedException if the thread is interrupted while acquiring semaphores
     */
    public void startWriting(Writer writer) throws InterruptedException {
        queueAccessPermits.acquire(1);
        System.out.printf("Writer %s started writing...\n", writer.getName());
        awaiting.remove(writer);
        queueAccessPermits.release(1);
    }

    /**
     * Stops writing by a {@link Writer} thread and releases the library access permit for all readers.
     *
     * @param writer the {@link Writer} thread requesting to stop writing
     */
    public void stopWriting(Writer writer) {
        System.out.printf("Writer %s stopped writing...\n", writer.getName());
        libraryAccessPermits.release(MAX_READERS);
    }

    /**
     * Requests writing access for a {@link Writer} thread. It adds the writer to the awaiting list and acquires the necessary
     * library access permits.
     *
     * @param writer the {@link Writer} thread requesting writing access
     * @throws InterruptedException if the thread is interrupted while acquiring semaphores
     */
    public void requestWriting(Writer writer) throws InterruptedException {
        queueAccessPermits.acquire(1);
        System.out.printf("Writer %s requested access...\n", writer.getName());
        printAwaitingInfo(writer);
        awaiting.add(writer);
        queueAccessPermits.release(1);
        libraryAccessPermits.acquire(MAX_READERS);
    }

    /**
     * Requests reading access for a {@link Reader} thread. It adds the reader to the awaiting list and acquires the necessary
     * library access permit.
     *
     * @param reader the {@link Reader} thread requesting reading access
     * @throws InterruptedException if the thread is interrupted while acquiring semaphores
     */
    public void requestReading(Reader reader) throws InterruptedException {
        queueAccessPermits.acquire(1);
        System.out.printf("Reader %s requested access...\n", reader.getName());
        printAwaitingInfo(reader);
        awaiting.add(reader);
        queueAccessPermits.release(1);
        libraryAccessPermits.acquire(1);
    }

    /**
     * Prints the list of people currently awaiting access to the library (either reading or writing).
     *
     * @param t the thread (either {@link Reader} or {@link Writer}) whose information will be included in the awaiting list
     */
    private void printAwaitingInfo(Thread t) {
        String currentlyAwaiting = TextUtils.listToStringWithAdditionalElement(awaiting, t);
        System.out.printf("People currently awaiting: %s\n", currentlyAwaiting);
    }
}
