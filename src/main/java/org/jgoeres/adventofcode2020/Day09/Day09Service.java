package org.jgoeres.adventofcode2020.Day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day09Service {
    private final String DEFAULT_INPUTS_PATH = "data/day09/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Long> inputList = new ArrayList<>();

    public Day09Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day09Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public long doPartA(int preambleLength) {
        long result;
        /** The first step of attacking the weakness in the XMAS data is to
         * find the first number in the list (after the preamble) which is
         * not the sum of two of the 25 numbers before it.
         *
         * What is the first number that does not have this property?
         * **/
        int index = preambleLength; // start immediately after the preamble

        ArrayList<Long> searchList = new ArrayList<>();
        while (true) {
            boolean valid = false; // Assume invalid, and check for validity
            // Find out if the element at 'index' can be made by
            // the sum of two integers in the previous 'preambleLength' elements

            int topIndex = index;    // top is previous element
            int bottomIndex = index - preambleLength;    // bottom is preambleLength elements back

            long target = inputList.get(index);

            searchList.clear();
            // Get the portion of the list we're searching over
            searchList.addAll((inputList.subList(bottomIndex, topIndex)));
            // ... and sort it so we can search efficiently
            Collections.sort(searchList);

            // Now re-home topIndex & bottomIndex to the searchList
            bottomIndex = 0;
            topIndex = searchList.size() - 1;
            while (topIndex != bottomIndex) {
                long top = searchList.get(topIndex);
                long bottom = searchList.get(bottomIndex);
                // creep in from the top & bottom until we meet in the middle
                if (DEBUG) {
                    System.out.println("1:\t" + bottomIndex + "\t" + topIndex + "\t" + inputList.get(index));
                }
                if (top + bottom > target) {
                    // sum is too big, move top down
                    topIndex--;
                } else if (top + bottom < target) {
                    // sum is too small, move bottom up
                    bottomIndex++;
                } else {
                    // We found two numbers that sum to target,
                    // so move on to the next iteration
                    valid = true;
                    index++;
                    break;
                }
            }
            // If we get here, we're done iterating. If we found a valid combination, go to the next iteration.
            if (!valid) {
                // we did NOT find a valid combo, so we're done searching!
                result = inputList.get(index);
                return result;
            }
        }
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs line-by-line as Integers
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                inputList.add(Long.parseLong(line));
            }
        } catch (
                Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
