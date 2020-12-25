package org.jgoeres.adventofcode2020.Day22;
public abstract class RunDay22 {
    static String pathToInputs = "data/day22/input.txt";

    static Day22Service day22Service = new Day22Service();

    public static long problem22A() {
        System.out.println("=== DAY 22A ===");

        long result = day22Service.doPartA();

        System.out.println("Day 22A: Answer = " + result);

        return result;
    }

    public static int problem22B() {
        System.out.println("=== DAY 22B ===");

        int result = day22Service.doPartB();

        System.out.println("Day 22B: Answer = " + result);

        return result;
    }



}
