package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day20.RunDay20;
import org.junit.Assert;
import org.junit.Test;

public class Day20Test {
   @Test
   public void Day20A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay20.problem20A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day20B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay20.problem20B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
