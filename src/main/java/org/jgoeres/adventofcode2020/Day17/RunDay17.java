package org.jgoeres.adventofcode2020.Day17;
public abstract class RunDay17 {
    static String pathToInputs = "data/day17/input.txt";

    static Day17Service day17Service = new Day17Service();

    public static long problem17A() {
        System.out.println("=== DAY 17A ===");

        long result = day17Service.doPartA();

        System.out.println("Day 17A: Number of active cubes after 6 cycles = " + result);

        return result;
    }

    public static long problem17B() {
        System.out.println("=== DAY 17B ===");

        long result = day17Service.doPartB();

        System.out.println("Day 17B: Number of active hypercubes after 6 cycles = " + result);

        return result;
    }
}
    
