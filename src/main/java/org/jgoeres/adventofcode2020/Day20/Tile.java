package org.jgoeres.adventofcode2020.Day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Tile {
    private static final int LINE_LENGTH = 10;
    private static final char ONE = '#';
    private static final char ZERO = '.';

    private static final int TOP = 0;
    private static final int RIGHT = 1;
    private static final int BOTTOM = 2;
    private static final int LEFT = 3;
    private static final int CW = 1;

    enum FlipDirection {
        TOPBOTTOM,
        LEFTRIGHT
    }

    private ArrayList<Integer> edgeIds = new ArrayList<>();
    private HashSet<Tile> matchedTiles = new HashSet<>();

    private int id;
    private char[][] tileData = new char[LINE_LENGTH][LINE_LENGTH];

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
        // Go around the tile clockwise, starting on top
        // Top edge
        char[] topEdge = tileData[0];
        int topEdgeId = calculateEdgeId(topEdge);
        edgeIds.add(topEdgeId);

        // Right edge
        char[] rightEdge = getColumn(tileData, LINE_LENGTH - 1);
        int rightEdgeId = calculateEdgeId(rightEdge);
        edgeIds.add(rightEdgeId);

        // Let's try NOT flipping the bottom & left edges so we don't have to re-/un-flip them to do matching
        // Bottom edge
        char[] bottomEdge = tileData[LINE_LENGTH - 1];
//        int bottomEdgeId = flip(calculateEdgeId(bottomEdge));  // This will be reversed compared to the top edge
        int bottomEdgeId = calculateEdgeId(bottomEdge);
        edgeIds.add(bottomEdgeId);


        // Left edge
        char[] leftEdge = getColumn(tileData, 0);
//        int leftEdgeId = flip(calculateEdgeId(leftEdge));  // This will be reversed compared to the right edge
        int leftEdgeId = calculateEdgeId(leftEdge);
        edgeIds.add(leftEdgeId);
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
        Collections.rotate(edgeIds, CW);
        // When right (1) goes to bottom (2), it gets flipped
//        flipEdge(BOTTOM);
        // When left (3) goes to top (0), it gets flipped
//        flipEdge(TOP);
    }

    public void flipTile(FlipDirection direction) {
        switch (direction) {
            case TOPBOTTOM:
                // Flipping top to bottom means:
                // top & bottom switch places (but do not flip)
                switchEdge(TOP);
                // right & left both flip (but do not switch places)
                flipEdge(RIGHT);
                flipEdge(LEFT);
                break;
            case LEFTRIGHT:
                // Flipping left to right means:
                // top & bottom both flip (but do not switch places)
                flipEdge(TOP);
                flipEdge(BOTTOM);
                // right & left switch places (but do not flip)
                switchEdge(RIGHT);
                break;
        }
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

    private int calculateEdgeId(char[] edgeArray) {
        int edgeId = 0;
        for (Character c : edgeArray) {
            // Process each character in the line
            edgeId <<= 1;   // shift it left
            if (c.equals(ONE)) {
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

    // Function for print matrix
    public void printTile() {
        System.out.println("Tile " + id + ":");
        for (int i = 0; i < LINE_LENGTH; i++) {
            for (int j = 0; j < LINE_LENGTH; j++)
                System.out.print(tileData[i][j]);
            System.out.println();
        }
    }


    public int getId() {
        return id;
    }

    public ArrayList<Integer> getEdgeIds() {
        return edgeIds;
    }

    public HashSet<Tile> getMatchedTiles() {
        return matchedTiles;
    }
}
