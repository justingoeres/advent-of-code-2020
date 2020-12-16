package org.jgoeres.adventofcode2020.Day16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16Service {
    private final String DEFAULT_INPUTS_PATH = "data/day16/input.txt";

    private static boolean DEBUG = false;

    private ArrayList<Integer> inputList = new ArrayList<>();
    private HashMap<String, Rule> rules = new HashMap<>();

    private ArrayList<Integer> myTicket = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> nearbyTickets = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> validTickets = new ArrayList<>();

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
        for (ArrayList<Integer> ticket : nearbyTickets) {
            // Check each field
            boolean ticketValid = true; // assume ticket is valid until it's not.
            for (Integer fieldValue : ticket) {
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
                    // Mark the whole ticket as invalid
                    ticketValid = false;
                }
            }
            // Now that we've checked the whole ticket,
            // if it's valid, add it to the list of valid tickets for Part B
            if (ticketValid) {
                validTickets.add(ticket);
            }
        }
        return errorRate;
    }

    public long doPartB() {
        long result = 1;
        /** Now that you've identified which tickets contain invalid values,
         * discard those tickets entirely.
         *
         * Use the remaining valid tickets to determine which field is which.
         **/
        // Use the list of valid tickets from Part A, or generate it here
        if (validTickets.isEmpty()) {
            // Need to generate valid tickets; Part A will do it for us
            doPartA();
        }

        // For each ticket field index (0-19)
        // Make a Set of all potentially valid Rules for this field (it starts out as "All of them")
//        HashMap<Integer, Set<String>> validRulesForField = new HashMap<>();
//        for (int i = 0; i < myTicket.size(); i++) {
//            // For each field, build the list of valid rules (all of them)
//            Set<String> initialRules = new HashSet<>(rules.keySet());
//            validRulesForField.put(i, initialRules);
//        }

        // For each RULE
        for (Rule rule : rules.values()) {
            // Initialize its list of fields it can be valid for
            // to "all the fields"
            for (int i = 0; i < myTicket.size(); i++) {
                rule.addValidField(i);
            }
        }

        // Now check each FIELD in each TICKET and if a Rule is invalid for that FIELD, remove it
        // Each ticket
        for (ArrayList<Integer> ticket : validTickets) {
            if (DEBUG) System.out.println("Checking Ticket beginning with:\t" + ticket.get(0));
            // Each field on the ticket
            for (int i = 0; i < ticket.size(); i++) {
                if (DEBUG) System.out.println("- Checking Field #:\t" + i + "\t(" + ticket.get(i) + ")");
                // Each rule for this field
                for (Rule rule : rules.values()) {
                    if (DEBUG) System.out.print("- - Checking Rule:\t" + rule.toString());
                    if (rule.getValidForFields().contains(i)  // Do we still think this rule is valid?
                            && !rule.isValid(ticket.get(i))) {  // Is this rule INvalid for this particular field?
                        if (DEBUG) System.out.println("\tINVALID");
                        rule.removeValidField(i);   // Remove it from consideration for this field
                    } else {
                        if (DEBUG) System.out.println("\tValid");
                    }
                }
            }
        }
        // Now check all the RULES. There should be at least one rule
        // that is valid for ONLY ONE field.
        // Find it, and remove that field from the list of valid fields for every other rule
        // As we go, count up the number of field's we've located that begin with 'departure'
        // - we need those for the answer
        int departuresFound = 0;
        while (true) {  // go until we've narrowed down all the departure fields
            for (String ruleName : rules.keySet()) {
                Rule rule = rules.get(ruleName);
                // Is this rule valid for only one field?
                if (rule.getValidForFields().size() == 1) {
                    // Iterating here (over the ONE element) seems like cheating
                    Integer validFieldNum = null;
                    for (Integer fieldNum : rule.getValidForFields()) {
                        if (DEBUG) System.out.println("Rule '" + ruleName + "' is valid only for field #\t" + fieldNum);
                        validFieldNum = fieldNum;
                    }
                    // Remove this field from the valid fields of every other rule
                    for (Rule ruleToUpdate : rules.values()) {
                        ruleToUpdate.removeValidField(validFieldNum);
                    }
                    // If this is a departure rule, multiple its value on myTicket to the running answer!
                    if (ruleName.startsWith("departure")) {
                        Integer fieldValue = myTicket.get(validFieldNum);
                        System.out.println("myTicket '" + ruleName + "':\t" + fieldValue);
                        result *= fieldValue;
                        departuresFound++;  // update our count of departures found
                        if (departuresFound == 6) {
                            // We found them all!
                            if (DEBUG) System.out.println("Product of all 'departures':\t" + result);
                            return result;
                        }
                    }
                }

            }
        }

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
                                Rule newRule = new Rule(min1, max1, min2, max2);
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
