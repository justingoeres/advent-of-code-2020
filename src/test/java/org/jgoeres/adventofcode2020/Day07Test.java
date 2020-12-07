package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day07.BaggageService;
import org.jgoeres.adventofcode2020.Day07.RunDay07;
import org.junit.Assert;
import org.junit.Test;

public class Day07Test {

    @Test
    public void Day07Example1() {
        BaggageService baggageService = new BaggageService("data/day07/example1.txt");
        int result = 0;
        try {
            result = baggageService.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(4, result);
    }

   @Test
   public void Day07A() {
       int result = 0;
       try {
           result = RunDay07.problem07A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(248, result);
   }

   @Test
   public void Day07B() {
       int result = 0;
       try {
           result = RunDay07.problem07B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(0, result);
   }
}
