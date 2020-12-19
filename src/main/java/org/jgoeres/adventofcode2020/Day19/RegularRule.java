package org.jgoeres.adventofcode2020.Day19;

import java.util.ArrayList;
import java.util.HashMap;

public class RegularRule implements Rule {

    ArrayList<Rule> branch1 = new ArrayList<>();
    ArrayList<Rule> branch2 = new ArrayList<>();

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
