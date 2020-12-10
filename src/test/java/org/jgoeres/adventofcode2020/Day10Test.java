package org.jgoeres.adventofcode2020;

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
   public void Day10B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay10.problem10B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
