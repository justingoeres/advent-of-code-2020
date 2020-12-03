package org.jgoeres.adventofcode2020.Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.jgoeres.adventofcode2020.common.DirectionURDL.DOWN;
import static org.jgoeres.adventofcode2020.common.DirectionURDL.RIGHT;

public class TobogganService {
    private final String DEFAULT_INPUTS_PATH = "data/day03/input.txt";

    private static boolean DEBUG = false;
    private char TREE = '#';

    private ArrayList<Integer> inputList = new ArrayList<>();
    private Forest forest = new Forest(0, 0); // toboggan starts at 0,0

    public TobogganService() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public TobogganService(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int treeCount = 0;
        // As long as the toboggan is in the forest
        while (forest.tobogganIsInside()) {
            // Check if the current location is a tree.
            if (forest.tobogganIsOnTree()) {
                treeCount++;
            }
            // Per problem, 3 right & 1 down
            forest.moveToboggan(3, RIGHT);
            forest.moveToboggan(1, DOWN);
        }
        // Return the number of trees we hit
        return treeCount;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs character by character and create a tree at each '#'
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            int y = 0;
            // Find all the trees and put them in the forest
            while ((line = br.readLine()) != null) {
                // Set the forest width when we read the first line
                if (forest.getSizeX() == null) forest.setSizeX(line.length());
                // Find all the trees on this line
                for (int x = 0; x < line.length(); x++) {
                    if (line.charAt(x) == TREE) {
                        forest.addTree(x, y);
                    }
                }
                y++;    // go to the next line
            }
            // Set the forest height based on the number of lines we read
            forest.setSizeY(y);
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
