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
        while (true) {
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

    public int calculateMissingExpenseThreeArguments(int targetSum) {
        // Find the THREE entries that sum to 2020
        int result;

        // inputList is probably already sorted because we just ran problem A,
        // but sort it here just in case.
        Collections.sort(inputList);

        int i;
        int j;
        int k;
        int smallNum;
        int middleNum;
        int largeNum;
        int sum;

        int iteration = 0;

        i = 0;
        j = 1;
        k = inputList.size() - 1;
        while (true) {
            if (j == k) {
                // If j & k have met in the middle with no solution, increment i and start over
                i++;
                j = i + 1;
                k = inputList.size() - 1;
            }
            smallNum = inputList.get(i);    // get the small number
            // Iterate the middle & large numbers in from both ends
            middleNum = inputList.get(j);
            largeNum = inputList.get(k);

            // Look for j & k to make a sum that adds up to (2020 - smallNum)
            int subTarget = targetSum - smallNum;
            sum = middleNum + largeNum;

            if (DEBUG) System.out.println(smallNum + " + " + middleNum + " + " + largeNum + " = " + sum);

            if (sum == subTarget) {
                // We found it!
                break;
            } else if (sum > subTarget) {
                // If we're greater than targetSum, then move the LARGER number down.
                k--;
            } else if (sum < subTarget) {
                // If we're under the targetSum, move the SMALLER number up.
                j++;
            }
        }

        System.out.println("Found result:");
        System.out.println("\t" + smallNum + " + " + middleNum + " + " + largeNum + " = " + sum);
        // Now that we have the target numbers, just multiply them.
        result = smallNum * middleNum * largeNum;
        System.out.println("\t" + smallNum + " * " + middleNum + " * " + largeNum + " = " + result);
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
