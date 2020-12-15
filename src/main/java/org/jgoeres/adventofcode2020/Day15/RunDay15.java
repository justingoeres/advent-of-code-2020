package org.jgoeres.adventofcode2020.Day15;
public abstract class RunDay15 {
    static String pathToInputs = "data/day15/input.txt";

    static Day15Service day15Service = new Day15Service();

    public static int problem15A() {
        System.out.println("=== DAY 15A ===");

        int result = day15Service.doPartA();

        System.out.println("Day 15A: Answer = " + result);

        return result;
    }

    public static int problem15B() {
        System.out.println("=== DAY 15B ===");

        int result = day15Service.doPartB();

        System.out.println("Day 15B: Answer = " + result);

        return result;
    }



}
