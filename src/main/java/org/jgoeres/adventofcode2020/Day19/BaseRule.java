package org.jgoeres.adventofcode2020.Day19;

public class BaseRule implements Rule {
    private Character ruleChar;


    public BaseRule() {
    }

    public BaseRule(Character ruleChar) {
        this.ruleChar = ruleChar;
    }

    public Character getRuleChar() {
        return ruleChar;
    }

    public void setRuleChar(Character ruleChar) {
        this.ruleChar = ruleChar;
    }
}
