package pl.edu.agh.kis.pz1.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

public class TextUtils {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String listToStringWithAdditionalElement(ArrayList<Thread> list, Thread additionalElement) {
        StringBuilder result = new StringBuilder();
        for (Thread thread : list) {
            result.append(thread.getName()).append(", ");
        }
        result.append(additionalElement.getName());
        return result.toString();
    }

}
