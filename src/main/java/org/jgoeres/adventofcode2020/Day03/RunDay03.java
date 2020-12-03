package org.jgoeres.adventofcode2020.Day03;
public abstract class RunDay03 {
    static String pathToInputs = "data/day03/input.txt";

    static Day03Service day03Service = new Day03Service();

    public static int problem03A() {
        System.out.println("=== DAY 03A ===");

        int result = day03Service.doPartA();

        System.out.println("Day 03A: Answer = " + result);

//        Day 03A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem03B() {
        System.out.println("=== DAY 03B ===");

        int result = day03Service.doPartB();

        System.out.println("Day 03B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
