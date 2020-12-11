package org.jgoeres.adventofcode2020.Day11;
public abstract class RunDay11 {
    static String pathToInputs = "data/day11/input.txt";

    static Day11Service day11Service = new Day11Service();

    public static int problem11A() {
        System.out.println("=== DAY 11A ===");

        int result = day11Service.doPartA();

        System.out.println("Day 11A: Answer = " + result);

        return result;
    }

    public static int problem11B() {
        System.out.println("=== DAY 11B ===");

        int result = day11Service.doPartB();

        System.out.println("Day 11B: Answer = " + result);

        return result;
    }



}
