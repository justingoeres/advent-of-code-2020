package org.jgoeres.adventofcode2020.Day25;
public abstract class RunDay25 {
    static String pathToInputs = "data/day25/input.txt";

    static Day25Service day25Service = new Day25Service();

    public static int problem25A() {
        System.out.println("=== DAY 25A ===");

        int result = day25Service.doPartA();

        System.out.println("Day 25A: Answer = " + result);

        return result;
    }

    public static int problem25B() {
        System.out.println("=== DAY 25B ===");

        int result = day25Service.doPartB();

        System.out.println("Day 25B: Answer = " + result);

        return result;
    }



}
