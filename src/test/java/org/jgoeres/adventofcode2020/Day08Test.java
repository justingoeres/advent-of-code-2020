package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day08.RunDay08;
import org.junit.Assert;
import org.junit.Test;

public class Day08Test {
   @Test
   public void Day08A() {
       int result = 0;
       try {
           result = RunDay08.problem08A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(1614, result);
   }

   @Test
   public void Day08B() {
       int result = 0;
       try {
           result = RunDay08.problem08B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }
}
