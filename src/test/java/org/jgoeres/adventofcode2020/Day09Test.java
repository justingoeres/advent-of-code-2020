package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day09.Day09Service;
import org.jgoeres.adventofcode2020.Day09.RunDay09;
import org.junit.Assert;
import org.junit.Test;

public class Day09Test {

    @Test
    public void Day09AExample1() {
        final int EXAMPLE_PREAMBLE = 5;
        final int EXPECTED = 127;
        Day09Service day09Service = new Day09Service("data/day09/example1.txt");
        long result = 0;
        try {
            result = day09Service.doPartA(EXAMPLE_PREAMBLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
   @Test
   public void Day09A() {
       final int EXPECTED = 25918798;
       long result = 0;
       try {
           result = RunDay09.problem09A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day09B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay09.problem09B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
