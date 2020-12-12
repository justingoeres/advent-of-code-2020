package org.jgoeres.adventofcode2020.Day12;

public class Move {
    Ship.Direction direction;
    int distance;

    public Move(String directionString, Integer distance) {
        switch (directionString) {
            case "N":
                this.direction = Ship.Direction.NORTH;
                break;
            case "S":
                this.direction = Ship.Direction.SOUTH;
                break;
            case "E":
                this.direction = Ship.Direction.EAST;
                break;
            case "W":
                this.direction = Ship.Direction.WEST;
                break;
            case "L":
                this.direction = Ship.Direction.LEFT;
                break;
            case "R":
                this.direction = Ship.Direction.RIGHT;
                break;
            case "F":
                this.direction = Ship.Direction.FORWARD;
                break;
        }
        this.distance = distance;
    }
}
