package org.jgoeres.adventofcode2020.Day20;

import org.jgoeres.adventofcode2020.common.Direction8Way;
import org.jgoeres.adventofcode2020.common.XYPoint;

import java.util.ArrayList;
import java.util.HashMap;

import static org.jgoeres.adventofcode2020.Day20.Day20Service.DEBUG;
import static org.jgoeres.adventofcode2020.Day20.Tile.FlipDirection.TOPBOTTOM;
import static org.jgoeres.adventofcode2020.common.Direction8Way.DOWN;
import static org.jgoeres.adventofcode2020.common.Direction8Way.UP;

public class Tile {
    public static final int LINE_LENGTH = 10;
    public static final int STRIPPED_LENGTH = LINE_LENGTH - 2;
    public static final char ONE_CHAR = '#';
    public static final char ZERO_CHAR = '.';

    static final int TOP = 0;
    static final int RIGHT = 1;
    static final int BOTTOM = 2;
    static final int LEFT = 3;
    private static final int CW = 1;

    boolean locked = false;
    XYPoint location = null;
    static HashMap<String, Tile> layout = new HashMap<>();

    enum FlipDirection {
        TOPBOTTOM,
        LEFTRIGHT
    }

    private ArrayList<Integer> edgeIds = new ArrayList<>();
    //    private HashSet<Tile> matchedTiles = new HashSet<>();
    private ArrayList<Tile> matchedTiles = new ArrayList<>();

    private int id;
    char[][] tileData = new char[LINE_LENGTH][LINE_LENGTH];

    private int row = 0;

    public Tile() {
    }

    public Tile(int id) {
        this.id = id;
    }

    public void addRow(String line) {
        // Add the row data into the tileData array
        tileData[row] = line.toCharArray();
        row++;
        return;
    }

    public void calculateEdges() {
        edgeIds.clear();
        // Go around the tile clockwise, starting on top
        // Top edge
        char[] topEdge = tileData[0];
        int topEdgeId = calculateRowId(topEdge);
        edgeIds.add(topEdgeId);

        // Right edge
        char[] rightEdge = getColumn(tileData, LINE_LENGTH - 1);
        int rightEdgeId = calculateRowId(rightEdge);
        edgeIds.add(rightEdgeId);

        // Let's try NOT flipping the bottom & left edges so we don't have to re-/un-flip them to do matching
        // Bottom edge
        char[] bottomEdge = tileData[LINE_LENGTH - 1];
//        int bottomEdgeId = flip(calculateEdgeId(bottomEdge));  // This will be reversed compared to the top edge
        int bottomEdgeId = calculateRowId(bottomEdge);
        edgeIds.add(bottomEdgeId);


        // Left edge
        char[] leftEdge = getColumn(tileData, 0);
//        int leftEdgeId = flip(calculateEdgeId(leftEdge));  // This will be reversed compared to the right edge
        int leftEdgeId = calculateRowId(leftEdge);
        edgeIds.add(leftEdgeId);
    }

    public void orientNeighbors(boolean recurse) {
        // Orient all of this tile's neighbors to match it. Then lock them.

        if (DEBUG) {
            System.out.println("=== Orienting neighbors of ===");
            printTile();
        }

        for (Tile neighbor : matchedTiles) {
            // If this neighbor is already locked, skip it
            if (neighbor.isLocked()) {
                if (DEBUG) System.out.println("\tLocked:\tTile #" + neighbor.getId());
                continue;   // << THIS STOPS THE RECURSION
            }
            // else figure out how to orient it to us
            // Which side does it match us on?
            int side;
            side:
            for (side = TOP; side <= LEFT; side++) {
                int ourEdgeId = edgeIds.get(side);
                if (neighbor.getEdgeIds().contains(ourEdgeId)
                        || neighbor.getEdgeIds().contains(flip(ourEdgeId))) {
                    // If the neighbor matches our edgeId or a FLIP of it,
                    // We need to rotate & flip it until it matches us on the side OPPOSITE ours
                    int neighborSide = (side + 2) % 4; // BOTTOM -> TOP; LEFT -> RIGHT; etc.
                    // Try every rotation. If it matches, bail out.
                    for (int i = 0; i < 4; i++) {
                        if (ourEdgeId == neighbor.getEdgeId(neighborSide)) break side;
                        neighbor.rotateCW();
                    }
                    // No match yet, flip it
                    neighbor.flipTile(TOPBOTTOM);
                    // And try again
                    for (int i = 0; i < 4; i++) {
                        if (ourEdgeId == neighbor.getEdgeId(neighborSide)) break side;
                        neighbor.rotateCW();
                    }
                }
                // else this neighbor doesn't match this side; check the next side
            }
            // Once the neighbor is rotated, lock it in place
            if (DEBUG) {
                System.out.println("** " + getId() + " Side:\t" + side);
                neighbor.printTile();
            }
            XYPoint neighborXY = null;
            switch (side) {
                case TOP:
                    neighborXY = this.location.getRelativeLocation(UP);
                    break;
                case RIGHT:
                    neighborXY = this.location.getRelativeLocation(Direction8Way.RIGHT);
                    break;
                case BOTTOM:
                    neighborXY = this.location.getRelativeLocation(DOWN);
                    break;
                case LEFT:
                    neighborXY = this.location.getRelativeLocation(Direction8Way.LEFT);
                    break;
            }
            neighbor.setLocation(neighborXY);
            neighbor.setLocked(true);
            // neighbor is oriented, so either go to the next one or recurse onward
            if (recurse) neighbor.orientNeighbors(recurse);
        }
    }

