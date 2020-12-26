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
        final long EXPECTED = 306L;
        long result = 0;
        try {
            result = day23Service.doPartA(EXAMPLE_LIMIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day23A() {
        final int EXPECTED = 0;
        int result = 0;
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
