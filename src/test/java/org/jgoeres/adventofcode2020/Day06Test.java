package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day06.RunDay06;
import org.junit.Assert;
import org.junit.Test;

public class Day06Test {
   @Test
   public void Day06A() {
       int result = 0;
       try {
           result = RunDay06.problem06A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }

   @Test
   public void Day06B() {
       int result = 0;
       try {
           result = RunDay06.problem06B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }
}
