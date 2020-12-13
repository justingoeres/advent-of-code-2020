package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day13.Day13Service;
import org.jgoeres.adventofcode2020.Day13.RunDay13;
import org.junit.Assert;
import org.junit.Test;

public class Day13Test {
   @Test
   public void Day13A() {
       final long EXPECTED = 115;
       long result = 0;
       try {
           result = RunDay13.problem13A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

    @Test
    public void Day13BExample1() {
        Day13Service day13Service = new Day13Service("data/day13/example1.txt");
        final int EXPECTED = 754018;
        long result = 0;
        try {
            result = day13Service.doPartB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

   @Test
   public void Day13B() {
       final long EXPECTED = 756261495958122L;
       long result = 0;
       try {
           result = RunDay13.problem13B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
