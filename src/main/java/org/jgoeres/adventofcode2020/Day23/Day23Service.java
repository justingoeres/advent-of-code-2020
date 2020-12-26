package org.jgoeres.adventofcode2020.Day23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23Service {
    private final String DEFAULT_INPUTS_PATH = "data/day23/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();
    private HashMap<Integer, Cup> cups = new HashMap<>();
    private Cup current;

    private ArrayList<Cup> removedCups = new ArrayList<>();

    public Day23Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day23Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA(long limit) {
        int result = 0;
        /**
         * After 'limit' moves, what are the labels on the cups AFTER cup 1?
         **/
        for (int i = 0; i < limit; i++) {
            doMove();
        }
        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    private void doMove() {
        /**
         * Each move, the crab does the following actions:
         *
         * The crab picks up the three cups that are immediately clockwise of the current cup.
         * They are removed from the circle; cup spacing is adjusted as necessary to maintain
         * the circle.
         *
         * The crab selects a destination cup: the cup with a label equal to the current cup's
         * label minus one. If this would select one of the cups that was just picked up, the
         * crab will keep subtracting one until it finds a cup that wasn't just picked up.
         * If at any point in this process the value goes below the lowest value on any cup's
         * label, it wraps around to the highest value on any cup's label instead.
         *
         * The crab places the cups it just picked up so that they are immediately clockwise
         * of the destination cup. They keep the same order as when they were picked up.
         *
         * The crab selects a new current cup: the cup which is immediately clockwise of
         * the current cup.
         **/

        removedCups.clear();
        // The crab picks up the THREE CUPS that are immediately clockwise (NEXT) of the current cup.
        // They are removed from the circle; cup spacing is adjusted as necessary to maintain
        // the circle.
        removedCups.add(removeNextCup(current));
        removedCups.add(removeNextCup(current));
        removedCups.add(removeNextCup(current));

        // The crab selects a destination cup: the cup with a label equal to the current cup's
        // label minus one. If this would select one of the cups that was just picked up, the
        // crab will keep subtracting one until it finds a cup that wasn't just picked up.
        // TODO: WORKING HERE
        int destinationLabel = current.getId() - 1;


        // If at any point in this process the value goes below the lowest value on any cup's
        // label, it wraps around to the highest value on any cup's label instead.
    }

    private Cup removeNextCup(Cup cup) {
        // Remove the CUP TO THE RIGHT OF the specified cup from the circle and update its neighbors
        Cup removed = cup.getNext();
        // Remove it from the map of cups too
        cups.remove(removed);
        // Connect its prev & next cups to each other
        Cup removedPrev = removed.getPrev();
        Cup removedNext = removed.getNext();
        removedPrev.setNext(removedNext);
        // Return the cup we removed
        return removed;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /**
             * Example:
             *  598162734
             **/
            Pattern p = Pattern.compile("(\\d)"); // read single ints
            Cup prev = null;
            Cup head = null;
            Cup cup = null;
            while ((line = br.readLine()) != null) {
                // process the line.
                Matcher m = p.matcher(line);
                while (m.find()) { // If our regex matched this line
                    // Parse it
                    int id = Integer.parseInt(m.group(1));
                    if (prev == null) {
                        // first cup
                        cup = new Cup(id);
                        head = cup; // Remember this is the first cup.
                    } else {
                        // not first cup
                        cup = new Cup(id, prev);
                    }
                    // Add the new cup to the map
                    cups.put(id, cup);
                    // Store the cup we just created as 'prev' and move on
                    prev = cup;
                }
                // When we're done, link the last cup up with the head
                cup.setNext(head);
                // And set the "start" cup to the head
                current = head;
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
