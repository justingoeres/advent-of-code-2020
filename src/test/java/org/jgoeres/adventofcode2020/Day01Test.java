package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day01.RunDay01;
import org.junit.Assert;
import org.junit.Test;

public class Day01Test {
    @Test
    public void Day1A() {
        int result = RunDay01.problem01A();
        Assert.assertEquals(997899, result);
    }

    @Test
    public void Day1B() {
        int result = RunDay01.problem01B();
        Assert.assertEquals(131248694, result);
    }
}
