package org.jgoeres.adventofcode2020.Day03;
public abstract class RunDay03 {
    static String pathToInputs = "data/day03/input.txt";

    static TobogganService tobogganService = new TobogganService();

    public static int problem03A() {
        System.out.println("=== DAY 03A ===");

        int result = tobogganService.doPartA();

        System.out.println("Day 03A: Number of Trees Hit = " + result);

//        Day 03A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem03B() {
        System.out.println("=== DAY 03B ===");

        int result = tobogganService.doPartB();

        System.out.println("Day 03B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
