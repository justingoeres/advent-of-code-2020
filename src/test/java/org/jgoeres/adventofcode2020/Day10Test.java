package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day10.AdapterService;
import org.jgoeres.adventofcode2020.Day10.RunDay10;
import org.junit.Assert;
import org.junit.Test;

public class Day10Test {
   @Test
   public void Day10A() {
       final int EXPECTED = 1625;
       int result = 0;
       try {
           result = RunDay10.problem10A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

    @Test
    public void Day10BExample1() {
        final long EXPECTED = 8;
        AdapterService adapterService = new AdapterService("data/day10/example1.txt");
        long result = 0;
        try {
            result = adapterService.doPartB();;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day10BExample2() {
        final long EXPECTED = 19208;
        AdapterService adapterService = new AdapterService("data/day10/example2.txt");
        long result = 0;
        try {
            result = adapterService.doPartB();;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
   public void Day10B() {
       final Long EXPECTED = 3100448333024L;
       Long result = 0L;
       try {
           result = RunDay10.problem10B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
