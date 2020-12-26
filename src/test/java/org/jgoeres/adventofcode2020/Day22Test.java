package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day22.Day22Service;
import org.jgoeres.adventofcode2020.Day22.RunDay22;
import org.junit.Assert;
import org.junit.Test;

public class Day22Test {

    @Test
    public void Day22AExample1() {
        Day22Service day22Service = new Day22Service("data/day22/example1.txt");
        final long EXPECTED = 306L;
        long result = 0;
        try {
            result = day22Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
   public void Day22A() {
       final long EXPECTED = 33403L;
       long result = 0;
       try {
           result = RunDay22.problem22A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       // 63750 too high
       Assert.assertEquals(EXPECTED, result);
   }


    @Test
    public void Day22BExample1() {
        Day22Service day22Service = new Day22Service("data/day22/example1.txt");
        final long EXPECTED = 291L;
        long result = 0;
        try {
            result = day22Service.doPartB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

   @Test
   public void Day22B() {
       final int EXPECTED = 29177;
       long result = 0;
       try {
           result = RunDay22.problem22B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
