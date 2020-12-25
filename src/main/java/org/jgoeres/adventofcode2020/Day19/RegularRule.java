package org.jgoeres.adventofcode2020.Day19;

import java.util.ArrayList;

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

    public void addBranch1(Rule rule) {
        branch1.add(rule);
    }

    public void addBranch2(Rule rule) {
        branch2.add(rule);
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
                // ... in a non-capturing group because we only care whether a whole match exists, not what it is
                regex = "(?:" + regex + "|" + regex2 + ")";
                if (DEBUG) System.out.println("Rule #" + getRuleNum() + " calculated:\t" + regex);
            }
        }
        return regex;
    }
}
