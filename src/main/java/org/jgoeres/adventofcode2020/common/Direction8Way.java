package org.jgoeres.adventofcode2020.common;

import java.util.HashMap;
import java.util.Map;

import static org.jgoeres.adventofcode2020.common.Rotation.CLOCKWISE;
import static org.jgoeres.adventofcode2020.common.Rotation.COUNTERCLOCKWISE;

public enum Direction8Way {
    UP("U"),
    UP_RIGHT("UR"),
    RIGHT("R"),
    DOWN_RIGHT("DR"),
    DOWN("D"),
    DOWN_LEFT("DL"),
    LEFT("L"),
    UP_LEFT("UL");

    private final String directionString;

    //****** Reverse Lookup Implementation************//

    //Lookup table
    private static final Map<String, Direction8Way> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static {
        for (Direction8Way dir : Direction8Way.values()) {
            lookup.put(dir.getDirectionString(), dir);
        }
    }

    //This method can be used for reverse lookup purpose
    public static Direction8Way get(String directionString) {
        return lookup.get(directionString);
    }

    Direction8Way(String directionString) {
        this.directionString = directionString;
    }

    public String getDirectionString() {
        return directionString;
    }

    public Direction8Way rotate(Rotation rotation) {
        int newDirection = this.ordinal();
        Direction8Way newDirectionURDLEnum = null;
        if (rotation == CLOCKWISE) {
            newDirection += 1;
            if (newDirection >= this.values().length) {
                // wrap it
                newDirection -= this.values().length;
            }
        }
        if (rotation == COUNTERCLOCKWISE) {
            newDirection -= 1;
            if (newDirection < 0) {
                // wrap it the other way
                newDirection += this.values().length;
            }
        }
        return Direction8Way.values()[newDirection];
    }
}
