package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day15.Day15Service;
import org.jgoeres.adventofcode2020.Day15.RunDay15;
import org.junit.Assert;
import org.junit.Test;

public class Day15Test {
    final int TARGET_TURN_2020 = 2020;

    @Test
    public void Day15AExample1() {
        final int TARGET_TURN = 10;
        Day15Service day15Service = new Day15Service("0,3,6");
        final long EXPECTED = 0L;
        long result = 0;
        try {
            result = day15Service.doPartA(TARGET_TURN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day15AExample2() {
        Day15Service day15Service = new Day15Service("1,3,2");
        final long EXPECTED = 1L;
        long result = 0;
        try {
            result = day15Service.doPartA(TARGET_TURN_2020);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day15AExample3() {
        Day15Service day15Service = new Day15Service("2,1,3");
        final long EXPECTED = 10L;
        long result = 0;
        try {
            result = day15Service.doPartA(TARGET_TURN_2020);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day15AExample4() {
        Day15Service day15Service = new Day15Service("1,2,3");
        final long EXPECTED = 27L;
        long result = 0;
        try {
            result = day15Service.doPartA(TARGET_TURN_2020);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day15AExample5() {
        Day15Service day15Service = new Day15Service("2,3,1");
        final long EXPECTED = 78L;
        long result = 0;
        try {
            result = day15Service.doPartA(TARGET_TURN_2020);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day15AExample6() {
        Day15Service day15Service = new Day15Service("3,2,1");
        final long EXPECTED = 438L;
        long result = 0;
        try {
            result = day15Service.doPartA(TARGET_TURN_2020);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day15AExample7() {
        Day15Service day15Service = new Day15Service("3,1,2");
        final long EXPECTED = 1836L;
        long result = 0;
        try {
            result = day15Service.doPartA(TARGET_TURN_2020);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
   public void Day15A() {
       final long EXPECTED = 763L;
       long result = 0;
       try {
           result = RunDay15.problem15A();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }

   @Test
   public void Day15B() {
       final int EXPECTED = 0;
       int result = 0;
       try {
           result = RunDay15.problem15B();
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       Assert.assertEquals(EXPECTED, result);
   }
}
