package org.jgoeres.adventofcode2020.Day11;
public abstract class RunDay11 {
    static String pathToInputs = "data/day11/input.txt";

    static SeatingSystemService seatingSystemService = new SeatingSystemService();

    public static int problem11A() {
        System.out.println("=== DAY 11A ===");

        int result = seatingSystemService.doPartA();

        System.out.println("Day 11A: Number of seats occupied = " + result);

        return result;
    }

    public static int problem11B() {
        System.out.println("=== DAY 11B ===");

        int result = seatingSystemService.doPartB();

        System.out.println("Day 11B: Answer = " + result);

        return result;
    }



}
