package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day20.Day20Service;
import org.jgoeres.adventofcode2020.Day20.RunDay20;
import org.junit.Assert;
import org.junit.Test;

public class Day20Test {

    @Test
    public void Day20AExample1() {
        Day20Service day20Service = new Day20Service("data/day20/example1.txt");
        final long EXPECTED = 20899048083289L;
        long result = 0;
        try {
            result = day20Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
    
   @Test
   public void Day20A() {
       final long EXPECTED = 47213728755493L;
       long result = 0;
       try {
           result = RunDay20.problem20A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

    @Test
    public void Day20BExample1() {
        Day20Service day20Service = new Day20Service("data/day20/example1.txt");
        final long EXPECTED = 20899048083289L;
        long result = 0;
        try {
            result = day20Service.doPartB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

   @Test
   public void Day20B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay20.problem20B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
