package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day11.RunDay11;
import org.junit.Assert;
import org.junit.Test;

public class Day11Test {
   @Test
   public void Day11A() {
       final int EXPECTED = 0;
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
