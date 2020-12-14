package org.jgoeres.adventofcode2020.Day14;
public abstract class RunDay14 {
    static String pathToInputs = "data/day14/input.txt";

    static Day14Service day14Service = new Day14Service();

    public static int problem14A() {
        System.out.println("=== DAY 14A ===");

        int result = day14Service.doPartA();

        System.out.println("Day 14A: Answer = " + result);

        return result;
    }

    public static int problem14B() {
        System.out.println("=== DAY 14B ===");

        int result = day14Service.doPartB();

        System.out.println("Day 14B: Answer = " + result);

        return result;
    }



}
