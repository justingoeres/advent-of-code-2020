package org.jgoeres.adventofcode2020.Day19;

public abstract class AbstractRule implements Rule {
    private int ruleNum;

    public int getRuleNum() {
        return ruleNum;
    }

    public void setRuleNum(int ruleNum) {
        this.ruleNum = ruleNum;
    }

    @Override
    public String makeRegex() {
        return null;
    }
}
