package org.jgoeres.adventofcode2020.Day05;
public abstract class RunDay05 {
    static String pathToInputs = "data/day05/input.txt";

    static Day05Service day05Service = new Day05Service();

    public static int problem05A() {
        System.out.println("=== DAY 05A ===");

        int result = day05Service.doPartA();

        System.out.println("Day 05A: Answer = " + result);

//        Day 05A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem05B() {
        System.out.println("=== DAY 05B ===");

        int result = day05Service.doPartB();

        System.out.println("Day 05B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
