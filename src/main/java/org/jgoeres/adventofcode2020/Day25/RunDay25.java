package org.jgoeres.adventofcode2020.Day25;
public abstract class RunDay25 {
    static String pathToInputs = "data/day25/input.txt";

    static Day25Service day25Service = new Day25Service();

    public static long problem25A() {
        System.out.println("=== DAY 25A ===");

        long result = day25Service.doPartA();

        System.out.println("Day 25A: Encryption key = " + result);

        return result;
    }
}
