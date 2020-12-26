package org.jgoeres.adventofcode2020.Day24;

import org.jgoeres.adventofcode2020.common.DirectionHexPointy;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.jgoeres.adventofcode2020.Day24.Tile.Side.BLACK;
import static org.jgoeres.adventofcode2020.Day24.Tile.Side.WHITE;
import static org.jgoeres.adventofcode2020.Day24.Tile.floor;
import static org.jgoeres.adventofcode2020.Day24.Tile.getOrCreate;
import static org.jgoeres.adventofcode2020.common.Debug.debugPrint;

public class Day24Service {
    private final String DEFAULT_INPUTS_PATH = "data/day24/input.txt";

    private static boolean DEBUG = false;
    private static boolean DEBUG_VERBOSE = false;
    private String inputFile = DEFAULT_INPUTS_PATH;

    private ArrayList<List<DirectionHexPointy>> inputList = new ArrayList<>();


    public Day24Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day24Service(String pathToFile) {
        inputFile = pathToFile;
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /**
         * After all of the instructions have been followed, how many tiles
         * are left with the black side up?
         **/
        for (List<DirectionHexPointy> steps : inputList) {
            // Start at the reference tile
            Tile tile = Tile.getOrCreate(0, 0);
            // Follow the steps
            for (DirectionHexPointy step : steps) {
                tile = tile.getNeighbor(step);
            }
            tile.flip(); // flip it!
        }
        // Count the black ones
        result = (int) floor.values().stream().filter(t -> t.getSide() == BLACK).count();
        return result;
    }

    public int doPartB() {
        final int NUM_DAYS = 100;
        int result = 0;
        /**
         * Every day, the tiles are all flipped according to the following rules:
         *  Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
         *  Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
         *
         * How many tiles will be black after 100 days?
         **/
        if (floor.isEmpty()) {
            // If we didn't just run Part A, do it now
            doPartA();
        }
        debugVerbosePrintFloor();

        // We need to know about every tile surrounding a BLACK tile,
        // so make sure they all have 6 neighbors
        for (int i = 1; i <= NUM_DAYS; i++) {
            // Make sure all the (black) tiles have all their neighbors
            List<Tile> blackTiles = floor.values().stream().filter(p -> p.getSide() == BLACK)
                    .collect(Collectors.toList());
            debugPrint(DEBUG,"Filling around black tiles...");
            for (Tile tile : blackTiles) {
                tile.fillNeighbors();
            }
            debugVerbosePrintFloor();

            // Figure out which tiles SHOULD flip
            debugPrint(DEBUG,"Calculating who should flip...");
            for (Tile tile : floor.values()) {
                tile.calculateShouldFlip();
            }
            debugVerbosePrintFloor();
            debugPrint(DEBUG,"Flipping...");
            // Then flip them all at the same time!
            for (Tile tile : floor.values()) {
                if (tile.shouldFlip) tile.flip();
            }
            debugVerbosePrintFloor();
            if (DEBUG && (i < 10 || i % 10 == 0)) {
                int count = (int) floor.values().stream().filter(t -> t.getSide() == BLACK).count();
                System.out.println("Day " + i + ": " + count);
            }
        }
        // Count the black ones after all the days are done
        result = (int) floor.values().stream().filter(t -> t.getSide() == BLACK).count();
        return result;
    }

    public void debugVerbosePrintFloor() {
        if (!DEBUG_VERBOSE) return;
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (XYPoint xy : floor.keySet()) {
            if (xy.getX() < minX) minX = xy.getX();
            if (xy.getX() > maxX) maxX = xy.getX();
            if (xy.getY() < minY) minY = xy.getY();
            if (xy.getY() > maxY) maxY = xy.getY();
        }
        final Character SPACE = ' ';
        final Character DOT = '.';
        Character c;
        Character d;
        for (int y = minY; y <= maxY; y++) {
            String outputLine = "";
            if (y % 2 == 0) outputLine += SPACE; // indent even lines by one space
            for (int x = minY; x <= maxY; x++) {
                XYPoint xy = new XYPoint(x, y);
                if (floor.containsKey(xy)) {
                    c = (floor.get(xy).getSide() == WHITE) ? '+' : 'X';
                    d = floor.get(xy).shouldFlip ? '!' : SPACE;
                } else {
                    // unknown / empty so far
                    c = DOT;
                    d = SPACE;
                }
                outputLine += c.toString() + d;
            }
            System.out.println(outputLine);
        }
        System.out.println();   // blank line after
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
