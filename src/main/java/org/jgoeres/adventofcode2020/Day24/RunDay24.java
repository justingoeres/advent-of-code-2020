package org.jgoeres.adventofcode2020.Day24;
public abstract class RunDay24 {
    static String pathToInputs = "data/day24/input.txt";

    static Day24Service day24Service = new Day24Service();

    public static int problem24A() {
        System.out.println("=== DAY 24A ===");

        int result = day24Service.doPartA();

        System.out.println("Day 24A: Number of Black Tiles = " + result);

        return result;
    }

    public static int problem24B() {
        System.out.println("=== DAY 24B ===");

        int result = day24Service.doPartB();

        System.out.println("Day 24B: Answer = " + result);

        return result;
    }



}
