package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day04.RunDay04;
import org.junit.Assert;
import org.junit.Test;

public class Day04Test {
   @Test
   public void Day04A() {
       long result = 0;
       try {
           result = RunDay04.problem04A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(192, result);
   }

   @Test
   public void Day04B() {
       int result = 0;
       try {
           result = RunDay04.problem04B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }
}
