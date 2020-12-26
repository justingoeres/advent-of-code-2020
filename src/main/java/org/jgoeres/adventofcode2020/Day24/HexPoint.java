package org.jgoeres.adventofcode2020.Day24;

public class HexPoint {
    int x;
    int y;

    public HexPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{ " + x + ", " + y + "}";
    }
}
