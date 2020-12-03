package org.jgoeres.adventofcode2020.Day03;

import org.jgoeres.adventofcode2020.common.DirectionURDL;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.util.HashSet;

public class Forest {
    private HashSet<XYPoint> trees = new HashSet<>();
    private Integer sizeX = null;
    private Integer sizeY = null;

    private XYPoint tobogganLocation = new XYPoint();

    public Forest() {
    }

    public Forest(HashSet<XYPoint> trees) {
        this.trees = trees;
    }

    public Forest(int tobogganX, int tobogganY) {
        this.tobogganLocation = new XYPoint(tobogganX, tobogganY);
    }

    public void addTree(int x, int y) {
        trees.add(new XYPoint(x, y));
    }

    public boolean isTree(XYPoint xy) {
        boolean isTree = trees.contains(xy);
        return isTree;
    }

    public boolean tobogganIsOnTree() {
        // check if the current toboggan location puts it on a tree
        boolean onTree = isTree(tobogganLocation);
        return onTree;
    }

    public void moveToboggan(int steps, DirectionURDL direction) {
        // Move the toboggan the specified direction & distance
        tobogganLocation.moveRelative(steps, direction);

        // Account for rollover because the forest is magical
        if (tobogganLocation.getX() >= sizeX) {
            tobogganLocation.setX(tobogganLocation.getX() % sizeX);
        }
    }

    public boolean tobogganIsInside() {
        // Is the toboggan (still?) inside the forest?
        boolean isInside = ((tobogganLocation.getX() < sizeX)
                && (tobogganLocation.getY() < sizeY));
        return isInside;
    }

    public void resetToboggan() {
        tobogganLocation.setX(0);
        tobogganLocation.setY(0);
    }

    public Integer getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
