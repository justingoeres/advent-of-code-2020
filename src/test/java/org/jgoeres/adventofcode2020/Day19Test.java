package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day19.RunDay19;
import org.junit.Assert;
import org.junit.Test;

public class Day19Test {
   @Test
   public void Day19A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay19.problem19A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day19B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay19.problem19B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
