package org.jgoeres.adventofcode2020.Day20;

import org.jgoeres.adventofcode2020.common.XYPoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jgoeres.adventofcode2020.Day20.Tile.*;
import static org.jgoeres.adventofcode2020.Day20.Tile.FlipDirection.LEFTRIGHT;
import static org.jgoeres.adventofcode2020.Day20.Tile.FlipDirection.TOPBOTTOM;

public class Day20Service {
    private final String DEFAULT_INPUTS_PATH = "data/day20/input.txt";

    public static boolean DEBUG = false;
    private static String EMPTY = "";

    private ArrayList<Integer> inputList = new ArrayList<>();
    private HashMap<Integer, Tile> tileMap = new HashMap<Integer, Tile>();

    public Day20Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    int PUZZLE_WIDTH;
    int MONSTER_ROW_WIDTH;
    int MONSTER_AREA_HEIGHT;

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

    public int doPartB(int puzzleWidth) {
        PUZZLE_WIDTH = puzzleWidth;
        MONSTER_ROW_WIDTH = STRIPPED_LENGTH * PUZZLE_WIDTH;
        MONSTER_AREA_HEIGHT = MONSTER_ROW_WIDTH;

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
        // Don't worry about flipping it now, but ROTATE it until it's the "upper left"
        // i.e. only its RIGHT (1) and BOTTOM (2) edges match anything.
        Tile upperLeft = null;
        for (Tile tile : tileMap.values()) {
            if (tile.getMatchedTiles().size() == 2) {
                upperLeft = tile;
                break;
            }
        }

        if (DEBUG) {
            upperLeft.printTile();
            System.out.println("\n^^^ Flip TOP-BOTTOM vvv\n");
            upperLeft.flipTile(TOPBOTTOM);
            upperLeft.printTile();
            System.out.println("\n<<< Flip LEFT-RIGHT >>>\n");
            upperLeft.flipTile(LEFTRIGHT);
            upperLeft.printTile();
        }

        // We found our upper right tile, now rotate it to the orientation we want
        // Check the matches of this corner tile to see if they match on the RIGHT and BOTTOM.
        // Rotate the tile until they do
        // Find which side the first matchedTile matches up to, starting with the RIGHT side
        Tile firstMatch = upperLeft.getMatchedTiles().get(0);
        int side1;
        all:
        for (side1 = TOP; side1 <= LEFT; side1++) {
            if (firstMatch.getEdgeIds().contains(upperLeft.getEdgeId(side1))
                    || firstMatch.getEdgeIds().contains(flip(upperLeft.getEdgeId(side1)))) {
                // When we find the first matching side, rotate or flip upperLeft so the match is on the RIGHT
                switch (side1) {
                    case TOP:
                        // If TOP matches, rotate ONCE
                        upperLeft.rotateCW();
                        break;
                    case RIGHT:
                        // If RIGHT matches, then we're halfway there; just bail
                        break;
                    case BOTTOM:
                        // If BOTTOM matches first, then rotate THRICE
                        upperLeft.rotateCW();
                        upperLeft.rotateCW();
                        upperLeft.rotateCW();
                        break;
                    case LEFT:
                        // if LEFT matches first, then rotate twice
                        upperLeft.rotateCW();
                        upperLeft.rotateCW();
                        break;
                }
                // Now we've got the RIGHT side where we want it.
                // We need to see if the BOTTOM side also matches, and rotate once if it doesn't
                Tile secondMatch = upperLeft.getMatchedTiles().get(1);
                if (secondMatch.getEdgeIds().contains(upperLeft.getEdgeId(TOP)))
                    // RIGHT & TOP match, so rotate ONCE
                    upperLeft.rotateCW();
                // Otherwise RIGHT & BOTTOM match already so everything is perfect. Nothing futher to do.
            }
        }

        // Now we have our upper left tile oriented correctly.
        // So we can proceed to orient all the other tiles
        upperLeft.setLocation(new XYPoint(0, 0));
        upperLeft.setLocked(true);

        // Orient all neighbors of the upperLeft tile
        upperLeft.orientNeighbors(true);

        if (DEBUG) {
            // print the whole damn thing
            printLayout(LINE_LENGTH);
        }

        // Now, you're ready to check the image for sea monsters.
        //
        // **The borders of each tile are not part of the actual image; start by removing them.**

        // Remove the top & bottom rows, and the left & right column of every tile
        for (Tile tile : tileMap.values()) {
            tile.stripEdges();
        }

        // Now build the big amalgamated BigInt array that we can search for monsters in.
        // For each row of the layout, construct a single 109-bit BigInteger
        // By iterating across the columns and masking/shifting to make the big number
        XYPoint key = new XYPoint(0, 0);
        ArrayList<BigInteger> monsterArea = new ArrayList<>();
        BigInteger monsterRow;
        for (int tileRow = 0; tileRow < PUZZLE_WIDTH; tileRow++) {
            for (int y = 0; y < STRIPPED_LENGTH; y++) {
                monsterRow = BigInteger.ZERO;
                for (int x = 0; x < PUZZLE_WIDTH; x++) {
                    key.set(x, tileRow);
                    Tile tile = layout.get(key.toString());
                    BigInteger monsterValue = BigInteger.valueOf(tile.calculateRowId(tile.tileData[y]));
                    monsterRow = monsterRow.shiftLeft(STRIPPED_LENGTH); // shift the current row up out of the way
                    monsterRow = monsterRow.or(monsterValue);   // OR the new bits into it
                }
                monsterArea.add(monsterRow);
                if (DEBUG) {
                    System.out.print(tileRow + " " + y + "\t");
                    printMonsterRow(monsterRow);
                }
            }
        }

        // Get the initial count of #s
        int initialCount = 0;
        for (BigInteger row : monsterArea) {
            int rowCount = row.bitCount();
            initialCount += rowCount;
        }

        // OK, we've got the entire field in a 1D array of BigInts
        // Look for the monster!
        // Try every rotation. If it matches, bail out.
        boolean found = false;
        all:
        while (true) {
            for (int i = 0; i < 4; i++) {
                if (DEBUG) {
                    System.out.println("\nORIENTATION #" + i);
                    printMonsterArea(monsterArea);
                }
                if (searchMonster(monsterArea)) break all;
                else monsterArea = rotateArea(monsterArea);     // rotate the entire monsterArea and continue
            }
            // No match yet, flip it
            System.out.println("\nFLIP!");
            flipArea(monsterArea);  // flip happens in place
            // And try again
            for (int i = 0; i < 4; i++) {
                if (DEBUG) {
                    System.out.println("\nORIENTATION #" + i);
                    printMonsterArea(monsterArea);
                }
                if (searchMonster(monsterArea)) break all;
                else monsterArea = rotateArea(monsterArea);     // rotate the entire monsterArea and continue
            }
            break;
        }
        if (DEBUG) {
            System.out.println("\n**** FINAL MAP ****");
            printMonsterArea(monsterArea);
        }
        // At some point we find at least one monster and end up here
        // Count up the remaining 1 bits!
        int count = 0;
        for (BigInteger row : monsterArea) {
            int rowCount = row.bitCount();
            count += rowCount;
        }

        return count;
    }

