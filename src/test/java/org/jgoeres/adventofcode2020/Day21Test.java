package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day21.RunDay21;
import org.junit.Assert;
import org.junit.Test;

public class Day21Test {
   @Test
   public void Day21A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay21.problem21A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day21B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay21.problem21B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
