package org.jgoeres.adventofcode2020.Day06;

import org.jgoeres.adventofcode2020.common.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jgoeres.adventofcode2020.common.Constants.EMPTY;

public class Day06Service {
    private final String DEFAULT_INPUTS_PATH = "data/day06/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Group> groups = new ArrayList<>();

    public Day06Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day06Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /** For each group, count the number of questions to which anyone answered "yes".
         * What is the sum of those counts?
         **/
        for (Group group : groups) {
            result += group.totalYesAnswers();
        }
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** For each group, count the number of questions to which everyone answered "yes".
         * What is the sum of those counts?
         **/
        for (Group group : groups) {
            result += group.everyoneYesAnswers();
        }

        return result;
    }

    /**
     * Each group's answers are separated by a blank line, and within each group, each person's answers are on a single
     * line. For example:
     * <p>
     * abc
     * <p>
     * a b c
     * <p>
     * ab ac
     * <p>
     * a a a a
     * <p>
     * b
     */
    private void loadInputs(String pathToFile) {
        groups.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Group group = new Group();
            while ((line = br.readLine()) != null) {
                if (!line.equals(EMPTY)) {
                    Passenger passenger = new Passenger(); // start a new passenger
                    // split the line into characters and add them into the current passenger
                    line.chars().mapToObj(i -> (char) i)
                            .forEach(answer -> passenger.addAnswer(answer));
                    // Add the passenger into the current group
                    group.addPassenger(passenger);
                } else {
                    // line is empty so we've finished a group; start a new one
                    groups.add(group);
                    group = new Group();
                }
            }
            // When we're done, add the leftover group to the list
            groups.add(group);
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
