package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day18.Day18Service;
import org.jgoeres.adventofcode2020.Day18.RunDay18;
import org.junit.Assert;
import org.junit.Test;

public class Day18Test {

    @Test
    public void Day18AExample1() {
        Day18Service day18Service = new Day18Service();
        final int EXPECTED = 71;
        long result = 0;
        try {
            result = day18Service.evaluate("1 + 2 * 3 + 4 * 5 + 6");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample2() {
        Day18Service day18Service = new Day18Service();
        final int EXPECTED = 51;
        long result = 0;
        try {
            result = day18Service.evaluate("1 + (2 * 3) + (4 * (5 + 6))");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample3() {
        Day18Service day18Service = new Day18Service();
        final int EXPECTED = 26;
        long result = 0;
        try {
            result = day18Service.evaluate("2 * 3 + (4 * 5)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample4() {
        Day18Service day18Service = new Day18Service();
        final int EXPECTED = 437;
        long result = 0;
        try {
            result = day18Service.evaluate("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample5() {
        Day18Service day18Service = new Day18Service();
        final int EXPECTED = 12240;
        long result = 0;
        try {
            result = day18Service.evaluate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample6() {
        Day18Service day18Service = new Day18Service();
        final int EXPECTED = 13632;
        long result = 0;
        try {
            result = day18Service.evaluate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18A() {
        final int EXPECTED = 0;
        long result = 0;
        try {
            result = RunDay18.problem18A();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18B() {
        final int EXPECTED = 0;
        long result = 0;
        try {
            result = RunDay18.problem18B();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
}
