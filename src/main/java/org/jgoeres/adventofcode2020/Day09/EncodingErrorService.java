package org.jgoeres.adventofcode2020.Day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncodingErrorService {
    private static final String DEFAULT_INPUTS_PATH = "data/day09/input.txt";

    private static final boolean DEBUG = false;

    private final ArrayList<Long> inputList = new ArrayList<>();

    public EncodingErrorService() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public EncodingErrorService(String pathToFile) {
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
                System.out.println("Invalid value found at index " + index);
                result = inputList.get(index);
                return result;
            }
        }
    }

    public long doPartB(long target) {
        long result;
        /** The final step in breaking the XMAS encryption relies on the invalid number you just found:
         * you must find a contiguous set of at least two numbers in your list which sum to the
         * invalid number from step 1.
         *
         * To find the encryption weakness, add together the smallest and largest number
         * in this contiguous range; in this example, these are 15 and 47, producing 62.
         *
         * What is the encryption weakness in your XMAS-encrypted list of numbers?
         **/

        int targetIndex = inputList.indexOf(target);

        // In general, the larger numbers are going to be CLOSER to the target index (i.e. near the top)
        // Also, if at any point the contiguous sum is GREATER THAN the target, that segment is invalid
        // and we can move on

        int topIndex = targetIndex - 1; // Start just below the target
        int bottomIndex = topIndex; // Start at the top, we decrement right away

        // Go until we get an answer and break
        while (true) {
            long sum = inputList.get(topIndex);  // start with the value at the top
            while (sum < target) {
                bottomIndex--;
                // Keep going as long as our sum is less than the target
                sum += inputList.get(bottomIndex);  // add the item at the bottom
            }
            // When we get here, our sum is either:
            //      == target : (yay!) or
            //      > target :  (decrement topIndex and start over)
            if (sum == target) {
                // We found it! Now find the smallest & largest numbers in the range
                List<Long> resultList = inputList.subList(bottomIndex, topIndex + 1);

                long min = Collections.min(resultList);
                long max = Collections.max(resultList);
                System.out.println("Encryption weakness found at index " + inputList.indexOf(min) + " (" + min + ")"
                        + " & " + inputList.indexOf(max) + " (" + max + ")");
                result = min + max;
                return result;
            } else {
                // We didn't find it yet. Decrement topIndex, reset, and start over
                topIndex--;
                bottomIndex = topIndex;
            }
        }
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
