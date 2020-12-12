package org.jgoeres.adventofcode2020.Day12;

import org.jgoeres.adventofcode2020.common.DirectionURDL;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
    public enum Direction {NORTH, EAST, SOUTH, WEST, LEFT, RIGHT, FORWARD}

    private static final ArrayList<Direction> facings =
            new ArrayList<>(Arrays.asList(
                    Direction.NORTH,
                    Direction.EAST,
                    Direction.SOUTH,
                    Direction.WEST));
    XYPoint location;
    Direction facing;

    public Ship() {
        // Start at 0,0
        location = new XYPoint(0, 0);
        // facing EAST
        facing = Direction.EAST;
    }

    public void move(Move move) {
        Direction direction = move.direction;
        Integer distance = move.distance;

        switch (direction) {
            case NORTH:
            case EAST:
            case SOUTH:
            case WEST:
                move(direction, distance);
                break;
            case FORWARD:
                // Move in the direction we're facing
                move(facing, distance);
                break;
            case LEFT:
            case RIGHT:
                rotate(direction, distance);
                break;
        }
    }

    public void move(Direction direction, Integer distance) {
        switch (direction) {
            case NORTH:
                // Default negative-Y direction is UP/NORTH
                location.setY(location.getY() - distance);
                break;
            case EAST:
                location.setX(location.getX() + distance);
                break;
            case SOUTH:
                // Default positive-Y direction is DOWN/SOUTH
                location.setY(location.getY() + distance);
                break;
            case WEST:
                location.setX(location.getX() - distance);
                break;
        }
    }

    public void rotate(Direction direction, Integer angle) {
        // Are we turning positive (RIGHT) or negative (LEFT)
        Integer sign = ((direction == Direction.RIGHT) ? 1 : -1);
        // How far?
        Integer turnSteps = angle / 90;
        // Combine direction with # of steps
        turnSteps *= sign;

        // Turn the ship
        Integer newDirectionInt = facings.indexOf(facing) + turnSteps;
        // Wrap it
        if (newDirectionInt >= facings.size()) newDirectionInt %= facings.size();
        else if (newDirectionInt < 0) newDirectionInt += facings.size();
        // Set the enum
        facing = facings.get(newDirectionInt);
    }


    public XYPoint getLocation() {
        return location;
    }
}
