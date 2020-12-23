package org.jgoeres.adventofcode2020.Day19;

public abstract class AbstractRule implements Rule {
    private int ruleNum;

    public int getRuleNum() {
        return ruleNum;
    }

    public void setRuleNum(int ruleNum) {
        this.ruleNum = ruleNum;
    }

    public void reset() {
        return;
    }
    public int testMatch(String s, int i){
        // Does the incoming String s match at all characters below this one?

    return 0;}

    @Override
    public String makeRegex() {
        return null;
    }
}
