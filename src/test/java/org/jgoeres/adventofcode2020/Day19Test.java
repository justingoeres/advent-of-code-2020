package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day19.Day19Service;
import org.jgoeres.adventofcode2020.Day19.RunDay19;
import org.junit.Assert;
import org.junit.Test;

public class Day19Test {

    @Test
    public void Day19AExample1() {
        Day19Service day19Service = new Day19Service("data/day19/example1.txt");
        final long EXPECTED = 2L;
        long result = 0;
        try {
            result = day19Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day19AExample2() {
        Day19Service day19Service = new Day19Service("data/day19/example2.txt");
        final long EXPECTED = 3L;
        long result = 0;
        try {
            result = day19Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day19A() {
        final int EXPECTED = 173;
        int result = 0;
        try {
            result = RunDay19.problem19A();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }


    @Test
    public void Day19BExample2() {
        Day19Service day19Service = new Day19Service("data/day19/example2.txt");
        final long EXPECTED = 12L;
        long result = 0;
        try {
            result = day19Service.doPartB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day19B() {
        final int EXPECTED = 367;
        int result = 0;
        try {
            result = RunDay19.problem19B();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 279 too low
        // 286 wrong
        // 374 too high
        Assert.assertEquals(EXPECTED, result);
    }
}
