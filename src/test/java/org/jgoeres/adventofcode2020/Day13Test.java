package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day13.RunDay13;
import org.junit.Assert;
import org.junit.Test;

public class Day13Test {
   @Test
   public void Day13A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay13.problem13A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day13B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay13.problem13B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
