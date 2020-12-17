package org.jgoeres.adventofcode2020.Day17;

import org.jgoeres.adventofcode2020.common.XYZPoint;

public class Cube extends XYZPoint {
    public static final Character ACTIVE_CHAR = '#';
    public static final Character INACTIVE_CHAR = '.';

    public static final boolean ACTIVE = true;
    public static final boolean INACTIVE = false;

    boolean active = true; // Assume cubes are active when we create them
    boolean checked = false;

    public Cube() {
        // create at 0,0,0
        super();
    }

    public Cube(int x, int y) {
        // Create at z = 0
        super(x, y, 0);
    }

    public Cube(int x, int y, int z) {
        super(x, y, z);
    }

    public Cube(int x, int y, int z, boolean active) {
        super(x, y, z);
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean hasBeenChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
