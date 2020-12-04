package org.jgoeres.adventofcode2020.Day04;
public abstract class RunDay04 {
    static String pathToInputs = "data/day04/input.txt";

    static Day04Service day04Service = new Day04Service();

    public static int problem04A() {
        System.out.println("=== DAY 04A ===");

        int result = day04Service.doPartA();

        System.out.println("Day 04A: Answer = " + result);

//        Day 04A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem04B() {
        System.out.println("=== DAY 04B ===");

        int result = day04Service.doPartB();

        System.out.println("Day 04B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
