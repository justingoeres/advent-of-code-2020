package org.jgoeres.adventofcode2020.Day03;
public abstract class RunDay03 {
    static String pathToInputs = "data/day03/input.txt";

    static TobogganService tobogganService = new TobogganService();

    public static int problem03A() {
        System.out.println("=== DAY 03A ===");

        // How many trees do we hit going on slope 3 RIGHT / 1 DOWN?
        int result = tobogganService.doPartA();

        System.out.println("Day 03A: Number of Trees Hit = " + result);

//        Day 03A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static long problem03B() {
        System.out.println("=== DAY 03B ===");

        long result = tobogganService.doPartB();

        System.out.println("Day 03B: Number of Trees Hit, all multiplied together = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
