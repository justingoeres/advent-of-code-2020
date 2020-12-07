package org.jgoeres.adventofcode2020.Day07;
public abstract class RunDay07 {
    static String pathToInputs = "data/day07/input.txt";

    static BaggageService baggageService = new BaggageService();

    public static int problem07A() {
        System.out.println("=== DAY 07A ===");

        int result = baggageService.doPartA();

        System.out.println("Day 07A: Answer = " + result);

//        Day 07A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem07B() {
        System.out.println("=== DAY 07B ===");

        int result = baggageService.doPartB();

        System.out.println("Day 07B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
