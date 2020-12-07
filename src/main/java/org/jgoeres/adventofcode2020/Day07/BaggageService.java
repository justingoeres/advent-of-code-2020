package org.jgoeres.adventofcode2020.Day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaggageService {
    private final String DEFAULT_INPUTS_PATH = "data/day07/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();
    private HashMap<String, Bag> allBags = new HashMap<>();

    public BaggageService() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public BaggageService(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int result = 0;
        /** How many bag colors can eventually contain at least one shiny gold bag? **/
        HashSet<Bag> allContainingBags = new HashSet<>();

        Bag targetBag = allBags.get("shiny gold");
        result = findAllContainingBags(targetBag,allContainingBags).size();

        return result;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    private HashSet<Bag> findAllContainingBags(Bag bag, HashSet<Bag> allContainingBags) {
        HashSet<Bag> containingBags = bag.getContainedBy();
        for (Bag containingBag : containingBags) {
            if (!allContainingBags.contains(containingBag)) {
                // If we don't already know about this container
                // Add it to our running list of bags
                allContainingBags.add(containingBag);
                // Process everything that contains it
                findAllContainingBags(containingBag,allContainingBags);
            }
        }
        return allContainingBags;
    }

    private Bag getOrCreateInAllBags(String bagName) {
        // Add this bag to the whole map of bags, or get the existing one.
        Bag currentBag;
        if (!allBags.containsKey(bagName)) {
            // Haven't seen this bag before, so create it
            currentBag = new Bag(bagName);
            allBags.put(bagName, currentBag);
        } else {
            currentBag = allBags.get(bagName);
        }
        return currentBag;
    }

    // load inputs line-by-line and apply a regex to extract fields

    /**
     * light red bags contain 1 bright white bag, 2 muted yellow bags. dark orange bags contain 3 bright white bags, 4
     * muted yellow bags.
     **/

    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /** Replace this regex **/
            /** (light red) bags contain (1 bright white bag, 2 muted yellow bags.) **/
            Pattern p1 = Pattern.compile("(\\w+ \\w+) bags contain (.*)");
            Pattern p2 = Pattern.compile("(\\d) (\\w+ \\w+)");
            while ((line = br.readLine()) != null) {
                // Pull out the color of this bag, and the whole list of things it contains.
                Matcher m1 = p1.matcher(line);
                if (m1.find()) { // If our regex matched this line
                    // The bag color and the whole list of things it contains
                    String bagName = m1.group(1);
                    String field2 = m1.group(2);

                    Bag currentBag = getOrCreateInAllBags(bagName);

                    // Now parse the "contains" list
                    Matcher m2 = p2.matcher(field2);
                    System.out.println(bagName);
                    while (m2.find()) {
                        // Add the "contains" bag
                        System.out.println("\t" + m2.group(1) + "\t" + m2.group(2));
                        Integer count = Integer.parseInt(m2.group(1));
                        String containsBagName = m2.group(2);

                        currentBag.addToContains(count, getOrCreateInAllBags(containsBagName));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
