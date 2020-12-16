package org.jgoeres.adventofcode2020.Day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16Service {
    private final String DEFAULT_INPUTS_PATH = "data/day16/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();
    private HashMap<String, Rule> rules = new HashMap<>();

    private ArrayList<Integer> myTicket = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> nearbyTickets = new ArrayList<>();

    public Day16Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day16Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        /** identify invalid nearby tickets by considering only whether tickets
         * contain values that are not valid for any field.
         *
         * Adding together all of the invalid values produces your ticket scanning error rate
         *
         * Consider the validity of the nearby tickets you scanned.
         * What is your ticket scanning error rate?
         **/
        int errorRate = 0;
        // Check each ticket
        for (ArrayList<Integer> ticketFields : nearbyTickets) {
            // Check each field
            for (Integer fieldValue : ticketFields) {
                boolean valid = false;
                // Is this fieldValue valid for ANY of the field rules?
                for (Rule rule : rules.values()) {
                    if (valid = rule.isValid(fieldValue)) {
                        // If we found a valid rule, stop looking
                        break;
                    }
                }
                if (!valid) {
                    // If this field matched NONE of the rules
                    // Accumulate it in our error rate
                    errorRate += fieldValue;
                }
            }
        }
        return errorRate;
    }

    public int doPartB() {
        int result = 0;
        /** Put problem implementation here **/

        return result;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            int fileSection = 0;
            /** Replace this regex **/
            Pattern p1 = Pattern.compile("(.*?):\\s+(\\d+)-(\\d+)\\s+or\\s+(\\d+)-(\\d+)");
            Pattern p2 = Pattern.compile("^\\d"); // all ticket lines start with a digit
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    //blank line, go to next section
                    fileSection++;
                } else {
                    Matcher m2 = p2.matcher(line);
                    String[] fieldValues;
                    switch (fileSection) {
                        case 0: // rules section
                            // Create a rule for this line
                            Matcher m1 = p1.matcher(line);
                            if (m1.find()) { // If our regex matched this line
                                // Parse it
                                String ruleName = m1.group(1);
                                Integer min1 = Integer.parseInt(m1.group(2));
                                Integer max1 = Integer.parseInt(m1.group(3));
                                Integer min2 = Integer.parseInt(m1.group(4));
                                Integer max2 = Integer.parseInt(m1.group(5));
                                Rule newRule = new Rule(min1, min2, max1, max2);
                                rules.put(ruleName, newRule);
                            }
                            break;
                        case 1: // my ticket
                            if (m2.find()) {
                                fieldValues = line.split(",");
                                for (String fieldValue : fieldValues) {
                                    myTicket.add(Integer.parseInt(fieldValue));
                                }
                            }
                            break;
                        case 2: // nearby tickets
                            if (m2.find()) {
                                ArrayList<Integer> newTicket = new ArrayList<>();
                                fieldValues = line.split(",");
                                for (String fieldValue : fieldValues) {
                                    newTicket.add(Integer.parseInt(fieldValue));
                                }
                                nearbyTickets.add(newTicket);
                            }
                            break;
                        default:
                            // nothing
                    }
                }
            }
        } catch (
                Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
