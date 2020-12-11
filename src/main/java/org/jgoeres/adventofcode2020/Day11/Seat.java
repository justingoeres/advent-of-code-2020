package org.jgoeres.adventofcode2020.Day11;

import org.jgoeres.adventofcode2020.common.Direction8Way;

import java.util.HashMap;

public class Seat {
    static final char SEAT = 'L';
    static final char FLOOR = '.';
    static final char OCCUPIED_CHAR = '#';

    static final boolean OCCUPIED = true;
    static final boolean UNOCCUPIED = false;

    static boolean somethingHasChanged;

    private boolean isOccupied;
    private HashMap<Direction8Way, Seat> neighbors = new HashMap<>();

    public Seat(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public void addNeighbor(Direction8Way direction8Way, Seat neighbor) {
        neighbors.put(direction8Way, neighbor);
    }

    public Seat getNeighbor(Direction8Way direction8Way) {
        return neighbors.get(direction8Way);
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
