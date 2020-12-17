package org.jgoeres.adventofcode2020.Day17;
public abstract class RunDay17 {
    static String pathToInputs = "data/day17/input.txt";

    static Day17Service day17Service = new Day17Service();

    public static int problem17A() {
        System.out.println("=== DAY 17A ===");

        int result = day17Service.doPartA();

        System.out.println("Day 17A: Answer = " + result);

        return result;
    }

    public static int problem17B() {
        System.out.println("=== DAY 17B ===");

        int result = day17Service.doPartB();

        System.out.println("Day 17B: Answer = " + result);

        return result;
    }



}
