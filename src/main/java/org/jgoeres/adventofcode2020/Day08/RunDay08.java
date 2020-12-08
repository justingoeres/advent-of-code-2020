package org.jgoeres.adventofcode2020.Day08;
public abstract class RunDay08 {
    static String pathToInputs = "data/day08/input.txt";

    static Day08Service day08Service = new Day08Service();

    public static int problem08A() {
        System.out.println("=== DAY 08A ===");

        int result = day08Service.doPartA();

        System.out.println("Day 08A: Accumulator when program loops = " + result);

//        Day 08A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem08B() {
        System.out.println("=== DAY 08B ===");

        int result = day08Service.doPartB();

        System.out.println("Day 08B: Accumulator when fixed program terminates = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
