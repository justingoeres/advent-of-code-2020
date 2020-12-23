package org.jgoeres.adventofcode2020.Day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
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
