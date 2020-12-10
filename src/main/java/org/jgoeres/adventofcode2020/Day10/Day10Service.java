package org.jgoeres.adventofcode2020.Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10Service {
    private final String DEFAULT_INPUTS_PATH = "data/day10/input.txt";

    private static boolean DEBUG = true;

    private HashSet<Integer> adapterSet = new HashSet<>();

    public Day10Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day10Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /** Find a chain that uses all of your adapters to connect the charging outlet
         * to your device's built-in adapter and count the joltage differences between
         * the charging outlet, the adapters, and your device.
         *
         * What is the number of 1-jolt differences multiplied by the number
         * of 3-jolt differences? **/

        // your device has a built-in joltage adapter rated
        // for 3 jolts higher than the highest-rated adapter in your bag.
        int deviceJoltage = Collections.max(adapterSet) + 3;
        adapterSet.add(deviceJoltage);

        // Treat the charging outlet near your seat as having an effective joltage rating of 0.
        int portJoltage = 0;

        int ones = 0;
        int threes = 0;

        int previousJoltage = portJoltage; // start from the port
        for (int jolts = 1; jolts <= deviceJoltage; jolts++) {
            // Cycle through all possible voltages and count up the differences
            if (adapterSet.contains(jolts)) {
                // Find the difference between this and the last joltage
                int difference = jolts - previousJoltage;
                if (difference == 1) ones++;
                else if (difference == 3) threes++;
                previousJoltage = jolts;
            }
        }
        result = ones * threes;
        if (DEBUG) {
            System.out.println("Ones (" + ones + ") x Threes (" + threes + ") = " + result);
        }
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs line-by-line as integers
    private void loadInputs(String pathToFile) {
        adapterSet.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            while ((line = br.readLine()) != null) {
                // process the line.
                nextInt = Integer.parseInt(line);
                adapterSet.add(nextInt);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
