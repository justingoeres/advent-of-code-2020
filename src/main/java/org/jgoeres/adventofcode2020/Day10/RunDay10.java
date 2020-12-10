package org.jgoeres.adventofcode2020.Day10;
public abstract class RunDay10 {
    static String pathToInputs = "data/day10/input.txt";

    static Day10Service day10Service = new Day10Service();

    public static int problem10A() {
        System.out.println("=== DAY 10A ===");

        int result = day10Service.doPartA();

        System.out.println("Day 10A: Product of 'ones' & 'threes' joltage differences = " + result);

        return result;
    }

    public static int problem10B() {
        System.out.println("=== DAY 10B ===");

        int result = day10Service.doPartB();

        System.out.println("Day 10B: Answer = " + result);

        return result;
    }



}
