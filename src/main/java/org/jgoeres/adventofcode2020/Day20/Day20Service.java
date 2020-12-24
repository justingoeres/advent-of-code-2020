package org.jgoeres.adventofcode2020.Day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day20Service {
    private final String DEFAULT_INPUTS_PATH = "data/day20/input.txt";

    private static boolean DEBUG = false;
    private static String EMPTY = "";

    private ArrayList<Integer> inputList = new ArrayList<>();
    private HashMap<Integer, Tile> tileMap = new HashMap<Integer, Tile>();

    public Day20Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day20Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public long doPartA() {
        /** By rotating, flipping, and rearranging them, you can find a square arrangement
         * that causes all adjacent borders to line up.
         *
         * Assemble the tiles into an image.
         * What do you get if you multiply together the IDs of the four corner tiles?
         **/

        // Match up all the tiles
        for (Tile tile1 : tileMap.values()) {
            for (Tile tile2 : tileMap.values()) {
                // Process all the possible matches
                tile1.matchesTile(tile2);
            }
        }
        long result = 1L;
        for (Tile tile : tileMap.values()) {
            // Find the tiles that have only two neighbors – there should be exactly four of them
            if (tile.getMatchedTiles().size() == 2) {
                result *= tile.getId();
            }
        }
        return result;
    }

    public int doPartB() {
        int result = 0;
        /**
         * Now, you're ready to check the image for sea monsters.
         * The borders of each tile are not part of the actual image; start by removing them.
         *a sea monster will look like this:
         *
         *                   #
         * #    ##    ##    ###
         *  #  #  #  #  #  #
         *
         * How many # are not part of a sea monster?
         */


        // Make sure all the tiles are matched up
        for (Tile tile1 : tileMap.values()) {
            for (Tile tile2 : tileMap.values()) {
                // Process all the possible matches
                tile1.matchesTile(tile2);
            }
        }

        // After Part A, we have all the tiles and their corresponding matching tiles.
        // But we don't know anything about rotation or flipping.
        // Can we figure that out by working from one of the known corners?

        // Start with one of the corner tiles (i.e. two matching edges)
        // Don't worry about flipping it now, but ROTATE it until it's the "upper right"
        // i.e. only its RIGHT (1) and BOTTOM (2) edges match anything.
        Tile upperRight = null;
        for (Tile tile : tileMap.values()) {
            if (tile.getMatchedTiles().size() == 2) {
                upperRight = tile;
                break;
            }
        }
        // We found our upper right tile, now rotate it to the orientation we want
        upperRight.printTile();
        upperRight.rotateCW();
        System.out.println();
        upperRight.printTile();

        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /**
             * Example input fragment:
             *  Tile 3079:
             *  #.#.#####.
             *  .#..######
             *  ..#.......
             *  ######....
             *  ####.#..#.
             *  .#...#.##.
             *  #.#####.##
             *  ..#.###...
             *  ..#.......
             *  ..#.###...
             * **/
            Pattern p = Pattern.compile("Tile (\\d+):");
            Tile tile = null;

            while ((line = br.readLine()) != null) {
                // process the line.
                Matcher m = p.matcher(line);
                if (m.matches()) { // If our regex matched this line
                    // Then this is a Tile label; create a tile
                    Integer tileNum = Integer.parseInt(m.group(1));
                    tile = new Tile(tileNum);
                } else if (line.equals(EMPTY)) {
                    // End of this tile; process it to get the edges
                    tile.calculateEdges();
                    // Then put it in the map
                    tileMap.put(tile.getId(), tile);
                } else {
                    // This is a pattern line, e.g.
                    //  ..#.#####.
                    tile.addRow(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
