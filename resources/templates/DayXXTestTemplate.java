package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day${AOC_DAY}.RunDay${AOC_DAY};
import org.junit.Assert;
import org.junit.Test;

public class Day${AOC_DAY}Test {
   @Test
   public void Day${AOC_DAY}A() {
       int result = 0;
       try {
           result = RunDay${AOC_DAY}.problem${AOC_DAY}A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }

   @Test
   public void Day${AOC_DAY}B() {
       int result = 0;
       try {
           result = RunDay${AOC_DAY}.problem${AOC_DAY}B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }
}
