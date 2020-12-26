package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day24.Day24Service;
import org.jgoeres.adventofcode2020.Day24.RunDay24;
import org.junit.Assert;
import org.junit.Test;

public class Day24Test {
    @Test
    public void Day24AExample1() {
        Day24Service day24Service = new Day24Service("data/day24/example1.txt");
        final int EXPECTED = 10;
        int result = 0;
        try {
            result = day24Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
    
   @Test
   public void Day24A() {
       final int EXPECTED = 420;
       int result = 0;
       try {
           result = RunDay24.problem24A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day24B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay24.problem24B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
