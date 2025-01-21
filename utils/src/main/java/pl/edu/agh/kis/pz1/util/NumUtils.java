package pl.edu.agh.kis.pz1.util;

import java.util.Random;

public class NumUtils {
    public static int randIntBetween(int a, int b) {
        Random r = new Random();
        return r.nextInt(b - a + 1) + a;
    }
}
