package org.jgoeres.adventofcode2020.Day17;

import org.jgoeres.adventofcode2020.common.XYZPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import static org.jgoeres.adventofcode2020.Day17.Cube.*;

public class Day17Service {
    private final String DEFAULT_INPUTS_PATH = "data/day17/input.txt";
    HashMap<String, Cube> universe = new HashMap<>();
    HashMap<String, Cube> nextUniverse = new HashMap<>();
    HashMap<String, Cube> temp; // for swapping

    HashMap<String, Cube> cubesToAdd = new HashMap<>();
    private static boolean DEBUG = false;

    private static final ArrayList<XYZPoint> neighbors = new ArrayList<>();

    private ArrayList<Integer> inputList = new ArrayList<>();

    public Day17Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
        initNeighbors();
    }

    public Day17Service(String pathToFile) {
        loadInputs(pathToFile);
        initNeighbors();
    }

    private void initNeighbors() {
        // Build the (static) array of all 26 XYZ neighbors' relative coordinates
        for (int z = -1; z <= 1; z++) {
            for (int y = -1; y <= 1; y++) {
                for (int x = -1; x <= 1; x++) {
                    if (x == 0 && y == 0 && z == 0) continue;    // cube is not a neighbor of itself
                    neighbors.add(new XYZPoint(x, y, z));
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

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    private void doTimerTick() {
        // Before calculating anything, make sure we have a big enough "universe"
        // For each currently active cube, make sure all of its immediate neighbors
        // exist so we can check them against our rules
        cubesToAdd.clear();
        for (Cube cube : universe.values()) {
            if (cube.isActive()) {
                // Make sure all its neighbors exist
                for (XYZPoint neighbor : neighbors) {
                    // Check if there's an active Cube at each neighboring coordinate
                    int neighborX = cube.getX() + neighbor.getX();
                    int neighborY = cube.getY() + neighbor.getY();
                    int neighborZ = cube.getZ() + neighbor.getZ();
                    String neighborKey = key(neighborX, neighborY, neighborZ);
                    // Create the neighbor in nextUniverse, if necessary
                    if (!(universe.containsKey(neighborKey))) {
                        cubesToAdd.put(neighborKey, new Cube(neighborX, neighborY, neighborZ, INACTIVE));
                    }
                }
            }
        }
        universe.putAll(cubesToAdd);

        // Now process every cube in the known universe AGAIN,
        // This time including any new cubes we just made
        for (Cube cube : universe.values()) {
            Cube nextCube = getOrCreateCube(nextUniverse, cube.getX(), cube.getY(), cube.getZ());
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

    private int countActiveNeighbors(Cube cube) {
        int MAX_COUNT = 3;  // Return early if more than this many neighbors are active
        int count = 0;
        for (XYZPoint neighbor : neighbors) {
            // Check if there's an active Cube at each neighboring coordinate
            int neighborX = cube.getX() + neighbor.getX();
            int neighborY = cube.getY() + neighbor.getY();
            int neighborZ = cube.getZ() + neighbor.getZ();
            String neighborKey = key(neighborX, neighborY, neighborZ);
            // If the neighbor is active, count it
            if (universe.containsKey(neighborKey)
                    && universe.get(neighborKey).isActive()) {
                count++;
                if (count > MAX_COUNT) return count;    // Return early if we find enough
            }
        }
        return count;
    }

    public long countAllActive(HashMap<String, Cube> universe) {
        long count = universe.values().stream().filter(c -> c.isActive()).count();
        return count;
    }

    private String key(int x, int y, int z) {
        return (x + ", " + y + ", " + z);
    }

    public Cube getOrCreateCube(HashMap<String, Cube> universe, int x, int y, int z) {
        // Get the specified Cube from nextUniverse, or create it
        String universeKey = key(x, y, z);
        if (universe.containsKey(universeKey)) {
            // exists; return it
            return universe.get(universeKey);
        } else {
            // does not exist; create it
            Cube newCube = new Cube(x, y, z, INACTIVE);
            universe.put(newCube.toString(), newCube);
            return newCube;
        }
    }

    private void printUniverse(HashMap<String, Cube> universe) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE;
        int maxZ = Integer.MIN_VALUE;
        // Find extents of the universe
        for (Cube cube : universe.values()) {
            if (cube.getX() > maxX) maxX = cube.getX();
            if (cube.getX() < minX) minX = cube.getX();
            if (cube.getY() > maxY) maxY = cube.getY();
            if (cube.getY() < minY) minY = cube.getY();
            if (cube.getZ() > maxZ) maxZ = cube.getZ();
            if (cube.getZ() < minZ) minZ = cube.getZ();
        }

        for (int z = minZ; z <= maxZ; z++) {
            System.out.println("z=" + z);
            System.out.println("X = " + minX + "-" + maxX);
            System.out.println("Y = " + minY + "-" + maxY);
            for (int y = minY; y <= maxY; y++) {
                for (int x = minX; x <= maxX; x++) {
                    boolean active = false;
                    if (universe.containsKey(key(x, y, z))) {
                        active = universe.get(key(x, y, z)).isActive();
                    }
                    System.out.print(active ? ACTIVE_CHAR : INACTIVE_CHAR);
                }
                System.out.print("\n"); // new line at end of X coords
            }
        }
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
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
            while ((line = br.readLine()) != null) {
                // process the line.
                x = 0;
                for (Character c : line.toCharArray()) {
                    // Create an cube
                    Cube cube = new Cube(x, y, z, ACTIVE);  // assume active
                    if (c == INACTIVE_CHAR) {
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
