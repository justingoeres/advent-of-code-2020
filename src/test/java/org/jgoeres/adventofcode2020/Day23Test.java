package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day23.Day23Service;
import org.jgoeres.adventofcode2020.Day23.RunDay23;
import org.junit.Assert;
import org.junit.Test;

public class Day23Test {
    @Test
    public void Day23AExample1() {
        Day23Service day23Service = new Day23Service("data/day23/example1.txt");
        final long EXAMPLE_LIMIT = 10L;
        final String EXPECTED = "92658374";
        String result="";
        try {
            result = day23Service.doPartA(EXAMPLE_LIMIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day23AExample2() {
        Day23Service day23Service = new Day23Service("data/day23/example1.txt");
        final long EXAMPLE_LIMIT = 100L;    // Same as Example 1 but 100 iterations
        final String EXPECTED = "67384529";
        String result="";
        try {
            result = day23Service.doPartA(EXAMPLE_LIMIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day23A() {
        final String EXPECTED = "32658947";
        String result = "";
        try {
            result = RunDay23.problem23A();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day23B() {
        final int EXPECTED = 0;
        int result = 0;
        try {
            result = RunDay23.problem23B();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
}
