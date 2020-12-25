package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day22.RunDay22;
import org.junit.Assert;
import org.junit.Test;

public class Day22Test {
   @Test
   public void Day22A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay22.problem22A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day22B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay22.problem22B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