    private void flipArea(ArrayList<BigInteger> monsterArea) {
        // Flip the monsterArea BigInts left to right
        for (int i = 0; i < MONSTER_AREA_HEIGHT; i++) {
            // Do each item in the array
            BigInteger monsterRow = monsterArea.get(i);
            for (int j = 0; j < MONSTER_ROW_WIDTH / 2; j++) {
                int rightBitN = j;
                int leftBitN = MONSTER_ROW_WIDTH - 1 - j;
                boolean rightBit = monsterRow.testBit(j);
                boolean leftBit = monsterRow.testBit(leftBitN);
                // Switch the bits
                if (rightBit) monsterRow = monsterRow.setBit(leftBitN);
                else monsterRow = monsterRow.clearBit(leftBitN);

                if (leftBit) monsterRow = monsterRow.setBit(rightBitN);
                else monsterRow = monsterRow.clearBit(rightBitN);
            }
            // Stick the flipped value back in the row.
            monsterArea.set(i, monsterRow);
        }
    }

    private ArrayList<BigInteger> rotateArea(ArrayList<BigInteger> monsterArea) {
        ArrayList<BigInteger> rotated = new ArrayList<>();
        // Rotate the array... clockwise?
        // Go down each COLUMN of monsterArea, build the bits into
        // a new number, and stick that number in the corresponding ROW of 'rotated'
        for (int j = 0; j < MONSTER_ROW_WIDTH; j++) {   // across the rows
            BigInteger newValue = BigInteger.ZERO;
            for (int i = 0; i < MONSTER_ROW_WIDTH; i++) { // down the columns
                // the bit in row 0 becomes the new bit0
                // the bit in row 1 becomes the new bit1, etc.
                boolean bitValue = monsterArea.get(i).testBit(MONSTER_ROW_WIDTH - j - 1);
                if (bitValue) {
                    newValue = newValue.setBit(i);
                } else {
                    newValue = newValue.clearBit(i);   // this is actually extraneous – we started with all zeroes
                }
            }
            // When we're done with this column stick it in the new array.
            rotated.add(newValue);
        }
        return rotated;
    }

