package org.jgoeres.adventofcode2020.Day11;

import org.jgoeres.adventofcode2020.common.Direction8Way;
import org.jgoeres.adventofcode2020.common.Utils.Pair;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.jgoeres.adventofcode2020.Day11.Seat.*;
import static org.jgoeres.adventofcode2020.common.Direction8Way.*;

public class SeatingSystemService {
    private final String DEFAULT_INPUTS_PATH = "data/day11/input.txt";

    private static boolean DEBUG = false;

    private HashMap<XYPoint, Seat> seatMap = new HashMap<>();
    private HashMap<XYPoint, Seat> nextSeatMap = new HashMap<>();

    Pair seatMaps = new Pair(seatMap, nextSeatMap);
    int seatMaxX = 0;
    int seatMaxY = 0;

    static List<Direction8Way> toCheck = new ArrayList<>();

    public SeatingSystemService() {
        // Because we setup neighbors during input loading,
        // we only need to check L, UL, and UR directions
        toCheck.add(LEFT);
        toCheck.add(UP_LEFT);
        toCheck.add(UP);
        toCheck.add(UP_RIGHT);
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public SeatingSystemService(String pathToFile) {
        // Because we setup neighbors during input loading,
        // we only need to check L, UL, and UR directions
        toCheck.add(LEFT);
        toCheck.add(UP_LEFT);
        toCheck.add(UP);
        toCheck.add(UP_RIGHT);
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int THRESHOLD_PART_A = 4;
        /** Put problem implementation here **/
        int generation = 0;
        while (run(THRESHOLD_PART_A)) {
            // Go until we stop
            if (DEBUG) {
                System.out.println("Generation #\t" + generation);
                generation++;
            }
        }
        // When we're done, count the occupied seats
        int occupiedCount = (int) seatMap.values().stream().filter(p -> p.isOccupied()).count();
        return occupiedCount;
    }

    public int doPartB() {
        int THRESHOLD_PART_B = 5;
        /**
         * Given the new visibility method and the rule change for occupied seats becoming empty,
         *  once equilibrium is reached, how many seats end up occupied?
         **/

        // Calculate the (additional) neighbors based on line-of-sight
        // (note this will go faster if we've already done Part A)
        toCheck = Arrays.asList(Direction8Way.values());   // Now we check every direction
        for (Seat seat : seatMap.values()) {
            seat.setOccupied(false); // reset the seat to empty
            setupNeighborsPartB(seat, seatMap);
        }
        for (Seat seat : nextSeatMap.values()) {
            seat.setOccupied(false);
            setupNeighborsPartB(seat, nextSeatMap);
        }

        /** Put problem implementation here **/
        int generation = 0;
        while (run(THRESHOLD_PART_B)) {
            // Go until we stop
            if (DEBUG) {
                System.out.println("Generation #\t" + generation);
                generation++;
            }
        }
        // When we're done, count the occupied seats
        int occupiedCount = (int) seatMap.values().stream().filter(p -> p.isOccupied()).count();
        return occupiedCount;
    }

    private boolean run(int threshold) {
        // Run a timer tick to calculate the next seatMap.
        // Return true if something changed (i.e. if we should continue)
        boolean changed = calculateNextSeatMap(threshold);
        if (changed) {
            // Swap the "next" seat map into "current" and keep going
            seatMaps.swap();
            seatMap = (HashMap<XYPoint, Seat>) seatMaps.getFirst();
            nextSeatMap = (HashMap<XYPoint, Seat>) seatMaps.getSecond();
        }
        return changed;
    }

    private boolean calculateNextSeatMap(int threshold) {
        // Flag to track when something changes
        boolean changed = false;

        // Process all the seats in the map
        for (XYPoint xy : seatMap.keySet()) {
            int neighborsCount = countOccupiedNeighbors(xy, threshold);
            Seat currentSeat = seatMap.get(xy);
            if (currentSeat.isEmpty() && neighborsCount == 0) {
                // If a seat is empty (L) and there are no occupied neighboring seats,
                // the seat becomes occupied.
                nextSeatMap.get(xy).setOccupied(OCCUPIED);
                changed = true;
            } else if (currentSeat.isOccupied() && neighborsCount >= threshold) {
                // If a seat is occupied (#) and 'threshold' or more neighboring seats
                // are also occupied, the seat becomes empty.
                nextSeatMap.get(xy).setOccupied(UNOCCUPIED);
                changed = true;
            } else {
                // Otherwise, the seat's state does not change
                nextSeatMap.get(xy).setOccupied(currentSeat.isOccupied());
            }
        }
        return changed; // Returns TRUE if something changed states, FALSE if nothing changed
    }

    private int countOccupiedNeighbors(XYPoint xySeat, int threshold) {
        // Check all 8 (potential) neighbors of the given seat, and count how many are OCCUPIED
        int count = 0;
        Seat seat = seatMap.get(xySeat);
        count = seat.countOccupiedNeighbors(threshold);
//        for (Direction8Way direction8Way : Direction8Way.values()) {
////            if (seatMap.containsKey(seat.getNeighbor(direction8Way))
////                    && seat.getNeighbor(direction8Way).isOccupied()) {
//            XYPoint neighbor = xySeat.getRelativeLocation8Way(direction8Way);
//            if (seatMap.containsKey(neighbor)
//                    && seatMap.get(neighbor).isOccupied()) {
//                count++;
//                if (count >= 4) {
//                    // If a seat is occupied (#) and four or more seats adjacent
//                    // to it are also occupied, the seat becomes empty.
//                    // (so we can stop counting when we find 4 occupied neighbors)
//                    return count;
//                }
//            }
//        }
        return count;
    }

    private void setupNeighborsPartA(Seat sourceSeat, HashMap<XYPoint, Seat> sourceSeatMap) {
        // Find all the adjacent seats in the 'toCheck' list
        // and set up a bidirectional "neighbor" relationship between them
        XYPoint sourceXY = sourceSeat.getLocation();
        for (Direction8Way direction8Way : toCheck) {
            XYPoint targetXY = sourceXY.getRelativeLocation8Way(direction8Way);
            if (sourceSeatMap.containsKey(targetXY)) {
                // If we found a neighbor
                Seat targetSeat = sourceSeatMap.get(targetXY);
                // Add each seat to the other's neighbors
                sourceSeat.addNeighbor(direction8Way, targetSeat);
                targetSeat.addNeighbor(direction8Way.opposite(), sourceSeat);
            }
        }
    }

    private void setupNeighborsPartB(Seat sourceSeat, HashMap<XYPoint, Seat> sourceSeatMap) {
        // Find all the adjacent seats in the 'toCheck' list
        // and set up a bidirectional "neighbor" relationship between them
        XYPoint sourceXY = sourceSeat.getLocation();
        for (Direction8Way direction8Way : toCheck) {
            if (!sourceSeat.hasNeighbor(direction8Way)) {
                // If this seat does NOT already have a neighbor in this direction
                // walk outward in the current direction until we either find a seat or an edge
                int distance = 1;
                while (true) { // do it until we break by going out of bounds or finding a seat
                    XYPoint targetXY = sourceXY.getRelativeLocation8Way(distance, direction8Way);
                    // Is the target out of bounds?
                    if (targetXY.getX() < 0 || targetXY.getX() > seatMaxX
                            || targetXY.getY() < 0 || targetXY.getY() > seatMaxY) {
                        // target is out of bounds, jump to next direction
                        break;
                    } else {
                        // target is still in-bounds, is there a seat there?
                        if (sourceSeatMap.containsKey(targetXY)) {
                            // If we found a neighbor
                            Seat targetSeat = sourceSeatMap.get(targetXY);
                            // Add each seat to the other's neighbors
                            sourceSeat.addNeighbor(direction8Way, targetSeat);
                            targetSeat.addNeighbor(direction8Way.opposite(), sourceSeat);
                            // and jump to the next direction
                            break;
                        } else {
                            // No seat yet, take the next step outward.
                            distance++;
                        }
                    }
                }
            }
        }
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        seatMap.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            int y = 0;
            while ((line = br.readLine()) != null) {
                // process the line.
                // Search character-by-character and create the seat map
                int x = 0;
                for (char pos : line.toCharArray()) {
                    if (pos == SEAT) {
                        XYPoint location = new XYPoint(x, y);
                        // Add this (empty) seat to the seatmap
                        Seat newSeat = new Seat(location, UNOCCUPIED);
                        seatMap.put(location, newSeat);
                        setupNeighborsPartA(newSeat, seatMap);
                        // And a copy of it to the next seatmap
                        Seat newNextSeat = new Seat(location, UNOCCUPIED);
                        nextSeatMap.put(location, newNextSeat);
                        setupNeighborsPartA(newNextSeat, nextSeatMap);
                    }
                    if (x > seatMaxX) seatMaxX = x;
                    x++;
                }
                if (y > seatMaxY) seatMaxY = y;
                y++;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