    public void orientAllPieces() {
        // Starting from THIS tile, recursively orient all other tiles
        // Orient our immediate neighbors
        this.orientNeighbors(true);
        // Now that they're in the correct orientation

    }

    public void rotateCW() {
        // Rotate the tileData matrix 90 degree clockwise
        // Ref: https://www.geeksforgeeks.org/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/
        // Traverse each cycle
        for (int i = 0; i < LINE_LENGTH / 2; i++) {
            for (int j = i; j < LINE_LENGTH - i - 1; j++) {

                // Swap elements of each cycle
                // in clockwise direction
                char temp = tileData[i][j];
                tileData[i][j] = tileData[LINE_LENGTH - 1 - j][i];
                tileData[LINE_LENGTH - 1 - j][i] = tileData[LINE_LENGTH - 1 - i][LINE_LENGTH - 1 - j];
                tileData[LINE_LENGTH - 1 - i][LINE_LENGTH - 1 - j] = tileData[j][LINE_LENGTH - 1 - i];
                tileData[j][LINE_LENGTH - 1 - i] = temp;
            }
        }

        // rotate the edgeIds to the right by one
//        Collections.rotate(edgeIds, CW);
        // When right (1) goes to bottom (2), it gets flipped
//        flipEdge(BOTTOM);
        // When left (3) goes to top (0), it gets flipped
//        flipEdge(TOP);
        calculateEdges();
    }

    public void flipTile(FlipDirection direction) {
        char[] temp;
        switch (direction) {
            case TOPBOTTOM:
                // Flip the tileData top to bottom
                for (int i = 0; i < LINE_LENGTH / 2; i++) {
                    temp = tileData[i];
                    tileData[i] = tileData[LINE_LENGTH - 1 - i];
                    tileData[LINE_LENGTH - 1 - i] = temp;
                }
                break;
            case LEFTRIGHT:
                // Flip the tileData left to right
                for (int i = 0; i < LINE_LENGTH / 2; i++) {
                    temp = getColumn(tileData, i);
                    setColumn(tileData, i, getColumn(tileData, LINE_LENGTH - 1 - i));
                    setColumn(tileData, LINE_LENGTH - 1 - i, temp);
                }
                break;
        }
        calculateEdges();
    }

    private void flipEdge(int edge) {
        edgeIds.set(edge, flip(edgeIds.get(edge)));
    }

    private void switchEdge(int edge) {
        int temp;
        switch (edge) {
            case TOP:
            case BOTTOM:
                temp = edgeIds.get(TOP);
                edgeIds.set(TOP, edgeIds.get(BOTTOM));
                edgeIds.set(BOTTOM, temp);
            case RIGHT:
            case LEFT:
                temp = edgeIds.get(RIGHT);
                edgeIds.set(RIGHT, edgeIds.get(LEFT));
                edgeIds.set(LEFT, temp);
        }
    }

    public int calculateRowId(char[] edgeArray) {
        int edgeId = 0;
        for (Character c : edgeArray) {
            // Process each character in the line
            edgeId <<= 1;   // shift it left
            if (c.equals(ONE_CHAR)) {
                // If this is a '1' in our edge fingerprint/id
                edgeId |= 1; // Put a one in the leftmost bit
            }
        }
        return edgeId;
    }

