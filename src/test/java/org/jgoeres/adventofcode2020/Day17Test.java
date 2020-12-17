package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day17.RunDay17;
import org.junit.Assert;
import org.junit.Test;

public class Day17Test {
   @Test
   public void Day17A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay17.problem17A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day17B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay17.problem17B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
