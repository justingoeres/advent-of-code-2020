package org.jgoeres.adventofcode2020.Day12;
public abstract class RunDay12 {
    static String pathToInputs = "data/day12/input.txt";

    static Day12Service day12Service = new Day12Service();

    public static int problem12A() {
        System.out.println("=== DAY 12A ===");

        int result = day12Service.doPartA();

        System.out.println("Day 12A: Answer = " + result);

        return result;
    }

    public static int problem12B() {
        System.out.println("=== DAY 12B ===");

        int result = day12Service.doPartB();

        System.out.println("Day 12B: Answer = " + result);

        return result;
    }



}