    private boolean searchMonster(ArrayList<BigInteger> monsterArea) {
        BigInteger monsterRow;
        // The sea monster looks like this
        //                    #
        //  #    ##    ##    ###
        //   #  #  #  #  #  #
        // In binary that's
        String monsterString0 = "00000000000000000010";
        String monsterString1 = "10000110000110000111";
        String monsterString2 = "01001001001001001000";
        // Convert these lines to BigInteger bitfields
        final int BINARY_RADIX = 2;
        BigInteger monster0 = new BigInteger(monsterString0, BINARY_RADIX);
        BigInteger monster1 = new BigInteger(monsterString1, BINARY_RADIX);
        BigInteger monster2 = new BigInteger(monsterString2, BINARY_RADIX);
        // We're going to need the width of the monster, also
        final int BITS_MONSTER_WIDTH = monsterString1.length();

        // The biggest part of the monster is the second line (monster1).
        // It can be anywhere (that it fits) from left to right,
        // And anywhere from the second row (i=1) to the second-to-last row (i=STRIPPED_LENGTH-1)

        boolean foundAMonster = false;
        // So let's try to find it
        for (int j = 1; j < MONSTER_AREA_HEIGHT - 1; j++) {
            // Start with monsterString1 all the way to the right,
            // and shift it left looking for a match
            BigInteger shiftMonster = monster1;
            monsterRow = monsterArea.get(j);
            for (int offset = 0; offset < (MONSTER_ROW_WIDTH - BITS_MONSTER_WIDTH); offset++) {
                boolean monsterExists1 = monsterRow.and(shiftMonster).compareTo(shiftMonster) == 0;
                if (!monsterExists1) {
                    // no monster here, shift and continue.
                } else {
                    // We found one part of a potential monster!
                    // Check the rows above and below this one, at the current offset
                    BigInteger monsterRow0 = monsterArea.get(j - 1);
                    BigInteger monsterMask0 = monster0.shiftLeft(offset);
                    boolean monsterExists0 = monsterRow0.and(monsterMask0).compareTo(monsterMask0) == 0;
                    BigInteger monsterRow2 = monsterArea.get(j + 1);
                    BigInteger monsterMask2 = monster2.shiftLeft(offset);
                    boolean monsterExists2 = monsterRow2.and(monsterMask2).compareTo(monsterMask2) == 0;
                    if (monsterExists0 && monsterExists2) {
                        if (DEBUG) {
                            System.out.println("** Monster found at row: " + j + "\toffset: " + offset);
                        }
                        // If all three parts of the monster match here
                        // Mask out those bits by ANDing with the NOT of the mask
                        BigInteger maskedMonster0 = monsterRow0.and(monsterMask0.not());
                        BigInteger maskedMonster1 = monsterRow.and(shiftMonster.not());
                        BigInteger maskedMonster2 = monsterRow2.and(monsterMask2.not());
                        // Update the area
                        monsterArea.set(j - 1, maskedMonster0);
                        monsterArea.set(j, maskedMonster1);
                        monsterArea.set(j + 1, maskedMonster2);
                        foundAMonster = true;   // Remember that we found one!

                        monsterRow = monsterArea.get(j);    // Also refresh the value of the row we're looking at
                    }
                    // but don't break – there might be more monsters in this row!
                }
                // shift and continue
                shiftMonster = shiftMonster.shiftLeft(1);

            }

        }

        return foundAMonster;
    }

    private void printMonsterArea(ArrayList<BigInteger> monsterArea) {
        System.out.println(); //blank line
        for (int i = 0; i < MONSTER_AREA_HEIGHT; i++) {
            System.out.print(i + "\t");
            printMonsterRow(monsterArea.get(i));
        }
    }

    private void printMonsterRow(BigInteger monsterRow) {
        final int MONSTER_ROW_WIDTH = STRIPPED_LENGTH * PUZZLE_WIDTH;
        for (int i = MONSTER_ROW_WIDTH - 1; i >= 0; i--) {
            boolean bit = monsterRow.testBit(i);
            char output = bit ? ONE_CHAR : ZERO_CHAR;
            System.out.print(output);
        }
        System.out.println();   // linefeed
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
