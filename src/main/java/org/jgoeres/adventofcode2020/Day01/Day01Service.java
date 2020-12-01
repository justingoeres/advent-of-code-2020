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

    public int calculateMissingExpenseThreeArguments(int targetSum) {
        // Find the THREE entries that sum to 2020
        int result;

        // inputList is probably already sorted because we just ran problem A,
        // but sort it here just in case.
        Collections.sort(inputList);

        int i;
        int j;
        int k;
        int smallNum = 0;
        int middleNum = 0;
        int largeNum = 0;
        int sum = 0;

        boolean found = false;
        all:
        for (i = 0; i < inputList.size() - 2; i++) { // -2 because j & k will get to the end of inputList; i will not
            for (j = 1; j < inputList.size() - 1; j++) { // -1 because k will get to the end of inputList; j will not
                for (k = inputList.size() - 1; k > j; k--) {
                    smallNum = inputList.get(i);
                    middleNum = inputList.get(j);
                    largeNum = inputList.get(k);

                    sum = smallNum + middleNum + largeNum;

                    if (DEBUG) System.out.println(smallNum + " + " + middleNum + " + " + largeNum + " = " + sum);

                    if (sum == targetSum) {
                        // we found it! Bail out completely
                        break all;
                    } else if (sum < targetSum) {
                        // we undershot the target, go to the next j and start again
                        break;
                    }
                }
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
