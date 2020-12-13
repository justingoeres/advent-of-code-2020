package org.jgoeres.adventofcode2020.Day13;
public abstract class RunDay13 {
    static String pathToInputs = "data/day13/input.txt";

    static Day13Service day13Service = new Day13Service();

    public static long problem13A() {
        System.out.println("=== DAY 13A ===");

        long result = day13Service.doPartA();

        System.out.println("Day 13A: Answer = " + result);

        return result;
    }

    public static long problem13B() {
        System.out.println("=== DAY 13B ===");

        long result = day13Service.doPartB();

        System.out.println("Day 13B: Answer = " + result);

        return result;
    }



}
