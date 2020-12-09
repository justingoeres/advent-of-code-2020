package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day09.RunDay09;
import org.junit.Assert;
import org.junit.Test;

public class Day09Test {
   @Test
   public void Day09A() {
       final int EXPECTED = null;
       int result = 0;
       try {
           result = RunDay09.problem09A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day09B() {
       final int EXPECTED = null;
       int result = 0;
       try {
           result = RunDay09.problem09B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
