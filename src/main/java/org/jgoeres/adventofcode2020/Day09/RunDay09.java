package org.jgoeres.adventofcode2020.Day09;
public abstract class RunDay09 {
    static String pathToInputs = "data/day09/input.txt";

    static Day09Service day09Service = new Day09Service();

    public static int problem09A() {
        System.out.println("=== DAY 09A ===");

        int result = day09Service.doPartA();

        System.out.println("Day 09A: Answer = " + result);

        return result;
    }

    public static int problem09B() {
        System.out.println("=== DAY 09B ===");

        int result = day09Service.doPartB();

        System.out.println("Day 09B: Answer = " + result);

        return result;
    }



}
