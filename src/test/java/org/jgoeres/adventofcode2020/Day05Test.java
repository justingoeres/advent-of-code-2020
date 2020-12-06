package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day05.RunDay05;
import org.junit.Assert;
import org.junit.Test;

public class Day05Test {
   @Test
   public void Day05A() {
       int result = 0;
       try {
           result = RunDay05.problem05A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }

   @Test
   public void Day05B() {
       int result = 0;
       try {
           result = RunDay05.problem05B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }
}
