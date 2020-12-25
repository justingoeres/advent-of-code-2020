package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day21.Day21Service;
import org.jgoeres.adventofcode2020.Day21.RunDay21;
import org.junit.Assert;
import org.junit.Test;

public class Day21Test {
//    @Test
//    public void Day21AExample1() {
//        Day21Service day21Service = new Day21Service("data/day21/example1.txt");
//        final long EXPECTED = 5L;
//        long result = 0;
//        try {
//            result = day21Service.doPartA();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        Assert.assertEquals(EXPECTED, result);
//    }
    
   @Test
   public void Day21A() {
       final int EXPECTED = 2125;
       int result = 0;
       try {
           result = RunDay21.problem21A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

//    @Test
//    public void Day21BExample1() {
//        Day21Service day21Service = new Day21Service("data/day21/example1.txt");
//        final String EXPECTED = "mxmxvkd,sqjhc,fvjkl";
//        String result = "";
//        try {
//            result = day21Service.doPartB();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        Assert.assertEquals(EXPECTED, result);
//    }

   @Test
   public void Day21B() {
       final String EXPECTED = "phc,spnd,zmsdzh,pdt,fqqcnm,lsgqf,rjc,lzvh";
       String result = "";
       try {
           result = RunDay21.problem21B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
