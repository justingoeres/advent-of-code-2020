package org.jgoeres.adventofcode2020.Day04;
public abstract class RunDay04 {
    static String pathToInputs = "data/day04/input.txt";

    static PassportService passportService = new PassportService();

    public static long problem04A() {
        System.out.println("=== DAY 04A ===");

        long result = passportService.doPartA();

        System.out.println("Day 04A: Number of valid passports = " + result);

//        Day 04A: Answer =
//        Time elapsed:	xxx ms

        return result;
    }

    public static int problem04B() {
        System.out.println("=== DAY 04B ===");

        int result = passportService.doPartB();

        System.out.println("Day 04B: Answer = " + result);

//        Day {$AOC_DAY}B: Answer =
//        Time elapsed:	xxx ms

        return result;
    }



}
