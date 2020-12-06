package org.jgoeres.adventofcode2020.Day06;
public abstract class RunDay06 {
    static String pathToInputs = "data/day06/input.txt";

    static Day06Service day06Service = new Day06Service();

    public static int problem06A() {
        System.out.println("=== DAY 06A ===");

        int result = day06Service.doPartA();

        System.out.println("Day 06A: Answer = " + result);

//        Day 06A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem06B() {
        System.out.println("=== DAY 06B ===");

        int result = day06Service.doPartB();

        System.out.println("Day 06B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
