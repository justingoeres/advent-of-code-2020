package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day14.Day14Service;
import org.jgoeres.adventofcode2020.Day14.RunDay14;
import org.junit.Assert;
import org.junit.Test;

public class Day14Test {

    @Test
    public void Day14AExample1() {
        Day14Service day14Service = new Day14Service("data/day14/example1.txt");
        final long EXPECTED = 165L;
        long result = 0;
        try {
            result = day14Service.doPartA();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

   @Test
   public void Day14A() {
       final long EXPECTED = 14553106347726L;
       long result = 0;
       try {
           result = RunDay14.problem14A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       // 4881952374 too low
       Assert.assertEquals(EXPECTED, result);
   }

    @Test
    public void Day14BExample2() {
        Day14Service day14Service = new Day14Service("data/day14/example2.txt");
        final long EXPECTED = 208L;
        long result = 0;
        try {
            result = day14Service.doPartB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

   @Test
   public void Day14B() {
       final long EXPECTED = 2737766154126L;
       long result = 0;
       try {
           result = RunDay14.problem14B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
