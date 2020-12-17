package org.jgoeres.adventofcode2020.Day17;

import org.jgoeres.adventofcode2020.common.XYZWPoint;

public class HyperCube extends XYZWPoint {
    public static final Character ACTIVE_CHAR = '#';
    public static final Character INACTIVE_CHAR = '.';

    public static final boolean ACTIVE = true;
    public static final boolean INACTIVE = false;

    boolean active = true; // Assume cubes are active when we create them

    public HyperCube() {
        // create at 0,0,0,0
        super();
    }

    public HyperCube(int x, int y) {
        // Create at z = 0
        super(x, y, 0);
    }

    public HyperCube(int x, int y, int z) {
        super(x, y, z);
    }

    public HyperCube(int x, int y, int z, int w, boolean active) {
        super(x, y, z, w);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
