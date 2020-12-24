package org.jgoeres.adventofcode2020.Day20;

import java.util.HashSet;

public class Tile {
    private static final int LINE_LENGTH = 10;
    private static final char ONE = '#';
    private static final char ZERO = '.';

    private static final int TOP = 0;
    private static final int BOTTOM = 9;

    private HashSet<Integer> edgeIds = new HashSet<>();
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
        // Top edge
        char[] topEdge = tileData[0];
        int topEdgeId = calculateEdgeId(topEdge);
        edgeIds.add(topEdgeId);
        // Bottom edge
        char[] bottomEdge = tileData[LINE_LENGTH - 1];
        int bottomEdgeId = calculateEdgeId(bottomEdge);  // This will be reversed compared top edge
        edgeIds.add(bottomEdgeId);

        // Right edge
        char[] rightEdge = getColumn(tileData, LINE_LENGTH - 1);
        int rightEdgeId = calculateEdgeId(rightEdge);  // This will be reversed compared top edge
        edgeIds.add(rightEdgeId);
        // Left edge
        char[] leftEdge = getColumn(tileData, 0);
        int leftEdgeId = calculateEdgeId(leftEdge);  // This will be reversed compared top edge
        edgeIds.add(leftEdgeId);
        flip(leftEdgeId);
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

    public int getId() {
        return id;
    }

    public HashSet<Integer> getEdgeIds() {
        return edgeIds;
    }

    public HashSet<Tile> getMatchedTiles() {
        return matchedTiles;
    }
}
