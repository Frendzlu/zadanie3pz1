package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.NumUtils;

public class Writer extends Thread {
    private final Library library;
    private final int MIN_ACTIVE_TIME;
    private final int MAX_ACTIVE_TIME;
    private final int MIN_IDLE_TIME;
    private final int MAX_IDLE_TIME;

    public int getMIN_ACTIVE_TIME() {
        return MIN_ACTIVE_TIME;
    }

    public int getMAX_ACTIVE_TIME() {
        return MAX_ACTIVE_TIME;
    }

    public int getMIN_IDLE_TIME() {
        return MIN_IDLE_TIME;
    }

    public int getMAX_IDLE_TIME() {
        return MAX_IDLE_TIME;
    }

    public Writer(Library library, String name) {
        this.setName(name);
        this.library = library;
        MIN_ACTIVE_TIME = 1000; // ms
        MAX_ACTIVE_TIME = 3000;
        MIN_IDLE_TIME = 3000;
        MAX_IDLE_TIME = 10000;
    }

    public Writer(Library library, String name,  int minWriteTime, int maxWriteTime, int minIdleTime, int maxIdleTime) {
        this.library = library;
        this.setName(name);
        MIN_ACTIVE_TIME = minWriteTime;
        MAX_ACTIVE_TIME = maxWriteTime;
        MIN_IDLE_TIME = minIdleTime;
        MAX_IDLE_TIME = maxIdleTime;
    }

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
