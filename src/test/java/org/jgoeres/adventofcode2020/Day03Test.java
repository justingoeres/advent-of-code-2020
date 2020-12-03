package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day03.RunDay03;
import org.junit.Assert;
import org.junit.Test;

public class Day03Test {
   @Test
   public void Day03A() {
       int result = 0;
       try {
           result = RunDay03.problem03A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(151, result);
   }

   @Test
   public void Day03B() {
       int result = 0;
       try {
           result = RunDay03.problem03B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }
}
