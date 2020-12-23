package org.jgoeres.adventofcode2020.Day19;

public interface Rule {
    boolean match(String s, Integer i);

    int getRuleNum();

    void setRuleNum(int ruleNum);

    String expandBranch1Match();

    String makeRegex();

    void reset();
}
