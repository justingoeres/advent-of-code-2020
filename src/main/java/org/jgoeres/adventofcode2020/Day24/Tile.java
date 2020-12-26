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

    public void flip() {
        if (side == WHITE) side = BLACK;
        else if (side == BLACK) side = WHITE;
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

