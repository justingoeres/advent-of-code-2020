package org.jgoeres.adventofcode2020.Day19;

public class BaseRule extends AbstractRule {
    private Character ruleChar;


    public BaseRule() {
    }

    public BaseRule(Character ruleChar) {
        this.ruleChar = ruleChar;
    }

    @Override
    public boolean match(String s, Integer i) {
        // This base rule matches if the character
        // at position 's' matches the one for the rule
        boolean match = (s.charAt(i) == ruleChar);
        return match;
    }

    @Override
    public String expandBranch1Match() {
        return ruleChar.toString();
    }

    public Character getRuleChar() {
        return ruleChar;
    }

    public void setRuleChar(Character ruleChar) {
        this.ruleChar = ruleChar;
    }

    @Override
    public String makeRegex() {
        // Base rule - just return the character
        return ruleChar.toString();
    }
}
