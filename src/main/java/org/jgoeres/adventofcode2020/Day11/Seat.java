package org.jgoeres.adventofcode2020.Day11;

import org.jgoeres.adventofcode2020.common.Direction8Way;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.util.HashMap;

public class Seat {
    static final char SEAT = 'L';
    static final char FLOOR = '.';
    static final char OCCUPIED_CHAR = '#';

    static final boolean OCCUPIED = true;
    static final boolean UNOCCUPIED = false;

    static boolean somethingHasChanged;

    private XYPoint location;
    private boolean isOccupied;
    private HashMap<Direction8Way, Seat> neighbors = new HashMap<>();

    public Seat(XYPoint location, boolean isOccupied) {
        this.location = location;
        this.isOccupied = isOccupied;
    }

    public XYPoint getLocation() {
        return location;
    }

    public void addNeighbor(Direction8Way direction8Way, Seat neighbor) {
        neighbors.put(direction8Way, neighbor);
    }

    public Seat getNeighbor(Direction8Way direction8Way) {
        return neighbors.get(direction8Way);
    }

    public int countOccupiedNeighbors() {
        int count = 0;
        for (Seat neighborSeat : neighbors.values()) {
            if (neighborSeat.isOccupied())
                count++;
            if (count >= 4) {
                // If a seat is occupied (#) and four or more seats adjacent
                // to it are also occupied, the seat becomes empty.
                // (so we can stop counting when we find 4 occupied neighbors)
                return count;
            }
        }
        return count;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public boolean isEmpty() {
        return !isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
