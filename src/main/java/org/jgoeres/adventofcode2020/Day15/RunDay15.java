package org.jgoeres.adventofcode2020.Day15;
public abstract class RunDay15 {
    static Day15Service day15Service = new Day15Service();

    public static long problem15A() {
        final long TARGET_TURN = 2020;
        System.out.println("=== DAY 15A ===");

        long result = day15Service.doPartA(TARGET_TURN);

        System.out.println("Day 15A: 2020th number spoken = " + result);

        return result;
    }

    public static long problem15B() {
        System.out.println("=== DAY 15B ===");

        long result = day15Service.doPartB();

        System.out.println("Day 15B: 30,000,000th number spoken = " + result);

        return result;
    }



}
