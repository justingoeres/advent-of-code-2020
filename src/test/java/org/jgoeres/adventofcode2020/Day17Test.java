package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day17.Day17Service;
import org.jgoeres.adventofcode2020.Day17.RunDay17;
import org.junit.Assert;
import org.junit.Test;

public class Day17Test {
    @Test
    public void Day17AExample1() {
        Day17Service day17Service = new Day17Service("data/day17/example1.txt");
        final long EXPECTED = 112L;
        long result = 0;
        try {
            result = day17Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
    
   @Test
   public void Day17A() {
       final int EXPECTED = 380;
       long result = 0;
       try {
           result = RunDay17.problem17A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day17B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay17.problem17B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
