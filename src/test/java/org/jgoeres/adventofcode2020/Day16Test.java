package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day16.RunDay16;
import org.junit.Assert;
import org.junit.Test;

public class Day16Test {
   @Test
   public void Day16A() {
       final int EXPECTED = 29019;
       int result = 0;
       try {
           result = RunDay16.problem16A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day16B() {
       final long EXPECTED = 517827547723L;
       long result = 0;
       try {
           result = RunDay16.problem16B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
