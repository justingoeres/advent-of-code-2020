package org.jgoeres.adventofcode2020.Day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day19Service {
    private final String DEFAULT_INPUTS_PATH = "data/day19/input.txt";
    private static final String EMPTY = "";
    private static final String SPACE = " ";
    public static boolean DEBUG = true;

    private ArrayList<String> inputList = new ArrayList<>();
    public HashMap<Integer, Rule> allRules = new HashMap<>();
    private HashSet<String> partBFirstPass = new HashSet<>();

    public Day19Service() {
        loadInputs(DEFAULT_INPUTS_PATH);
    }

    public Day19Service(String pathToFile) {
        loadInputs(pathToFile);
    }

    public int doPartA() {
        int count = 0;
        /**
         * How many messages completely match rule 0?
         **/
        Rule rule0 = allRules.get(0);
        String rule0Regex = rule0.makeRegex();
        if (DEBUG) {
            System.out.println("To match:\t" + rule0Regex);
        }

        Pattern p = Pattern.compile(rule0Regex);
//        for (Rule rule : allRules.values()) {
//            System.out.println("Rule #" + rule.getRuleNum()
//                    + ":\t" + rule.expandBranch1Match());
//        }
        int i = 0;
        for (String line : inputList) {
            Matcher m = p.matcher(line);
            if (m.matches()) count++;
            if (DEBUG) {
                System.out.println("input #" + i + "\t" + line + "\t" + m.matches());
                i++;
            }
        }
        return count;
    }

    public int countMatches(String s, Rule rule) {
        int count = 0;
        // Test s against all the branches of this rule, all the way down


        return count;
    }

    public int doPartB() {
        int count = 0;
        /** completely replace rules 8: 42 and 11: 42 31
         * with the following:
         * 8: 42 | 42 8
         * 11: 42 31 | 42 11 31
         **/

        // Fix rule 8
        // 8: 42 | 42 8
//        RegularRule toFix = (RegularRule) allRules.get(8);
//        toFix.reset();
//        toFix.addBranch1(allRules.get(42));
//        toFix.addBranch2(allRules.get(42));
//        toFix.addBranch2(allRules.get(8));
//
//        // Fix rule 1
//        // 11: 42 31 | 42 11 31
//        toFix = (RegularRule) allRules.get(11);
//        toFix.reset();
//        toFix.addBranch1(allRules.get(42));
//        toFix.addBranch1(allRules.get(31));
//        toFix.addBranch2(allRules.get(42));
//        toFix.addBranch2(allRules.get(11));
//        toFix.addBranch2(allRules.get(31));

        // Let's pretend we're smart!
        // Rule 0:
        //  8 11
        // Rule 8:
        //  OLD: 42
        //  NEW: 42 *8* << it loops on itself
        // Rule 11:
        //  OLD: 42 31
        //  NEW: 42 31 | 42 *11* 31 << it loops on itself, a little differently than Rule 8 does
        //
        // We know that before substitution all rules terminated, so 42 & 31 still terminate after substitution.
        // That means the ONLY loops are "8 to itself" and "11 to itself"
        //
        // In fact, Rule 8 boils down to
        //  42 8 --> 42 42? 42? 42? ... multiple applications of Rule 42
        // And Rule 11 boils down to
        //  42 11 42 --> 42 42? 42? ... 31 31? 31? ...  multiple applications of 42, followed by multiple applications of 31.
        //
        // So then Rule 0 reduces to:
        //  (8) (11)
        //  (42 8) (42 11 31)
        //  (42 42? 42? ...) (42 42? 42? ... 31 31? 31?)
        //
        // Which is really
        //  "at least two matches of 42, followed by at least one match of 31"
        // Using that, we can build a better Rule 0 regex as:
        //  Rule0 = Rule42 Rule42+ Rule31+
        // and then loop over the input with that?

        // Get our two important rules (42 & 31)
        // But this time, capture with them
        String rule42regex = "(" + allRules.get(42).makeRegex() + ")";
        String rule31regex = "(" + allRules.get(31).makeRegex() + ")";

        // Mush them into the regex string we need
        // Both are already enclosed in non-capturing groups, so we can just concat them
        String regex = rule42regex + rule42regex + "+" + rule31regex + "+";

//        Rule rule0 = allRules.get(0);
//        String rule0Regex = rule0.makeRegex();

        if (DEBUG) {
            System.out.println("To match:\t" + regex);
        }

        Pattern p = Pattern.compile(regex);

        // Anchor the p42 & p31 at the beginning of the strings
        Pattern p42 = Pattern.compile("^" + rule42regex);
        Pattern p31 = Pattern.compile("^" + rule31regex);
//        for (Rule rule : allRules.values()) {
//            System.out.println("Rule #" + rule.getRuleNum()
//                    + ":\t" + rule.expandBranch1Match());
//        }
        int i = 0;
        for (String line : inputList) {
            Matcher m = p.matcher(line);
            if (m.matches()) {
                if (DEBUG) System.out.println("input #" + i + "\t" + line + " matches; checking valid counts");
                // If this matched the giant regex, make sure it has a valid number of 42 & 31 matches.

                // Chop up the string to iteratively match Rule 42s, then Rule 312.
                int match42count = 0;
                Matcher m42 = p42.matcher(line);
                while (m42.find()) {
                    match42count++;
                    line = line.substring(m42.end());
                    m42 = p42.matcher(line);
                }

                int match31count = 0;
                Matcher m31 = p31.matcher(line);
                while (m31.find()) {
                    match31count++;
                    line = line.substring(m31.end());
                    m31 = p31.matcher(line);
                }

                if (DEBUG) System.out.println("\t" + "Rule 42: " + match42count + "\tRule 31: " + match31count);
                // There must be MORE 42 matches than 31 matches
                // (and let's just hope the input doesn't have any tricky ones where they're out of order)
                if (match31count > 0 && match42count > match31count) {
                    if (DEBUG) System.out.println("\tVALID");
                    count++;
                } else {
                    if (DEBUG) {
                        System.out.println("INVALID");
                    }
                }
            }
            if (DEBUG) i++;

        }

        // Now we have a (smaller) set of input lines that match 42 42+ 31+
        // But the actual set of matches is smaller because of the way the recursion is set up.

        return count;
    }

    // load inputs line-by-line and apply a regex to extract fields
    private void loadInputs(String pathToFile) {
        inputList.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line;
            Integer nextInt = 0;
            /**
             * Example:
             *   11: 42 31
             *   51: 117 67 | 33 46
             *   94: 33 | 117
             * **/
            // Ref: https://regex101.com/r/iUjhVe/1
            Pattern p1 = Pattern.compile("(\\d+): ([0-9 ]+)(?:\\| ([0-9 ]+))?");
            Pattern p1a = Pattern.compile("(\\d+): \"(\\w)\""); // match '117: "b"' etc.
            // First section
            while (!(line = br.readLine()).equals(EMPTY)) {
                // process the line.
                Matcher m1 = p1.matcher(line);
                Matcher m1a = p1a.matcher(line);
                if (m1.find()) { // If our regex matched this line
                    // Parse it
                    Integer parentRuleNum = Integer.parseInt(m1.group(1));
                    // Create an empty rule at this number, or get one if it exists
                    RegularRule newRule;
                    if (allRules.containsKey(parentRuleNum)) {
                        // If we've seen this rule before, it exists; get it
                        newRule = (RegularRule) allRules.get(parentRuleNum);
                    } else {
                        // We haven't seen this rule before; create it
                        newRule = new RegularRule();
                        newRule.setRuleNum(parentRuleNum);
                        allRules.put(parentRuleNum, newRule);
                    }
                    String subRules = m1.group(2); // This will always exist
                    for (String subRuleStr : subRules.split(SPACE)) {
                        Integer ruleNum = Integer.parseInt(subRuleStr);
                        Rule subRule;
                        if (allRules.containsKey(ruleNum)) {
                            // If we've seen this rule before, it exists; get it
                            subRule = allRules.get(ruleNum);
                        } else {
                            // We haven't seen this rule before; create it
                            subRule = new RegularRule();
                            subRule.setRuleNum(ruleNum);
                            allRules.put(ruleNum, subRule);
                        }
                        newRule.addBranch1(subRule);
                    }

                    if (m1.group(3) != null) { // If there is a second group of rules (after the '|')
                        subRules = m1.group(3);
                        for (String subRuleStr : subRules.split(SPACE)) {
                            Integer ruleNum = Integer.parseInt(subRuleStr);
                            Rule subRule;
                            if (allRules.containsKey(ruleNum)) {
                                // If we've seen this rule before, it exists; get it
                                subRule = allRules.get(ruleNum);
                            } else {
                                // We haven't seen this rule before; create it
                                subRule = new RegularRule();
                                subRule.setRuleNum(ruleNum);
                                allRules.put(ruleNum, subRule);
                            }
                            newRule.addBranch2(subRule);
                        }
                    }
                } else if (m1a.find()) {
                    // Else if it's a base rule
                    Integer baseRuleNum = Integer.parseInt(m1a.group(1));
                    BaseRule baseRule;
                    if (allRules.containsKey(baseRuleNum)) {
                        // If we've seen this rule before, it exists; get it
                        baseRule = (BaseRule) allRules.get(baseRuleNum);
                    } else {
                        // We haven't seen this rule before; create it
                        baseRule = new BaseRule();
                        baseRule.setRuleNum(baseRuleNum);
                        allRules.put(baseRuleNum, baseRule);
                    }
                    Character ruleChar = m1a.group(2).charAt(0);
                    baseRule.setRuleChar(ruleChar);
                }
            }
            // Second section
            while ((line = br.readLine()) != null) {
                // process the line.
                inputList.add(line);
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
