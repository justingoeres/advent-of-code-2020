package org.jgoeres.adventofcode2020.Day16;

public class Rule {
    int min1;
    int max1;
    int min2;
    int max2;

    public Rule(int min1, int max1, int min2, int max2) {
        this.min1 = min1;
        this.max1 = max1;
        this.min2 = min2;
        this.max2 = max2;
    }

    public boolean isValid(int number) {
        boolean isValid1 = (number >= min1) && (number <= max1);
        boolean isValid2 = (number >= min2) && (number <= max2);
        return isValid1 || isValid2;
    }
}
