package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day15.RunDay15;
import org.junit.Assert;
import org.junit.Test;

public class Day15Test {
   @Test
   public void Day15A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay15.problem15A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day15B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay15.problem15B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
