package org.jgoeres.adventofcode2020.Day12;

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

    XYPoint waypoint;

    public Ship() {
        // Start at 0,0
        location = new XYPoint(0, 0);
        // facing EAST
        facing = Direction.EAST;
        waypoint = new XYPoint(10, -1); // 10 units EAST, 1 unit NORTH
    }

    public void move(Move move) {
        Direction direction = move.direction;
        Integer distance = move.distance;

        switch (direction) {
            case NORTH:
            case EAST:
            case SOUTH:
            case WEST:
                moveShip(direction, distance);
                break;
            case FORWARD:
                // Move in the direction we're facing
                moveShip(facing, distance);
                break;
            case LEFT:
            case RIGHT:
                rotate(direction, distance);
                break;
        }
    }

    public void stepTowardWaypoint(int numOfSteps) {
        // Each step is the distance all the way to the waypoint
        // e.g. if waypoint is (10, -1) then *each step* is 10 EAST, -1 NORTH
        // As long as we're allowed to use negative numbers, all moves-to-waypoint
        // can be represented as EAST & SOUTH
        moveShip(Direction.EAST, waypoint.getX() * numOfSteps);
        moveShip(Direction.SOUTH, waypoint.getY() * numOfSteps);
    }

    public void moveShip(Direction direction, Integer distance) {
        move(direction, distance, location);
    }

    public void rotate(Direction direction, Integer angle) {
        // Are we turning positive (RIGHT) or negative (LEFT)
        int sign = ((direction == Direction.RIGHT) ? 1 : -1);
        // How far?
        int turnSteps = angle / 90;
        // Combine direction with # of steps
        turnSteps *= sign;

        // Turn the ship
        int newDirectionInt = facings.indexOf(facing) + turnSteps;
        // Wrap it
        if (newDirectionInt >= facings.size()) newDirectionInt %= facings.size();
        else if (newDirectionInt < 0) newDirectionInt += facings.size();
        // Set the enum
        facing = facings.get(newDirectionInt);
    }

    public void followWaypoint(Move move) {
        Direction direction = move.direction;
        Integer distance = move.distance;

        switch (direction) {
            // Move the waypoint
            case NORTH:
            case EAST:
            case SOUTH:
            case WEST:
                moveWaypoint(direction, distance);
                break;
            case FORWARD:
                // Move toward the waypoint
                stepTowardWaypoint(distance);
                break;
            case LEFT:
            case RIGHT:
                rotateWaypoint(direction, distance);
                break;
        }
    }

    public void moveWaypoint(Direction direction, Integer distance) {
        move(direction, distance, waypoint);
    }

    private void move(Direction direction, Integer distance, XYPoint xyPoint) {
        switch (direction) {
            case NORTH:
                // Default negative-Y direction is UP/NORTH
                xyPoint.setY(xyPoint.getY() - distance);
                break;
            case EAST:
                xyPoint.setX(xyPoint.getX() + distance);
                break;
            case SOUTH:
                // Default positive-Y direction is DOWN/SOUTH
                xyPoint.setY(xyPoint.getY() + distance);
                break;
            case WEST:
                xyPoint.setX(xyPoint.getX() - distance);
                break;
            default:
                // do nothing
        }
    }

    public void rotateWaypoint(Direction direction, Integer angle) {
        // How far?
        int turnSteps = angle / 90;

        // Turn the waypoint
        for (int i = 0; i < turnSteps; i++) {
            int temp;
            switch (direction) {
                // Note UP/NORTH is NEGATIVE-Y, so the signs in the
                // rotation are opposite what a normal XY grid would give
                case LEFT:
                    temp = -1 * waypoint.getX();
                    waypoint.setX(waypoint.getY());
                    waypoint.setY(temp);
                    break;
                case RIGHT:
                    temp = -1 * waypoint.getY();
                    waypoint.setY(waypoint.getX());
                    waypoint.setX(temp);
                    break;
                default:
                    // do nothing
            }
        }
    }

    public XYPoint getLocation() {
        return location;
    }
}
