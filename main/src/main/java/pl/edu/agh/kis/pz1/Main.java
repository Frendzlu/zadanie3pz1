package pl.edu.agh.kis.pz1;

import pl.edu.agh.kis.pz1.util.TextUtils;

/**
 * Code for the concurrency problem with set max readers at a time
 * @author Mateusz Francik
 */
public class Main {
    public static void main( String[] args ) {
        int readers = 10;
        try {
            readers = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Invalid number of readers, using default value: " + 10);
        }
        int writers = 3;
        try {
            writers = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println("Invalid number of writers, using default value: " + 5);
        }

        Library l = new Library();
        ReaderFactory rf = new ReaderFactory();
        WriterFactory wf = new WriterFactory();


        for (int i = 0; i < readers; i++) {
            Reader r = rf.addReader(l);
            r.start();
        }

        for (int i = 0; i < writers; i++) {
            Writer w = wf.addWriter(l);
            w.start();
        }
//
//        Writer w1 = new Writer(l, "#w1");
//        Writer w2 = new Writer(l, "#w2");
//        Writer w3 = new Writer(l, "#w3");
//
//        Reader r1 = new Reader(l, "#r1");
//        Reader r2 = new Reader(l, "#r2");
//        Reader r3 = new Reader(l, "#r3");
//        Reader r4 = new Reader(l, "#r4");
//        Reader r5 = new Reader(l, "#r5");
//        Reader r6 = new Reader(l, "#r6");
//        Reader r7 = new Reader(l, "#r7");
//        Reader r8 = new Reader(l, "#r8");
//        Reader r9 = new Reader(l, "#r9");
//        Reader r10 = new Reader(l, "#r10");
//        w1.start();
//        w2.start();
//        w3.start();
//        r1.start();
//        r2.start();
//        r3.start();
//        r4.start();
//        r5.start();
//        r6.start();
//        r7.start();
//        r8.start();
//        r9.start();
//        r10.start();
    }
}
