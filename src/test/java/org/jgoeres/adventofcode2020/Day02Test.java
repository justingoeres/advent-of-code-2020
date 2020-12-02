package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day02.RunDay02;
import org.junit.Assert;
import org.junit.Test;

public class Day02Test {
    static String DAY = "02";

    @Test
    public void Day02A() {
        long result = 0;
        try {
            result = RunDay02.problem02A();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(580, result);
    }

    @Test
    public void Day02B() {
        long result = 0;
        try {
            result = RunDay02.problem02B();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(611, result);
    }
}
