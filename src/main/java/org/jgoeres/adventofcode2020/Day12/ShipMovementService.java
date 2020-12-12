package org.jgoeres.adventofcode2020.Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShipMovementService {
    private static final String DEFAULT_INPUTS_PATH = "data/day12/input.txt";

    private static final boolean DEBUG = false;

    private final ArrayList<Move> moveList = new ArrayList<>();

    public ShipMovementService() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public ShipMovementService(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result;
        /** Figure out where the navigation instructions lead.
         *
         * What is the Manhattan distance between that location and the ship's starting position?
         **/
        Ship ship = new Ship();
        for (Move move : moveList) {
            ship.move(move);
        }

        // Find the Manhattan location of the ship
        int absX = Math.abs(ship.location.getX());
        int absY = Math.abs(ship.location.getY());
        result = absX + absY;
        return result;
    }

    public int doPartB() {
        int result;
        /** Figure out where the navigation instructions actually lead.
         * What is the Manhattan distance between that location
         * and the ship's starting position?
         **/
        // Get a new ship
        Ship ship = new Ship();
        for (Move move : moveList) {
            ship.followWaypoint(move);
        }

        // Find the Manhattan location of the ship
        int absX = Math.abs(ship.location.getX());
        int absY = Math.abs(ship.location.getY());
        result = absX + absY;
        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        moveList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            /** The navigation instructions (your puzzle input) consists of a sequence of single-character actions paired with integer input values. After staring at them for a few minutes, you work out what they probably mean:
             *       Action N means to move north by the given value.
             *       Action S means to move south by the given value.
             *       Action E means to move east by the given value.
             *       Action W means to move west by the given value.
             *       Action L means to turn left the given number of degrees.
             *       Action R means to turn right the given number of degrees.
             *       Action F means to move forward by the given value in the direction the ship is currently facing.
             **/
            Pattern p = Pattern.compile("(\\w)(\\d+)");
            while ((line = br.readLine()) != null) {
                // process the line.
                Matcher m = p.matcher(line);
                if (m.find()) { // If our regex matched this line
                    // Parse it
                    String direction = m.group(1);
                    Integer distance = Integer.parseInt(m.group(2));
                    // Create the specified move operation
                    Move move = new Move(direction, distance);
                    moveList.add(move);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
