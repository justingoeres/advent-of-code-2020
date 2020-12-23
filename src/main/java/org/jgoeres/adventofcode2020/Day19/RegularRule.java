package org.jgoeres.adventofcode2020.Day19;

import java.util.ArrayList;
import java.util.HashMap;

import static org.jgoeres.adventofcode2020.Day19.Day19Service.DEBUG;

public class RegularRule extends AbstractRule {

    ArrayList<Rule> branch1 = new ArrayList<>();
    ArrayList<Rule> branch2 = new ArrayList<>();
    private String regex = "";

    public RegularRule() {
    }

    public RegularRule(RegularRule regularRule1A, RegularRule regularRule1B,
                       RegularRule regularRule2A, RegularRule regularRule2B) {
        branch1.add(regularRule1A);
        if (regularRule1B != null) branch1.add(regularRule1B);

        branch2.add(regularRule2A);
        if (regularRule2B != null) branch2.add(regularRule2B);
    }

    public static RegularRule create(Integer regularRuleNum,
                                     String regularRule1A, String regularRule1B,
                                     String regularRule2A, String regularRule2B,
                                     HashMap<Integer, RegularRule> allRegularRules) {
        return null;

    }

    public void addBranch1(Rule rule) {
        branch1.add(rule);
    }

    public void addBranch2(Rule rule) {
        branch2.add(rule);
    }

    @Override
    public boolean match(String s, Integer i) {
        /**
         * Example:
         *  3: 4 5 | 5 4
         *  4: "a"
         *  5: "b"
         */
        // This rule matches if all the 'branch1' rules match OR if all the 'branch2' rules match
//        boolean match =
        return false;
    }

    @Override
    public String expandBranch1Match() {
        String matchString = "";
        for (Rule branchRule : branch1) {
            matchString += branchRule.expandBranch1Match();
        }
        return matchString;
    }

    @Override
    public String makeRegex() {
        // Build up the regex for this rule, recursively!
        // Do branch 1 first
        if (DEBUG) System.out.println("Rule #" + getRuleNum());
        if (!regex.equals("")) {
            // If we've already calculated this rule's regex,
            // just return that
            if (DEBUG) System.out.println("\tcached value:\t" + regex);
            return regex;
        } else {
            if (DEBUG) System.out.println("\tcalculating...");
            for (Rule r : branch1) {
                // Concatenate all the rules together
                regex += r.makeRegex();
            }
            // Now process branch 2 if it exists
            if (!branch2.isEmpty()) {
                String regex2 = "";
                for (Rule r : branch2) {
                    regex2 += r.makeRegex();
                }
                // Now put the two pieces together with an OR
                // TODO: as a non-capturing group?
                regex = "(?:" + regex + "|" + regex2 + ")";
                if (DEBUG) System.out.println("Rule #" + getRuleNum() + " calculated:\t" + regex);
            }
        }
        return regex;
    }

    @Override
    public void reset() {
        // Clear the branches
        branch1.clear();
        branch2.clear();
    }

    public ArrayList<Rule> getBranch1() {
        return branch1;
    }

    public void setBranch1(ArrayList<Rule> branch1) {
        this.branch1 = branch1;
    }

    public ArrayList<Rule> getBranch2() {
        return branch2;
    }

    public void setBranch2(ArrayList<Rule> branch2) {
        this.branch2 = branch2;
    }
}
