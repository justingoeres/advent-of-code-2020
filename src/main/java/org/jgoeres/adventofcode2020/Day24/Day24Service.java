package org.jgoeres.adventofcode2020.Day24;

import org.jgoeres.adventofcode2020.common.DirectionHexPointy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jgoeres.adventofcode2020.Day24.Tile.Side.BLACK;
import static org.jgoeres.adventofcode2020.Day24.Tile.floor;

public class Day24Service {
    private final String DEFAULT_INPUTS_PATH = "data/day24/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<List<DirectionHexPointy>> inputList = new ArrayList<>();

    public Day24Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day24Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /** Put problem implementation here **/
        for (List<DirectionHexPointy> steps : inputList) {
            // Start at the reference tile
            Tile tile = Tile.getOrCreate(0, 0);
            // Follow the steps
            for (DirectionHexPointy step : steps) {
                tile = tile.getNeighbor(step);
            }
            tile.flip(); // flip it!
        }
        if (DEBUG) {
            for (Tile tile : floor.values()) {
                System.out.println(tile.getLocation().toString() + "\t" + tile.getSide());
            }
        }
        // Count the black ones
        result = (int) floor.values().stream().filter(t -> t.getSide() == BLACK).count();
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            /** Replace this regex **/
            Pattern p = Pattern.compile("(se|sw|nw|ne|e|w)");
            while ((line = br.readLine()) != null) {
                ArrayList<DirectionHexPointy> steps = new ArrayList<>();
                // process the line.
                Matcher m = p.matcher(line);
                while (m.find()) { // Find all the direction steps in this line
                    steps.add(DirectionHexPointy.valueOf(m.group(1).toUpperCase()));
                }
                inputList.add(steps);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
