package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day25.Day25Service;
import org.jgoeres.adventofcode2020.Day25.RunDay25;
import org.junit.Assert;
import org.junit.Test;

public class Day25Test {
    @Test
    public void Day25AExample1() {
        Day25Service day25Service = new Day25Service("data/day25/example1.txt");
        final long EXPECTED = 14897079;
        long result = 0;
        try {
            result = day25Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day25A() {
        final int EXPECTED = 10548634;
        long result = 0;
        try {
            result = RunDay25.problem25A();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 14239149
        // 14239149 too high
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day25B() {
        final int EXPECTED = 0;
        int result = 0;
        try {
            result = RunDay25.problem25B();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
}
