package org.jgoeres.adventofcode2020.Day11;

import org.jgoeres.adventofcode2020.common.Direction8Way;
import org.jgoeres.adventofcode2020.common.Utils.Pair;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jgoeres.adventofcode2020.Day11.Seat.*;

public class SeatingSystemService {
    private final String DEFAULT_INPUTS_PATH = "data/day11/input.txt";

    private static boolean DEBUG = false;

    private HashMap<XYPoint, Seat> seatMap = new HashMap<>();
    private HashMap<XYPoint, Seat> nextSeatMap = new HashMap<>();

    public SeatingSystemService() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public SeatingSystemService(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /** Put problem implementation here **/

        Pair seatMaps = new Pair(seatMap, nextSeatMap);
        while (true) {
            // Go until we stop
            boolean changed = calculateNextSeatMap();
            if (!changed) {
                // If nothing changed, stop!
                break;
            } else {
                // Swap the "next" seat map into "current" and keep going
                seatMaps.swap();
                seatMap = (HashMap<XYPoint, Seat>) seatMaps.getFirst();
                nextSeatMap = (HashMap<XYPoint, Seat>) seatMaps.getSecond();
            }
        }
        // When we're done, count the occupied seats
        int occupiedCount = (int) seatMap.values().stream().filter(p -> p.isOccupied()).count();
        return occupiedCount;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    private boolean calculateNextSeatMap() {
        // Flag to track when something changes
        boolean changed = false;

        // Process all the seats in the map
        for (XYPoint xy : seatMap.keySet()) {
            int neighborsCount = countOccupiedNeighbors(xy);
            Seat currentSeat = seatMap.get(xy);
            if (currentSeat.isEmpty() && neighborsCount == 0) {
                // If a seat is empty (L) and there are no occupied seats adjacent to it,
                // the seat becomes occupied.
                nextSeatMap.get(xy).setOccupied(OCCUPIED);
                changed = true;
            } else if (currentSeat.isOccupied() && neighborsCount >= 4) {
                // If a seat is occupied (#) and four or more seats adjacent to it
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

    private int countOccupiedNeighbors(XYPoint xySeat) {
        // Check all 8 (potential) neighbors of the given seat, and count how many are OCCUPIED
        int count = 0;
        Seat seat = seatMap.get(xySeat);
        for (Direction8Way direction8Way : Direction8Way.values()) {
//            if (seatMap.containsKey(seat.getNeighbor(direction8Way))
//                    && seat.getNeighbor(direction8Way).isOccupied()) {
            XYPoint neighbor = xySeat.getRelativeLocation8Way(direction8Way);
            if (seatMap.containsKey(neighbor)
                    && seatMap.get(neighbor).isOccupied()) {
                count++;
                if (count >= 4) {
                    // If a seat is occupied (#) and four or more seats adjacent
                    // to it are also occupied, the seat becomes empty.
                    // (so we can stop counting when we find 4 occupied neighbors)
                    return count;
                }
            }
        }
        return count;
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
                        // Add this (empty) seat to the seatmap
                        seatMap.put(new XYPoint(x, y), new Seat(UNOCCUPIED));
                        // And a copy of it to the next seatmap
                        nextSeatMap.put(new XYPoint(x, y), new Seat(UNOCCUPIED));
                    }
                    x++;
                }
                y++;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
