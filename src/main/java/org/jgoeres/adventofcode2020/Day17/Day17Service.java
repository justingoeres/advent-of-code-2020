package org.jgoeres.adventofcode2020.Day17;

import org.jgoeres.adventofcode2020.common.XYZWPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.jgoeres.adventofcode2020.Day17.HyperCube.*;

public class Day17Service {
    private static final String DEFAULT_INPUTS_PATH = "data/day17/input.txt";
    HashMap<String, HyperCube> universe = new HashMap<>();
    HashMap<String, HyperCube> nextUniverse = new HashMap<>();
    HashMap<String, HyperCube> temp; // for swapping

    HashMap<String, HyperCube> cubesToAdd = new HashMap<>();
    private static final boolean DEBUG = false;

    private static final ArrayList<XYZWPoint> neighbors = new ArrayList<>();

    public Day17Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
        initNeighbors(false);
    }

    public Day17Service(String pathToFile) {
        loadInputs(pathToFile);
        initNeighbors(false);
    }

    private void initNeighbors(boolean in4D) {
        neighbors.clear();
        // Build the (static) array of all neighbors' relative coordinates
        int wMin = in4D ? -1 : 0;
        int wMax = in4D ? 1 : 0;
        for (int w = wMin; w <= wMax; w++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = -1; y <= 1; y++) {
                    for (int x = -1; x <= 1; x++) {
                        if (x == 0 && y == 0 && z == 0 && w == 0) continue;    // cube is not a neighbor of itself
                        neighbors.add(new XYZWPoint(x, y, z, w));
                    }
                }
            }
        }
    }

    public long doPartA() {
        long result;
        int TARGET_CYCLES = 6;
        /**
         * Starting with your given initial configuration, simulate SIX cycles.
         * How many cubes are left in the active state after the sixth cycle?
         **/
        System.out.println("Before any cycles:\n");
        if (DEBUG) {
            printUniverse(universe);
        }
        for (int t = 0; t < TARGET_CYCLES; t++) {
            // Calculate the next universe state
            doTimerTick();
            if (DEBUG) {
                System.out.println("\n\nAfter " + (t + 1) + " cycles:");
                printUniverse(universe);
            }
        }
        result = countAllActive(universe);

        return result;
    }

    public long doPartB() {
        return doPartB(DEFAULT_INPUTS_PATH);
    }

    public long doPartB(String pathToFile) {
        int TARGET_CYCLES = 6;

        // (Re-)init the universe
        universe.clear();
        nextUniverse.clear();
        cubesToAdd.clear();
        loadInputs(pathToFile);
        initNeighbors(true);
        long result;
        /** Starting with your given initial configuration,
         * simulate six cycles in a 4-dimensional space.
         *
         * How many cubes are left in the active state after the sixth cycle?
         **/

        for (int t = 0; t < TARGET_CYCLES; t++) {
            // Calculate the next universe state
            doTimerTick();
            if (DEBUG) {
                System.out.println("\n\nAfter " + (t + 1) + " cycles:");
                printUniverse(universe);
            }
        }
        result = countAllActive(universe);

        return result;
    }

    private void doTimerTick() {
        // Before calculating anything, make sure we have a big enough "universe"
        // For each currently active cube, make sure all of its immediate neighbors
        // exist so we can check them against our rules
        cubesToAdd.clear();
        for (HyperCube cube : universe.values()) {
            if (cube.isActive()) {
                // Make sure all its neighbors exist
                for (XYZWPoint neighbor : neighbors) {
                    // Check if there's an active Cube at each neighboring coordinate
                    int neighborX = cube.getX() + neighbor.getX();
                    int neighborY = cube.getY() + neighbor.getY();
                    int neighborZ = cube.getZ() + neighbor.getZ();
                    int neighborW = cube.getW() + neighbor.getW();
                    String neighborKey = key(neighborX, neighborY, neighborZ, neighborW);
                    // Create the neighbor in nextUniverse, if necessary
                    if (!(universe.containsKey(neighborKey))) {
                        cubesToAdd.put(neighborKey, new HyperCube(neighborX, neighborY, neighborZ, neighborW, HyperCube.INACTIVE));
                    }
                }
            }
        }
        universe.putAll(cubesToAdd);

        // Now process every cube in the known universe AGAIN,
        // This time including any new cubes we just made
        for (HyperCube cube : universe.values()) {
            HyperCube nextCube = getOrCreateCube(nextUniverse, cube.getX(), cube.getY(), cube.getZ(), cube.getW());
            nextCube.setActive(cube.isActive());    // nextCube starts off in same state as current cube
            // Apply rules:
            int activeNeighbors = countActiveNeighbors(cube);
            if (cube.isActive()) {
                if (DEBUG) {
                    System.out.println("cube = " + cube);
                    System.out.println("nextCube = " + nextCube);
                    System.out.println("cube.active = " + cube.isActive());
                    System.out.println("activeNeighbors = " + activeNeighbors);
                }
                // If a cube is active and exactly 2 or 3 of its neighbors are also active,
                //   the cube remains active. Otherwise, the cube becomes inactive.
                if (!(activeNeighbors == 2
                        || activeNeighbors == 3)) {
                    // set inactive
                    nextCube.setActive(INACTIVE);
                }
            } else {
                // If a cube is inactive but exactly 3 of its neighbors are active,
                //   the cube becomes active. Otherwise, the cube remains inactive.
                activeNeighbors = countActiveNeighbors(cube);
                if (activeNeighbors == 3) {
                    // set active
                    nextCube.setActive(ACTIVE);
                }
            }
            if (DEBUG) {
                System.out.println("nextCube.active = " + nextCube.isActive() + "\n");
            }
            // Update nextCube in nextUniverse
        }
        // Swap the universes for the next timer tick
        temp = nextUniverse;
        nextUniverse = universe;
        universe = temp;
    }

    private int countActiveNeighbors(HyperCube cube) {
        int MAX_COUNT = 3;  // Return early if more than this many neighbors are active
        int count = 0;
        for (XYZWPoint neighbor : neighbors) {
            // Check if there's an active Cube at each neighboring coordinate
            int neighborX = cube.getX() + neighbor.getX();
            int neighborY = cube.getY() + neighbor.getY();
            int neighborZ = cube.getZ() + neighbor.getZ();
            int neighborW = cube.getW() + neighbor.getW();
            String neighborKey = key(neighborX, neighborY, neighborZ, neighborW);
            // If the neighbor is active, count it
            if (universe.containsKey(neighborKey)
                    && universe.get(neighborKey).isActive()) {
                count++;
                if (count > MAX_COUNT) return count;    // Return early if we find enough
            }
        }
        return count;
    }

    public long countAllActive(Map<String, HyperCube> universe) {
        return universe.values().stream().filter(HyperCube::isActive).count();

    }

    private String key(int x, int y, int z, int w) {
        return (x + ", " + y + ", " + z + ", " + w);
    }

    public HyperCube getOrCreateCube(Map<String, HyperCube> universe, int x, int y, int z, int w) {
        // Get the specified Cube from nextUniverse, or create it
        String universeKey = key(x, y, z, w);
        if (universe.containsKey(universeKey)) {
            // exists; return it
            return universe.get(universeKey);
        } else {
            // does not exist; create it
            HyperCube newCube = new HyperCube(x, y, z, w, INACTIVE);
            universe.put(newCube.toString(), newCube);
            return newCube;
        }
    }

    private void printUniverse(HashMap<String, HyperCube> universe) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxZ = Integer.MIN_VALUE;
        int minW = Integer.MAX_VALUE;
        int maxW = Integer.MIN_VALUE;
        // Find extents of the universe
        for (HyperCube cube : universe.values()) {
            if (cube.getX() > maxX) maxX = cube.getX();
            if (cube.getX() < minX) minX = cube.getX();
            if (cube.getY() > maxY) maxY = cube.getY();
            if (cube.getY() < minY) minY = cube.getY();
            if (cube.getZ() > maxZ) maxZ = cube.getZ();
            if (cube.getZ() < minZ) minZ = cube.getZ();
            if (cube.getW() > maxW) maxW = cube.getW();
            if (cube.getW() < minW) minW = cube.getW();
        }

        for (int w = minW; w < maxW; w++) {
            for (int z = minZ; z <= maxZ; z++) {
                System.out.println("z=" + z);
                System.out.println("X = " + minX + "-" + maxX);
                System.out.println("Y = " + minY + "-" + maxY);
                for (int y = minY; y <= maxY; y++) {
                    for (int x = minX; x <= maxX; x++) {
                        boolean active = false;
                        if (universe.containsKey(key(x, y, z, w))) {
                            active = universe.get(key(x, y, z, w)).isActive();
                        }
                        System.out.print(active ? ACTIVE_CHAR : INACTIVE_CHAR);
                    }
                    System.out.print("\n"); // new line at end of X coords
                }
            }
        }
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            /**
             * Example:
             *   .#.
             *   ..#
             *   ###
             * **/
            int x;
            int y = 0;
            int z = 0;
            int w = 0;
            while ((line = br.readLine()) != null) {
                // process the line.
                x = 0;
                for (Character c : line.toCharArray()) {
                    // Create an cube
                    HyperCube cube = new HyperCube(x, y, z, w, ACTIVE);  // assume active
                    if (c.equals(INACTIVE_CHAR)) {
                        // Set it inactive if necessary
                        cube.setActive(INACTIVE);
                    }
                    // Add it to the universe
                    universe.put(cube.toString(), cube);
                    x++;    // next char x-coord
                }
                y++;    // next char y-coord
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
