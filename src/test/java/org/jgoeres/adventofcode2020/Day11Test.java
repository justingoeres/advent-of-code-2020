package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day11.SeatingSystemService;
import org.jgoeres.adventofcode2020.Day11.RunDay11;
import org.junit.Assert;
import org.junit.Test;

public class Day11Test {

    @Test
    public void Day11AExample1() {
        final long EXPECTED = 37;
        SeatingSystemService seatingSystemService = new SeatingSystemService("data/day11/example1.txt");
        long result = 0;
        try {
            result = seatingSystemService.doPartA();;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
   public void Day11A() {
       final int EXPECTED = 2108;
       int result = 0;
       try {
           result = RunDay11.problem11A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day11B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay11.problem11B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
