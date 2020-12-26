package org.jgoeres.adventofcode2020.Day24;

import org.jgoeres.adventofcode2020.common.DirectionHexPointy;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.util.HashMap;

import static org.jgoeres.adventofcode2020.Day24.Tile.Side.*;

public class Tile {
    enum Side {
        WHITE,
        BLACK
    }

    public static HashMap<XYPoint, Tile> floor = new HashMap<>();

    private Side side = WHITE;  // Everybody starts out white
    private XYPoint location;
    private HashMap<DirectionHexPointy, Tile> neighbors = new HashMap<>();
    public boolean shouldFlip = false;

    public Tile(XYPoint xy) {
        location = xy;
    }

    public Tile(int x, int y) {
        location = new XYPoint(x, y);
    }

    public static Tile getOrCreate(int x, int y) {
        XYPoint xy = new XYPoint(x, y);
        return getOrCreate(xy);
    }

    public static Tile getOrCreate(XYPoint xy) {
        if (floor.containsKey(xy)) return floor.get(xy);    // Tile exists, return it
        else {  // Tile does not exist, create it
            Tile tile = new Tile(xy);
            tile.location.set(xy.getX(), xy.getY());
            floor.put(xy, tile);
            return tile;
        }
    }

    public boolean hasNeighbor(DirectionHexPointy direction) {
        return (neighbors.containsKey(direction));
    }

    public void flip() {
        if (side == WHITE) side = BLACK;
        else if (side == BLACK) side = WHITE;

        shouldFlip = false;
    }

    public void calculateShouldFlip() {
        int count = countNeighbors(BLACK);
        switch (side) {
            // Any black tile with zero or more than 2 black tiles
            // immediately adjacent to it is flipped to white.
            case BLACK:
                shouldFlip = (count == 0 || count > 2);
                break;
            case WHITE:
                // Any white tile with exactly 2 black tiles immediately
                // adjacent to it is flipped to black.
                shouldFlip = (count == 2);
                break;
        }
    }

    private int countNeighbors(Side color) {
        int count = 0;
        for (Tile neighbor : neighbors.values()) {
            count += (neighbor.side == color) ? 1 : 0;
        }
        return count;
    }

    public Tile getNeighbor(DirectionHexPointy direction) {
        // See if WE already have this neighbor. If so, return it
        if (neighbors.containsKey(direction)) return neighbors.get(direction);
        // Create the requested tile, and link it both ways
        XYPoint neighborXY = this.location.getRelativeLocationHexPointy(direction);
        Tile neighbor = getOrCreate(neighborXY);
        addNeighbor(neighbor, direction);
        return neighbor;
    }

    public void fillNeighbors() {
        // Scan all the neighbors and add any that are missing
        if (neighbors.size() != 6) {  // quick check to see if we have them all and return if we do
            for (DirectionHexPointy direction : DirectionHexPointy.values()) {
                Tile neighbor = getNeighbor(direction); // just "getting" the neighbor creates it.
                // Having gotten the (new) neighbor, make sure all ITS neighbors are wired up.
                // But don't *create* any more new tiles here – just wire to existing ones
                for (DirectionHexPointy neighborDir : DirectionHexPointy.values()) {
                    XYPoint neighborNeighborXY = neighbor.getLocation().getRelativeLocationHexPointy(neighborDir);
                    if (floor.containsKey(neighborNeighborXY)) {
                        // If a tile that WOULD be a neighbor of the neighbor exists
                        // Wire to it
                        Tile neighborNeighbor = floor.get(neighborNeighborXY);
                        neighbor.addNeighbor(neighborNeighbor, neighborDir);
                    }
                }
            }
        }
    }

    public void addNeighbor(Tile neighbor, DirectionHexPointy directionHexPointy) {
        neighbors.put(directionHexPointy, neighbor);
        // Make sure we're the opposite neighbor
        neighbor.neighbors.put(directionHexPointy.opposite(), this);
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public XYPoint getLocation() {
        return location;
    }
}

