package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day18.RunDay18;
import org.junit.Assert;
import org.junit.Test;

public class Day18Test {
   @Test
   public void Day18A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay18.problem18A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day18B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay18.problem18B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
