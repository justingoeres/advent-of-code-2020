package org.jgoeres.adventofcode2020.Day21;
public abstract class RunDay21 {
    static String pathToInputs = "data/day21/input.txt";

    static Day21Service day21Service = new Day21Service();

    public static int problem21A() {
        System.out.println("=== DAY 21A ===");

        int result = day21Service.doPartA();

        System.out.println("Day 21A: Answer = " + result);

        return result;
    }

    public static int problem21B() {
        System.out.println("=== DAY 21B ===");

        int result = day21Service.doPartB();

        System.out.println("Day 21B: Answer = " + result);

        return result;
    }



}
