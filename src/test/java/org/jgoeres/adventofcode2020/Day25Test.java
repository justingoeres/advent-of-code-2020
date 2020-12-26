package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day25.RunDay25;
import org.junit.Assert;
import org.junit.Test;

public class Day25Test {
   @Test
   public void Day25A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay25.problem25A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day25B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay25.problem25B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
