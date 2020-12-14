package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day14.RunDay14;
import org.junit.Assert;
import org.junit.Test;

public class Day14Test {
   @Test
   public void Day14A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay14.problem14A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day14B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay14.problem14B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
