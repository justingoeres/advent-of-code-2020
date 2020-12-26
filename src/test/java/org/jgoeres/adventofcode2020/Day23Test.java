package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day23.RunDay23;
import org.junit.Assert;
import org.junit.Test;

public class Day23Test {
   @Test
   public void Day23A() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay23.problem23A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day23B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay23.problem23B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}