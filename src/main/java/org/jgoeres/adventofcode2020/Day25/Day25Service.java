package org.jgoeres.adventofcode2020.Day25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Day25Service {
    private final String DEFAULT_INPUTS_PATH = "data/day25/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();

    public Day25Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day25Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    private long cardPubKey;
    private long doorPubKey;

    public long doPartA() {
        long result;
        /**
         * What encryption key is the handshake trying to establish?
         **/

        // Find the loop size of the card & door starting with the subject number 7.
        int cardLoopSize = findLoopSize(cardPubKey, 7);
        int doorLoopSize = findLoopSize(doorPubKey, 7);

        // Now find the encryption key of the card & door by using each other's loop size
        long cardEncryptionKey = transform(cardPubKey, doorLoopSize);
        long doorEncryptionKey = transform(doorPubKey, cardLoopSize);
        if (DEBUG) {
            System.out.println("Card Encryption Key: " + cardEncryptionKey);
            System.out.println("Door Encryption Key: " + doorEncryptionKey);
        }

        result = cardEncryptionKey; // either one is fine – they're equal!
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    public int findLoopSize(long key, long subject) {
        // To find the loop size for a given key & subject,
        // start with 0 loops and increase until the transformed
        // subject number (value) equals the given key.
        int value = 1;     // Start with 1
        int loopSize = 0;
        while (value != key) {    // Secret number of loops
            value *= subject;   // set value to itself times subject
            value %= 20201227;  // set value to remainder after dividing by 20201227
            loopSize++;
        }
        return loopSize;
    }

    public long transform(long subject, long loopSize) {
        // To transform a subject number, start with the value 1.
        // Then, a number of times called the loop size,
        // perform the following steps:
        //  Set the value to itself multiplied by the subject number.
        //  Set the value to the remainder after dividing the value by 20201227.
        long value = 1;     // Start with 1
        for (int i = 0; i < loopSize; i++) {    // Secret number of loops
            value *= subject;   // set value to itself times subject
            value %= 20201227;  // set value to remainder after dividing by 20201227
        }
        return value;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            cardPubKey = Integer.parseInt(br.readLine());
            doorPubKey = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
