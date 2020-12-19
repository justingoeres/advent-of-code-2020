package org.jgoeres.adventofcode2020.Day19;
public abstract class RunDay19 {
    static String pathToInputs = "data/day19/input.txt";

    static Day19Service day19Service = new Day19Service();

    public static int problem19A() {
        System.out.println("=== DAY 19A ===");

        int result = day19Service.doPartA();

        System.out.println("Day 19A: Answer = " + result);

        return result;
    }

    public static int problem19B() {
        System.out.println("=== DAY 19B ===");

        int result = day19Service.doPartB();

        System.out.println("Day 19B: Answer = " + result);

        return result;
    }



}
