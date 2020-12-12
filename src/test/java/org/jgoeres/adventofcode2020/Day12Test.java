package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day12.RunDay12;
import org.junit.Assert;
import org.junit.Test;

public class Day12Test {
   @Test
   public void Day12A() {
       final int EXPECTED = 1177;
       int result = 0;
       try {
           result = RunDay12.problem12A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day12B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay12.problem12B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