    public boolean matchesTile(Tile otherTile) {
        // This tile matches otherTile if one edgeId of this tile (flipped or not flipped)
        // matches one edgeId of the otherTile
        if (this == otherTile) return false;    // don't match ourselves
        if (matchedTiles.size() == 4) return true;  // If we're full
        if (matchedTiles.contains(otherTile)) return true;  // If we already know the answer from a previous calc
        for (int otherEdgeId : otherTile.getEdgeIds()) {
            if (edgeIds.contains(otherEdgeId) || edgeIds.contains(flip(otherEdgeId))) {
                // If this edge (from the other tile) matches one of our edges, flipped or not
                // Add both tiles to each other's list of matches
                matchedTiles.add(otherTile);
                otherTile.matchedTiles.add(this);
                return true;
            }
        }
        // If we get here, nothing matched.
        return false;
    }

    public static int flip(int edgeId) {
        // Reverse the bits in the edgeId and return the resulting value
        int flippedId = 0;
        int MASK_BIT = 1;
        int bit;
        for (int i = 0; i <= LINE_LENGTH; i++) {
            // Get the i'th bit of the incoming value
            bit = edgeId & (MASK_BIT << i);
            // Then slide that bit to its new home
            //      e.g bit 0 -> bit 9      slide by 9
            //          bit 3 -> bit 6      slide by 3
            //          bit 7 -> bit 2      slide by -5
            //          bit i -> bit 9-i
            // Shift left or right (I thought we could shift by negative distances but I guess not)
            int shift = ((LINE_LENGTH - 1) - 2 * i);
            if (shift > 0) bit <<= shift;
            else if (shift < 0) bit >>= Math.abs(shift);
            // Add the moved bit into the flippedId
            flippedId |= bit;
        }
        return flippedId;
    }

    private char[] getColumn(char[][] matrix, int index) {
        char[] column = new char[LINE_LENGTH];
        for (int i = 0; i < LINE_LENGTH; i++) {
            column[i] = matrix[i][index];
        }
        return column;
    }

    private void setColumn(char[][] matrix, int index, char[] column) {
        for (int i = 0; i < LINE_LENGTH; i++) {
            matrix[i][index] = column[i];
        }
    }

    public void stripEdges() {
        // Remove the top & bottom rows, and the left & right column of this tile
        char[][] stripped = new char[STRIPPED_LENGTH][STRIPPED_LENGTH];
        for (int i = 0; i < STRIPPED_LENGTH; i++) {
            for (int j = 0; j < STRIPPED_LENGTH; j++) {
                stripped[i][j] = tileData[i + 1][j + 1];
            }
        }
        // Replace the tileData with the stripped data
        tileData = stripped;
    }

    // Function for print matrix
    public void printTile() {
        System.out.println("Tile " + id + ":");
        for (int i = 0; i < LINE_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++)
                System.out.print(tileData[i][j]);
            System.out.println();
        }
        System.out.println(); // blank line after
    }


    public int getId() {
        return id;
    }

    public ArrayList<Integer> getEdgeIds() {
        return edgeIds;
    }

    public int getEdgeId(Integer side) {
        return edgeIds.get(side);
    }


    public ArrayList<Tile> getMatchedTiles() {
        return matchedTiles;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setLocation(XYPoint loc) {
        location = loc;
        layout.put(loc.toString(), this);
    }

    static void printLayout(int lineLength) {
        int PUZZLE_WIDTH = 3; // Change this somehow for puzzle
        XYPoint xy = new XYPoint(0, 0);

        for (int tileRow = 0; tileRow < PUZZLE_WIDTH; tileRow++) {
            xy.setY(tileRow);
            for (int row = 0; row < lineLength; row++) {
                xy.setX(0);
                for (int i = 0; i < PUZZLE_WIDTH; i++) {
                    // Print each row of each tile in this tilerow
                    Tile tile = layout.get(xy.toString());
                    char[] tileRowData = tile.tileData[row];
                    // Print all the data for this row of this tile
                    for (int j = 0; j < lineLength; j++)
                        System.out.print(tileRowData[j]);
                    System.out.print(" "); // space between tiles
                    xy = xy.getRelativeLocation(Direction8Way.RIGHT);
                }
                System.out.println();
            }
            System.out.println();
        }


        xy = xy.getRelativeLocation(Direction8Way.RIGHT);

    }
}
