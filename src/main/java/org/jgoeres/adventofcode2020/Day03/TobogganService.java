package org.jgoeres.adventofcode2020.Day03;

import org.jgoeres.adventofcode2020.common.XYPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static org.jgoeres.adventofcode2020.common.DirectionURDL.DOWN;
import static org.jgoeres.adventofcode2020.common.DirectionURDL.RIGHT;

public class TobogganService {
    private final String DEFAULT_INPUTS_PATH = "data/day03/input.txt";

    private static boolean DEBUG = false;
    private char TREE = '#';

    private ArrayList<Integer> inputList = new ArrayList<>();
    private Forest forest = new Forest(0, 0); // toboggan starts at 0,0

    int slopeX = 0;
    int slopeY = 0;

    public TobogganService() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public TobogganService(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        /**
         * Starting at the top-left corner of your map and
         * following a slope of right 3 and down 1, how many trees would you encounter?
         */
        int treeCount = 0;
        slopeX = 3;
        slopeY = 1;
        // As long as the toboggan is in the forest
        while (forest.tobogganIsInside()) {
            // Check if the current location is a tree.
            if (forest.tobogganIsOnTree()) {
                treeCount++;
            }
            // Per problem, 3 right & 1 down
            forest.moveToboggan(slopeX, RIGHT);
            forest.moveToboggan(slopeY, DOWN);
        }
        // Return the number of trees we hit
        return treeCount;
    }

    public long doPartB() {
        /**
         * Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left corner and traverse the map all the way to the bottom:
         *
         * Right 1, down 1.
         * Right 3, down 1. (This is the slope you already checked.)
         * Right 5, down 1.
         * Right 7, down 1.
         * Right 1, down 2.
         */

        long result = 1; // start from 1 because we're going to multiple treeCount by this

        // Set up all the slopes we need to check
        ArrayList<XYPoint> slopes = new ArrayList<>();
        slopes.add(new XYPoint(1, 1));
        slopes.add(new XYPoint(3, 1));
        slopes.add(new XYPoint(5, 1));
        slopes.add(new XYPoint(7, 1));
        slopes.add(new XYPoint(1, 2));

        // Check each one and multiply them together
        for (XYPoint slope : slopes) {
            forest.resetToboggan();
            int treeCount = 0;
            slopeX = slope.getX();
            slopeY = slope.getY();
            // As long as the toboggan is in the forest
            while (forest.tobogganIsInside()) {
                // Check if the current location is a tree.
                if (forest.tobogganIsOnTree()) {
                    treeCount++;
                }
                // Per problem, 3 right & 1 down
                forest.moveToboggan(slopeX, RIGHT);
                forest.moveToboggan(slopeY, DOWN);
            }
            // multiple the running result by the number of trees we hit
            result *= treeCount;
        }
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
