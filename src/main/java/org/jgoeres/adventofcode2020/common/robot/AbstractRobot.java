package org.jgoeres.adventofcode2020.common.robot;

import org.jgoeres.adventofcode2020.common.Rotation;
import org.jgoeres.adventofcode2020.common.XYPoint;

public abstract class AbstractRobot {
    protected XYPoint location;
    protected final int DEFAULT_DISTANCE = 1;

    public AbstractRobot(XYPoint location) {
        this.location = location;
    }

    public XYPoint getLocation() {
        return location;
    }

    public void stepRobot() {
        moveRobot(DEFAULT_DISTANCE);
    }

    public abstract void moveRobot(int numSteps);

    public abstract void turnRobot(Rotation rotation);
}
