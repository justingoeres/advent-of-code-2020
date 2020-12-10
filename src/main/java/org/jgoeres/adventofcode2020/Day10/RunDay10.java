package org.jgoeres.adventofcode2020.Day10;
public abstract class RunDay10 {
    static String pathToInputs = "data/day10/input.txt";

    static AdapterService adapterService = new AdapterService();

    public static int problem10A() {
        System.out.println("=== DAY 10A ===");

        int result = adapterService.doPartA();

        System.out.println("Day 10A: Product of 'ones' & 'threes' joltage differences = " + result);

        return result;
    }

    public static Long problem10B() {
        System.out.println("=== DAY 10B ===");

        Long result = adapterService.doPartB();

        System.out.println("Day 10B: Total number of possible adapter combinations = " + result);

        return result;
    }



}
