package org.jgoeres.adventofcode2020.Day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Day01Service {
    private final String DAY = "01";
    private final String DEFAULT_INPUTS_PATH = "data/day" + DAY + "/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();

    public Day01Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day01Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int calculateMissingExpense(int targetSum) {
        // Find two entries that sum to targetSum, and multiply them.
        int result;

        // To do things faster, sort the list first from low to high
        Collections.sort(inputList);

        int i = 0;
        int j = inputList.size() - 1;
        int smallerNum = 0;
        int largerNum = 0;
        int sum = 0;
        // Work from the front and back to find the two items that sum to the target
        boolean found = false;
        while (!found) {
            smallerNum = inputList.get(i);
            largerNum = inputList.get(j);

            sum = smallerNum + largerNum;

            if (DEBUG) System.out.println(smallerNum + " + " + largerNum + " = " + sum);
            if (sum == targetSum) {
                // We found it!
                break;
            } else if (sum > targetSum) {
                // If we're greater than targetSum, then move the LARGER number down.
                j--;
            } else if (sum < targetSum) {
                // If we're under the targetSum, move the SMALLER number up.
                i++;
            }
        }
        System.out.println("Found result:");
        System.out.println("\t" + smallerNum + " + " + largerNum + " = " + sum);
        // Now that we have the target numbers, just multiply them.
        result = smallerNum * largerNum;
        System.out.println("\t" + smallerNum + " * " + largerNum + " = " + result);
        return result;
    }


    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            while ((line = br.readLine()) != null) {
                // process the line.
                nextInt = Integer.parseInt(line);

                inputList.add(nextInt);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
