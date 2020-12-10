package org.jgoeres.adventofcode2020.Day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

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

    public Long doPartB() {
        Long result = 0L;
        /** What is the total number of distinct ways you can arrange
         * the adapters to connect the charging outlet to your device?
         **/

        // Build a tree of all the adapters and what they can connect to
        // The root adapter is the port itself
        HashMap<Integer, AdapterNode> allAdapters = new HashMap<>();

        // Create a map of AdapterNodes from all known Adapters
        for (Integer joltage : adapterSet) {
            AdapterNode portNode = new AdapterNode(joltage);
            allAdapters.put(joltage, portNode);
        }

        int portJoltage = 0;
        int deviceJoltage = Collections.max(adapterSet) + 3;

        AdapterNode portNode = new AdapterNode(portJoltage);
        allAdapters.put(portJoltage, portNode);
        AdapterNode deviceNode = new AdapterNode(deviceJoltage);
        allAdapters.put(deviceJoltage, deviceNode);


        // Attach them to each other
        for (Integer joltage : allAdapters.keySet()) {
            // For each adapter joltage
            AdapterNode adapter = allAdapters.get(joltage);
            for (int j = joltage + 1; j <= joltage + 3; j++) {
                // Check the joltages 1-3 jolts above this adapter, and connect them
                if (allAdapters.containsKey(j)) {
                    // We have an adapter for this joltage
                    // Make it a child of us
                    adapter.addChild(allAdapters.get(j));
                    // Make us a parent of it
                    allAdapters.get(j).addParent(adapter);
                }
            }
        }

        // There is one "path" from the device to itself
        deviceNode.setTotalNumPaths(1L);
        // Now calculate all paths from the port to the device
        result = portNode.calculateTotalNumPaths();
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
